package com.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.metadata.AliasOrIndex;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import com.esfacets.FacetResponseUtil;
import com.esfacets.input.UserInput;
import com.esfacets.ESFacetConstants;
import com.esfacets.FacetResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.response.ResponseCodeAndDescription;
import com.transformedvehicles.TVehicle;

import static com.response.ResponseCodeAndDescription.SUCCESS_IMPORT;
import static com.response.ResponseCodeAndDescription.ELASTIC_DUPLICATE_INDEX;
import static com.response.ResponseCodeAndDescription.ELASTIC_INTSERTION_ERROR;

import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.stats.Stats;

/**
 * This class is used to insert and read vehicles from Elasticsearch.
 * 
 * @author sandor.naghi
 *
 */
public class ElasticsearchVehicleService {

	private static final Logger LOGGER = Logger.getLogger(ElasticsearchVehicleService.class.getName());

	@Inject
	private TransportClient transportClient;

	@Inject
	private FacetResponseUtil facetResponseHelper;

	/**
	 * Insert a List of Vehicles in Elasticsearch for the given market and
	 * category. First checks if the index exists, if no than is created.
	 * 
	 * @param country
	 *            The given market.
	 * @param vehicleCategory
	 *            Category of the vehicle, that can be new or used.
	 * @param tVehicleList
	 *            The List of Vehicles that will be inserted.
	 */
	public ResponseCodeAndDescription insertTVehiclesToElasticsearch(String country, String vehicleCategory,
			List<TVehicle> tVehicleList) {

		String alias = country.toLowerCase() + "_" + vehicleCategory.toLowerCase();
		String index = alias + "_"
				+ DateTimeFormatter.ofPattern(ESFacetConstants.DATE_TIME_FORMAT).format(LocalDateTime.now());

		// check if index exists
		boolean indexExists = transportClient.admin().indices().prepareExists(index).execute().actionGet().isExists();

		ResponseCodeAndDescription response;

		if (!indexExists) {

			// create index
			transportClient.admin().indices().prepareCreate(index).get();

			// add mapping to index
			MappingReader mapping = new MappingReader();
			String mappingAsString = mapping.getMapping();

			transportClient.admin().indices().preparePutMapping(index).setType(ESFacetConstants.INDEX_TYPE)
					.setSource(mappingAsString).execute().actionGet();

			// insert into index
			BulkRequestBuilder bulkRequest = transportClient.prepareBulk();

			for (TVehicle tVehicle : tVehicleList) {
				bulkRequest.add(transportClient.prepareIndex(index, ESFacetConstants.INDEX_TYPE)
						.setSource(new Gson().toJson(tVehicle)));
			}

			BulkResponse resp = bulkRequest.get();

			// if has no errors in insertion remove alias from old index
			if (!resp.hasFailures()) {
				boolean aliasExists = transportClient.admin().indices().prepareExists(alias).execute().actionGet()
						.isExists();

				if (aliasExists) {
					// find the index for the alias
					SortedMap<String, AliasOrIndex> lookup = transportClient.admin().cluster().prepareState().execute()
							.actionGet().getState().getMetaData().getAliasAndIndexLookup();

					// if index exists remove alias
					if (lookup.containsKey(alias)) {
						String existentIndex = lookup.get(alias).getIndices().get(0).getIndex();
						transportClient.admin().indices().prepareAliases().removeAlias(existentIndex, alias).execute()
								.actionGet();
					}
				}

				// add alias to new index
				transportClient.admin().indices().prepareAliases().addAlias(index, alias).execute().actionGet();
				response = new ResponseCodeAndDescription(SUCCESS_IMPORT);

			} else {
				response = new ResponseCodeAndDescription(ELASTIC_INTSERTION_ERROR);
			}
		} else {
			response = new ResponseCodeAndDescription(ELASTIC_DUPLICATE_INDEX);
		}

		return response;
	}

	/**
	 * Extract a List of Vehicles from the Elasticsearch, if the index exists.
	 * 
	 * @param alias
	 *            The alias that we search.
	 * @param language
	 *            The language we search.
	 * @return A List of Vehicles that fits for the given alias(which is build
	 *         for the market and category) and language.
	 */
	public List<TVehicle> readTVehiclesFromElasticsearch(String alias, String language) {

		List<TVehicle> tVehicleList = new ArrayList<>();

		boolean exists = transportClient.admin().indices().prepareExists(alias).execute().actionGet().isExists();

		if (exists) {
			SearchResponse response = transportClient.prepareSearch(alias)
					.setQuery(QueryBuilders.termQuery("language", language)).execute().actionGet();

			tVehicleList = getTVehiclesFromResponse(response);
		}

		return tVehicleList;
	}

	/**
	 * This method read a facet from Elasticsearch for a given context, based
	 * upon the user input.
	 * 
	 * @param alias
	 *            The context for the given country and vehicle category.
	 * @param userInput
	 *            Data from the user, based upon the facets will be made,
	 *            language, body type, paint, fuel type, transmission codes, and
	 *            the price and first registration dates interval.
	 * @return A FacetResponse object, that contain the facets results.
	 */
	public FacetResponse getFacetsForVehicles(String alias, UserInput userInput) {

		SearchRequestBuilder requestBuilder = transportClient.prepareSearch(alias)
				.setQuery(facetResponseHelper.buildQuery(userInput));

		for (AbstractAggregationBuilder aggregationBuilder : facetResponseHelper.buildFacets(userInput)) {
			requestBuilder.addAggregation(aggregationBuilder);
		}

		SearchResponse response = requestBuilder.execute().actionGet();

		Stats stats = response.getAggregations().get(ESFacetConstants.COUNT);

		Terms terms = response.getAggregations().get(ESFacetConstants.TERMS);

		Range priceRange = response.getAggregations().get(ESFacetConstants.PRICE_RANGE);

		Range dateRange = response.getAggregations().get(ESFacetConstants.DATE_RANGE);
		
		FacetResponse facetResponse = facetResponseHelper.buildFacetResponse(stats, terms, priceRange, dateRange);

		if (userInput.isWithVehicleList()) {
			facetResponse.settVehicleList(getTVehiclesFromResponse(response));
		}
		
		return facetResponse;
	}
	
	private List<TVehicle> getTVehiclesFromResponse(SearchResponse response) {
		
		List<TVehicle> tVehicleList = new ArrayList<>();
		
		SearchHit[] hits = response.getHits().getHits();
		if (hits.length != 0) {
			for (SearchHit hit : hits) {

				String source = hit.getSourceAsString();

				ObjectMapper mapper = new ObjectMapper();
				try {
					TVehicle tVehicle = mapper.readValue(source, TVehicle.class);
					tVehicleList.add(tVehicle);
				} catch (IOException e) {
					LOGGER.severe(e.getMessage());
				}
			}
		}
		return tVehicleList;
	}
	
}
