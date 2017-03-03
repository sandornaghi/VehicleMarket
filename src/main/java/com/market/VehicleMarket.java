package com.market;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import com.helper.TVehicleHelper;

//import org.elasticsearch.client.transport.TransportClient;

import com.rulebeans.Configuration;
import com.rulebeans.Correction;
import com.services.RuleService;
import com.services.TransformationService;
import com.services.VSEService;
import com.transformedvehicles.TVehicles;
import com.vsevehiclebeans.Vehicles;

@Path("/vehicle")
public class VehicleMarket {

	private static final Logger LOGGER = Logger.getLogger(VehicleMarket.class.getName());

	@Inject
	private VSEService vseService;

	@Inject
	private RuleService ruleService;

	@Inject
	private TransformationService transService;

	// @Inject
	// private TransportClient client;

	@GET
	@Path("/{country}/{vehicleCategory}")
	@Produces("application/json")
	public Response importVehiclesFromVSE(@PathParam("country") String country,
			@PathParam("vehicleCategory") String vehicleCategory) {

		Vehicles vehicles = null;

		try {
			vehicles = vseService.getVehiclesFromVSE(country, vehicleCategory);
		} catch (JAXBException e) {
			LOGGER.severe(e.getMessage());
		}

		String response = null;
		if (vehicles != null) {

			List<Correction> corrections = ruleService.getCorrectionRules(country, vehicleCategory);

			List<Configuration> configurations = ruleService.getConfigurationRules(country, vehicleCategory);

			TVehicleHelper helper = new TVehicleHelper(country, vehicleCategory, vehicles, corrections, configurations);
			TVehicles tVehicles = transService.transformVehicles(helper);
					

			if (tVehicles.getVehicleList().isEmpty()) {
				response = "{\"code\":401, \"text\":\"No rules found for " + country + "/" + vehicleCategory + "\"}";
			} else {
				response = "{\"code\":200, \"text\":\"Successful import\"}";
			}

			return Response.status(200).entity(response).build();
		} else {

			response = "{\"code\":400, \"text\":\"Invalid input\"}";
			return Response.status(400).entity(response).build();
		}
	}
}
