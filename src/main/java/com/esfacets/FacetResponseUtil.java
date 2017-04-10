package com.esfacets;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.range.Range;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.stats.Stats;

import com.esfacets.input.UserInput;

import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;

/**
 * This is a helper class for transforming the input from the request, and to
 * build the response.
 * 
 * @author sandor.naghi
 *
 */
public class FacetResponseUtil {

	private static final Logger LOGGER = Logger.getLogger(FacetResponseUtil.class.getName());

	/**
	 * This method build the facets, based upon the data from the user.
	 * 
	 * @param userInput
	 *            Data from the user, based upon the facets will be made,
	 *            language, body type, paint, fuel type, transmission codes, and
	 *            the price and first registration dates interval.
	 * @return List of facets.
	 */
	public List<AbstractAggregationBuilder> buildFacets(UserInput userInput) {

		List<AbstractAggregationBuilder> list = new ArrayList<>();

		list.add(AggregationBuilders.stats(ESFacetConstants.COUNT).field(ESFacetConstants.PRICE));
		list.add(AggregationBuilders.terms(ESFacetConstants.TERMS).field(ESFacetConstants.PRICE));

		if (userInput.getPriceInformation() != null) {
			list.add(AggregationBuilders.range(ESFacetConstants.PRICE_RANGE)
					.addRange(userInput.getPriceInformation().getMin(), userInput.getPriceInformation().getMax())
					.field(ESFacetConstants.PRICE));
		}

		if (userInput.getFirstRegistrationDate() != null){
			double minFirstRegistrationDate = getDateFromUser(userInput.getFirstRegistrationDate().getMin());
			double maxFirstRegistrationDate = getDateFromUser(userInput.getFirstRegistrationDate().getMax());
			list.add(AggregationBuilders.range(ESFacetConstants.DATE_RANGE)
					.addRange(minFirstRegistrationDate, maxFirstRegistrationDate).field(ESFacetConstants.DATE));
		}
		
		return list;
	}

	/**
	 * Transform a String to a Date, and then to double.
	 * 
	 * @param dateAsString
	 *            The String that will be transformed to double.
	 * @return The double representation of the String(date).
	 */
	public double getDateFromUser(String dateAsString) {

		DateFormat format = new SimpleDateFormat(ESFacetConstants.DATE_FORMAT);
		double dateAsDouble = 0;

		try {
			Date date = format.parse(dateAsString);
			dateAsDouble = date.getTime();
		} catch (ParseException e) {
			LOGGER.severe(e.getMessage());
		}

		return dateAsDouble;
	}

	/**
	 * This method build a Facet Response, from the responses from
	 * Elasticsearch.
	 * 
	 * @param stats
	 *            A metric facet response from Elasticsearch, contain the total
	 *            vehicle numbers.
	 * @param terms
	 *            A bucket facet response from Elasticsearch, include the
	 *            vehicle numbers for certain prices.
	 * @param priceRange
	 *            A bucket facet response from Elasticsearch, containing the
	 *            number of vehicles in the given price range.
	 * @param dateRange
	 *            A bucket facet response from Elasticsearch, containing the
	 *            number of vehicles in the given date range.
	 * @return A FacetResponse Object, containing all the facet responses.
	 */
	public FacetResponse buildFacetResponse(Stats stats, Terms terms, Range priceRange, Range dateRange) {

		FacetResponse facetResponse = new FacetResponse();

		// set the statistics
		facetResponse.setTotalVehicles((int) stats.getCount());
		facetResponse.setVehicleMinPrice(stats.getMin());
		facetResponse.setVehicleMaxPrice(stats.getMax());
		
		
		// set vehicle price and number
		List<ElasticFacetsAndTerms> facetList = new ArrayList<>();

		for (Bucket bucket : terms.getBuckets()) {
			ElasticFacetsAndTerms facetTerms = new ElasticFacetsAndTerms();
			facetTerms.setVehicleNumbers((int) bucket.getDocCount());
			facetTerms.setPrice(bucket.getKeyAsString());
			facetList.add(facetTerms);
		}
		facetResponse.setTermsList(facetList);

		// vehicles within the price interval
		if (priceRange != null) {
			facetResponse.setVehicleNumWithinPrices((int) priceRange.getBuckets().get(0).getDocCount());
		}

		// vehicles within the date interval
		if (dateRange != null) {
			facetResponse.setVehicleNumWithinDates((int) dateRange.getBuckets().get(0).getDocCount());
		}
		
		return facetResponse;
	}
}