package com.esfacets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import com.esfacets.input.UserQuery;

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
	public QueryBuilder buildQuery(UserQuery userQuery) {

		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

		if (userQuery.getLanguage().isEmpty() && userQuery.getBodyType().isEmpty() && userQuery.getPaint().isEmpty()
				&& userQuery.getFuelType().isEmpty() && userQuery.getTransmission().isEmpty()
				&& userQuery.getPriceInformation() == null) {
			return QueryBuilders.matchAllQuery();

		} else if (userQuery.getQuery().toLowerCase().equals(ESFacetConstants.OR)) {
			
			for (Entry<String, List<String>> query : queries(userQuery).entrySet()) {
				queryBuilder.should(QueryBuilders.termsQuery(query.getKey(), query.getValue()));
			}

		} else if (userQuery.getQuery().toLowerCase().equals(ESFacetConstants.AND)) {
			for (Entry<String, List<String>> query : queries(userQuery).entrySet()) {
				queryBuilder.must(QueryBuilders.termsQuery(query.getKey(), query.getValue()));
			}
		}
		
		if (userQuery.getPriceInformation() != null) {
			queryBuilder.must(QueryBuilders.rangeQuery(ESFacetConstants.PRICE).gt(userQuery.getPriceInformation().getMin()).lt(userQuery.getPriceInformation().getMax()));
		}
		
		return queryBuilder;
	}

	private Map<String, List<String>> queries(UserQuery userQuery) {

		Map<String, List<String>> fieldnameAndInputMap = new HashMap<>();

		fieldnameAndInputMap.put(ESFacetConstants.LANGUAGE, userQuery.getLanguage());
		fieldnameAndInputMap.put(ESFacetConstants.BODY_TYPE, userQuery.getBodyType());
		fieldnameAndInputMap.put(ESFacetConstants.PAINT, userQuery.getPaint());
		fieldnameAndInputMap.put(ESFacetConstants.FUEL_TYPE, userQuery.getFuelType());
		fieldnameAndInputMap.put(ESFacetConstants.TRANSMISSION, userQuery.getTransmission());

		return fieldnameAndInputMap;
	}
}