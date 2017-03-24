package com.services.importsearchtransform;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.xml.bind.JAXBException;

import static com.response.ResponseCodeAndDescription.*;

import com.response.ResponseCodeAndDescription;
import com.response.VehicleResponse;
import com.services.ElasticsearchVehicleService;
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
	private VSEDataRetrievelService vseService;

	@Inject
	private TransformationService transformationService;

	@Inject
	private ElasticsearchVehicleService insertService;

	/**
	 * This method do the import from the VSE system, and builds the response
	 * for the REST service.
	 * 
	 * @param country
	 *            The market from where we do the import.
	 * @param vehicleCategory
	 *            The category of the vehicle, new or used.
	 * @return Response for the REST service with the code and the description
	 *         of the response.
	 */
	public ResponseCodeAndDescription importVseVehicle(String country, String vehicleCategory) {

		ResponseCodeAndDescription response = null;

		VehicleResponse vehicleResponse = null;

		Vehicles vehicles = null;

		try {
			vehicleResponse = vseService.getVehiclesFromVSE(country, vehicleCategory);
			vehicles = vehicleResponse.getVehicles();
			if (vehicles == null) {
				response = new ResponseCodeAndDescription(VSE_INEXISTENT);
			}
		} catch (JAXBException e) {
			LOGGER.severe(e.getMessage());
			response = new ResponseCodeAndDescription(VSE_FAILED);
		}

		if (vehicles != null) {
			List<TVehicle> tVehicleList = transformationService.transformVehicles(country, vehicleCategory, vehicles);
			if (tVehicleList.isEmpty()) {
				response = new ResponseCodeAndDescription(VSE_NO_RULES);
			} else {
				response = insertService.insertTVehiclesToElasticsearch(country, vehicleCategory, tVehicleList);
			}
		}

		return response;
	}
}
