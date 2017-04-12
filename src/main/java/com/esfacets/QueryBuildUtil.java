package com.esfacets;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import com.esfacets.input.UserQuery;

public class QueryBuildUtil {

	/**
	 * This method build the search query based upon the data from the rest
	 * service.
	 * 
	 * @param userQuery
	 *            Object that contain the information from the rest service,
	 *            containing language, body type, paint code, price range, and
	 *            first registration date ranges.
	 * @return A QueryBuilder object, that contain the query build from the data
	 *         from rest service.
	 */
	public QueryBuilder buildQuery(UserQuery userQuery) {

		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

		if (userQuery.getLanguage().isEmpty() && userQuery.getBodyType().isEmpty() && userQuery.getPaint().isEmpty()
				&& userQuery.getFuelType().isEmpty() && userQuery.getTransmission().isEmpty()
				&& userQuery.getPriceInformation() == null && userQuery.getFirstRegistrationDate() == null) {
			return QueryBuilders.matchAllQuery();

		}

		if (userQuery.getQuery().toLowerCase().equals(ESFacetConstants.OR)) {

//			for (Entry<String, List<String>> query : queries(userQuery).entrySet()) {
//				queryBuilder.should(QueryBuilders.termsQuery(query.getKey(), query.getValue()));
//			}
			// with lambda expressions
			queries(userQuery).entrySet().forEach(query -> {queryBuilder.should(QueryBuilders.termsQuery(query.getKey(), query.getValue()));});

		} else if (userQuery.getQuery().toLowerCase().equals(ESFacetConstants.AND)) {
//			for (Entry<String, List<String>> query : queries(userQuery).entrySet()) {
//				queryBuilder.must(QueryBuilders.termsQuery(query.getKey(), query.getValue()));
//			}
			// with lambda expressions
			queries(userQuery).entrySet().forEach(query -> {queryBuilder.must(QueryBuilders.termsQuery(query.getKey(), query.getValue()));});
		}

		if (userQuery.getPriceInformation() != null) {
			queryBuilder.must(QueryBuilders.rangeQuery(ESFacetConstants.PRICE)
					.gt(userQuery.getPriceInformation().getMin()).lt(userQuery.getPriceInformation().getMax()));
		}

		if (userQuery.getFirstRegistrationDate() != null) {
			queryBuilder.must(
					QueryBuilders.rangeQuery(ESFacetConstants.DATE).gt(userQuery.getFirstRegistrationDate().getMin())
							.lt(userQuery.getFirstRegistrationDate().getMax()));
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