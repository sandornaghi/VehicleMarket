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
import com.rulebeans.Configuration;
import com.rulebeans.Correction;
import com.services.RuleService;
import com.services.TransformationService;
import com.services.VSEService;
import com.vehiclebeans.Vehicles;

@Path("/vehicle")
public class VehicleMarket {

	private static final Logger LOGGER = Logger.getLogger(VehicleMarket.class.getName());

	@Inject
	private VSEService vseService;

	@Inject
	private RuleService ruleService;

	private TransformationService transService = new TransformationService();

	@GET
	@Path("/{country}/{vehicleCategory}")
	@Produces("application/xml")
	public Response getVehicles(@PathParam("country") @NotEmpty String country,
			@PathParam("vehicleCategory") @NotEmpty String vehicleCategory) {

		Vehicles vehicles = null;

		try {
			vehicles = vseService.getVehiclesFromVSE(country, vehicleCategory);
		} catch (JAXBException e) {
			LOGGER.severe(e.getMessage());
		}

		if (vehicles != null) {
			List<Correction> corrections = ruleService.getCorrectionRules(country, vehicleCategory);
			List<Configuration> configurations = ruleService.getConfigurationRules(country, vehicleCategory);
			
			vehicles = transService.applyCorrections(vehicles, corrections);
			vehicles = transService.applyConfiguartions(vehicles, configurations);

			return Response.ok(vehicles).build();
		} else {

			return Response.ok("Invalid parameters!!").build();
		}

	}
}
