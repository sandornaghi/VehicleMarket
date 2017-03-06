package com.services;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.xml.bind.JAXBException;

import com.helper.VSEResponse;
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
		} catch (JAXBException e) {
			LOGGER.severe(e.getMessage());
		}

		if (vehicles != null) {
			int statusCode = transService.transformVehicles(country, vehicleCategory, vehicles);
			if (statusCode == 401) {
				response = new VSEResponse(statusCode, "No rules found for " + country + "/" + vehicleCategory);
			} else { // else the status code is 200
				response = new VSEResponse(statusCode, "Successful import");
			}
		} else {

			response = new VSEResponse(400, "Invalid input");
		}

		return response;
	}

}
