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
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.metadata.AliasOrIndex;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.transformedvehicles.TVehicle;

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
	public void insertTVehiclesToElasticsearch(String country, String vehicleCategory, List<TVehicle> tVehicleList) {

		String alias = country.toLowerCase() + "_" + vehicleCategory.toLowerCase();
		String index = alias + "_" + DateTimeFormatter.ofPattern("yyyyMMddhhmm").format(LocalDateTime.now());

		// check if index exists
		boolean indexExists = transportClient.admin().indices().prepareExists(index).execute().actionGet().isExists();

		if (!indexExists) {

			// create index
			transportClient.admin().indices().prepareCreate(index).get();

			// insert into index
			BulkRequestBuilder bulkRequest = transportClient.prepareBulk();

			for (TVehicle tVehicle : tVehicleList) {
				bulkRequest.add(transportClient.prepareIndex(index, "vehicle").setSource(new Gson().toJson(tVehicle)));
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
			}
			
		}
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

			SearchHit[] hits = response.getHits().getHits();
			if (hits.length != 0) {
				for (SearchHit hit : hits) {

					String source = new String(hit.getSourceAsString());

					ObjectMapper mapper = new ObjectMapper();
					try {
						TVehicle tVehicle = mapper.readValue(source, TVehicle.class);
						tVehicleList.add(tVehicle);
					} catch (IOException e) {
						LOGGER.severe(e.getMessage());
					}
				}
			}
		}
		return tVehicleList;
	}

}
