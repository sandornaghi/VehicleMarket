package com.services;

import javax.inject.Inject;

import com.response.Context;
import com.response.ElasticsearchResponse;
import com.response.StatusInfo;

/**
 * In this class the response in build for the REST service, for a given market,
 * category and language.
 * 
 * @author sandor.naghi
 *
 */
public class SearchService {

	private static final int SUCCESS_CODE = 200;
	private static final String SUCCESS_TEXT = "OK";

	private static final int NO_RESULT_CODE = 701;
	private static final String NO_RESULT_TEXT = "No result for %s/%s/%s";

	@Inject
	private ElasticsearchVehicleService elasticService;

	/**
	 * Create a list of vehicles for the given market, category and language if
	 * exists.
	 * 
	 * @param country
	 *            Country based upon the search is made.
	 * @param vehicleCategory
	 *            The vehicle category that can be new or used.
	 * @param language
	 *            The language based upon we make the search.
	 * @return A List of vehicles, that match the country, category and
	 *         language, and a status of 200 if vehicles are available, or 401
	 *         if not.
	 */
	public ElasticsearchResponse searchTVehicles(String country, String vehicleCategory, String language) {

		Context context = new Context(country, vehicleCategory, language);

		ElasticsearchResponse elasticResp = new ElasticsearchResponse();
		elasticResp.setContext(context);

		String alias = country + "_" + vehicleCategory;

		elasticResp.settVehicleList(elasticService.readTVehiclesFromElasticsearch(alias, language));

		StatusInfo statusInfo = new StatusInfo();
		if (elasticResp.gettVehicleList().isEmpty()) {
			statusInfo.setCode(NO_RESULT_CODE);
			statusInfo.setText(String.format(NO_RESULT_TEXT, country, vehicleCategory, language));
		} else {
			statusInfo.setCode(SUCCESS_CODE);
			statusInfo.setText(SUCCESS_TEXT);
		}
		elasticResp.setStatusInfo(statusInfo);

		return elasticResp;
	}
}
