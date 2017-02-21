package com.help;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

import com.annotations.NotEmpty;
import com.rulebeans.Configurations;
import com.services.RuleService;
import com.services.VSEService;
import com.vehiclebeans.Vehicles;

@Path("/")
public class VehicleServiceTest {

	private static final Logger LOGGER = Logger.getLogger(VehicleServiceTest.class.getName());

	@Inject
	VSEService vseService;

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

		Configurations sysConf = new Configurations();
		sysConf.setId(3);
		sysConf.setCountry("de");
		sysConf.setVehicleCategory("new");
		sysConf.setCode("acceptedLanguages");
		sysConf.setValue("hu");
		
		RuleService rs = new RuleService();
		
		rs.persistRule(sysConf);
				
		if (vehicles == null) {
			return Response.ok("Invalid parameters!!").build();
		} else {
			return Response.ok(vehicles).build();
		}

	}
}
