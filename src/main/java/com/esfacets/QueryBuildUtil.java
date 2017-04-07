package com.esfacets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import com.esfacets.input.UserInput;

public class QueryBuildUtil {

	/**
	 * This method build the search query for logical OR, or AND.
	 * 
	 * @param userInput
	 *            Data from the user, based upon the facets will be made,
	 *            language, body type, paint, fuel type, transmission codes, and
	 *            the price and first registration dates interval.
	 * @return A query build for OR, or AND.
	 */
	public QueryBuilder buildQuery(UserInput userInput) {

		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

		if (userInput.getLanguage().isEmpty() && userInput.getBodyType().isEmpty() && userInput.getPaint().isEmpty()
				&& userInput.getFuelType().isEmpty() && userInput.getTransmission().isEmpty()) {
			return QueryBuilders.matchAllQuery();

		} else if (userInput.getQuery().toLowerCase().equals(ESFacetConstants.OR)) {
			for (Entry<String, List<String>> s : queries(userInput).entrySet()) {
				queryBuilder.should(QueryBuilders.termsQuery(s.getKey(), s.getValue()));
			}

		} else if (userInput.getQuery().toLowerCase().equals(ESFacetConstants.AND)) {
			for (Entry<String, List<String>> s : queries(userInput).entrySet()) {
				queryBuilder.must(QueryBuilders.termsQuery(s.getKey(), s.getValue()));
			}
		}
		return queryBuilder;
	}

	private Map<String, List<String>> queries(UserInput userInput) {

		Map<String, List<String>> fieldnameAndInputMap = new HashMap<>();

		fieldnameAndInputMap.put(ESFacetConstants.LANGUAGE, userInput.getLanguage());
		fieldnameAndInputMap.put(ESFacetConstants.BODY_TYPE, userInput.getBodyType());
		fieldnameAndInputMap.put(ESFacetConstants.PAINT, userInput.getPaint());
		fieldnameAndInputMap.put(ESFacetConstants.FUEL_TYPE, userInput.getFuelType());
		fieldnameAndInputMap.put(ESFacetConstants.TRANSMISSION, userInput.getTransmission());

		return fieldnameAndInputMap;
	}
}
