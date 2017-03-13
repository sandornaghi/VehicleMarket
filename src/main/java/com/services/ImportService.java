package com.services;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.xml.bind.JAXBException;

import com.response.VSEResponse;
import com.transformedvehicles.TVehicle;
import com.vsevehiclebeans.Vehicles;

/**
 * In this class the import from the VSE system is made, and a Response is build
 * for the Rest services.
 * 
 * @author sandor.naghi
 *
 */
public class ImportService {

	private static final Logger LOGGER = Logger.getLogger(ImportService.class.getName());

	@Inject
	private VSEService vseService;

	@Inject
	private TransformationService transService;

	@Inject
	private ElasticsearchVehicleService insertServce;

	/**
	 * This method do the import from the VSE system, and builds the response
	 * for the REST service.
	 * 
	 * @param country
	 *            The market from where we do the import.
	 * @param vehicleCategory
	 *            The category of the vehicle, new or used.
	 * @return Response for the REST service, if the status code is 200 then
	 *         successful, if 401 then there are no rules, and if 400 then the
	 *         input is invalid.
	 */
	public VSEResponse importVseVehicle(String country, String vehicleCategory) {

		VSEResponse response = null;

		Vehicles vehicles = null;
		try {
			vehicles = vseService.getVehiclesFromVSE(country, vehicleCategory);
			if (vehicles == null ) {
				response = new VSEResponse(401, "Invalid input, inexistent country or vehicle category in the VSE system.");
			}
		} catch (JAXBException e) {
			LOGGER.severe(e.getMessage());
			response = new VSEResponse(402, "Getting vehicles from VSE system failed!");
		}

		if (vehicles != null) {
			List<TVehicle> tVehicleList = transService.transformVehicles(country, vehicleCategory, vehicles);
			if (tVehicleList.isEmpty()) {
				response = new VSEResponse(403, String.format("No rules found for %s / %s", country, vehicleCategory));
			} else { // else the status code is 200
				switch (insertServce.insertTVehiclesToElasticsearch(country, vehicleCategory,tVehicleList)) {
				case 200:
					response = new VSEResponse(200, "Successful import");
					break;
				case 404:
					response = new VSEResponse(404, "Insertion to Elasticsearch failed due the existence of same index.");
					break;
				case 405:
					response = new VSEResponse(404, "Insertion to Elasticsearch failed due an internal error.");
					break;
				}
			}
		}
		return response;
	}

}
