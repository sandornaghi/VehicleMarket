package com.elasticsearchfacets;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.stats.Stats;

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
	 * This method build a facet response, that contain the number of vehicles
	 * for this request, and the prices for the vehicles.
	 * 
	 * @param stats
	 *            Object that contain data with statistics.
	 * @param terms
	 *            Object that contain data with specific vehicle quantities and
	 *            prices.
	 * @return A ElasticsearchFacetResponse object containing the above
	 *         informations transformed.
	 */
	public ElasticFacetResponse buildFacetResponse(Stats stats, Terms terms) {

		ElasticFacetResponse elasticFacet = new ElasticFacetResponse();

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
