package com.elasticsearchfacets;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.stats.Stats;
import org.json.JSONObject;

import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;

/**
 * This is a helper class for transforming the input from the request, and to
 * build the response.
 * 
 * @author sandor.naghi
 *
 */
public class ElasticHelper {

	/**
	 * This method transform the input from the request to a java object.
	 * @param input	String that contain the request.
	 * @return	A UserInput object, that that contain the key and value of the request.
	 */
	public UserInput transformInput(String input) {

		JSONObject jsonObject = new JSONObject(input);

		UserInput userInput = new UserInput();

		@SuppressWarnings("unchecked")
		Iterator<String> keys = jsonObject.keys();
		while (keys.hasNext()) {
			userInput.setKey(keys.next());
			userInput.setValue(jsonObject.getString(userInput.getKey()));
		}

		return userInput;
	}

	/**
	 * This method build a facet response, that contain the number of vehicles for this request, and the prices for the vehicles.
	 * @param stats	Object that contain data with statistics.
	 * @param terms	Object that contain data with specific vehicle quantities and prices.
	 * @return	A ElasticsearchFacetResponse object containing the above informations transformed.
	 */
	public ElasticsearchFacetResponse buildFacetResponse(Stats stats, Terms terms) {

		ElasticsearchFacetResponse elasticFacet = new ElasticsearchFacetResponse();
		
		elasticFacet.setMinPrice((int) stats.getMin());
		elasticFacet.setMaxPrice((int) stats.getMax());
		elasticFacet.setVehicleNumbers((int) stats.getCount());

		List<Bucket> list = terms.getBuckets();

		List<ElasticFacetTerms> facetList = new ArrayList<>();

		for (Bucket bucket : list) {
			ElasticFacetTerms facetTerms = new ElasticFacetTerms();
			facetTerms.setVehicleNumbers((int) bucket.getDocCount());
			facetTerms.setValue(bucket.getKeyAsString());
			facetList.add(facetTerms);
		}
		elasticFacet.setFacetList(facetList);

		return elasticFacet;
	}
}
