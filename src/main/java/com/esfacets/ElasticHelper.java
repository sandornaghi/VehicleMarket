package com.esfacets;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.search.aggregations.bucket.range.Range;
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
	 * for this request, prices for the vehicles, and the number of vehicles
	 * within the given price interval.
	 * 
	 * @param stats
	 *            Object that contain data with statistics.
	 * @param terms
	 *            Object that contain data with specific vehicle quantities and
	 *            prices.
	 * @param range
	 *            Object that contain vehicle numbers in the given prices.
	 * @return A FacetResponse object containing the above informations
	 *         transformed.
	 */
	public FacetResponse buildFacetResponse(Stats stats, Terms terms, Range range) {

		FacetResponse facetResponse = new FacetResponse();

		// set the statistics
		facetResponse.setMinPrice(stats.getMin());
		facetResponse.setMaxPrice(stats.getMax());
		facetResponse.setVehicleNumbers((int) stats.getCount());
		
		// set vehicle price and number
		List<Bucket> list = terms.getBuckets();
		List<ElasticFacetsAndTerms> facetList = new ArrayList<>();

		for (Bucket bucket : list) {
			ElasticFacetsAndTerms facetTerms = new ElasticFacetsAndTerms();
			facetTerms.setVehicleNumbers((int) bucket.getDocCount());
			facetTerms.setPrice(bucket.getKeyAsString());
			facetList.add(facetTerms);
		}
		facetResponse.setTermsList(facetList);

		// vehicles within the price interval
		int vehicleNumWithPriceInterval = (int) range.getBuckets().get(0).getDocCount();
		if (vehicleNumWithPriceInterval != 0) {
			facetResponse.setVehicleNumbers(vehicleNumWithPriceInterval);
			facetResponse.setMinPrice((double) range.getBuckets().get(0).getFrom());
			facetResponse.setMaxPrice((double) range.getBuckets().get(0).getTo());
		}
		
		return facetResponse;
	}
}