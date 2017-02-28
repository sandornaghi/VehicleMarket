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

import com.annotations.NotEmpty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

	@GET
	@Path("/{country}/{vehicleCategory}")
	@Produces("application/json")
	public Response getVehicles(@PathParam("country") @NotEmpty String country,
			@PathParam("vehicleCategory") @NotEmpty String vehicleCategory) {

		Vehicles vehicles = null;

		// read the vehicles from vse system
		try {
			vehicles = vseService.getVehiclesFromVSE(country, vehicleCategory);
		} catch (JAXBException e) {
			LOGGER.severe(e.getMessage());
		}

		// if the vehicles is not empty:
		if (vehicles != null) {
			// get the correction rules for the country and vehicleCategory
			// not used yet...
			List<Correction> corrections = ruleService.getCorrectionRules(country, vehicleCategory);

			// get the configuration rules for the country and vehicleCategory
			List<Configuration> configurations = ruleService.getConfigurationRules(country, vehicleCategory);

			// set the language for this country and vehicleCategoty category
			TVehicles configuredVehicle = transService.setLanguages(vehicles, configurations);

			// transform the object to a JSON object
			ObjectMapper mapper = new ObjectMapper();
			String s = null;
			try {
				s = mapper.writeValueAsString(configuredVehicle);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

			return Response.ok(s).build();
		} else {

			return Response.ok("Invalid parameters!!").build();
		}

	}
}
