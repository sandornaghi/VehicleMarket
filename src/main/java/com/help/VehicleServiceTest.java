package com.help;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.xml.bind.JAXBException;

import com.annotations.NotEmpty;
import com.services.VSEService;
import com.vehiclebeans.Vehicles;

@Path("/")
public class VehicleServiceTest {
	
	private static final Logger LOGGER = Logger.getLogger(VehicleServiceTest.class.getName());
	
	@Inject
	VSEService vseService;
	
	@GET
	@Path("/{country},{vehicleCategory}")
	public String getVehicles(@PathParam("country") @NotEmpty String country, @PathParam("vehicleCategory") @NotEmpty String vehicleCategory) {
		
		Vehicles vehicles = null;
		
		try {
			vehicles = vseService.getVehiclesFromVSE(country, vehicleCategory);
		} catch (JAXBException e) {
			LOGGER.severe(e.getMessage());
		}

		if (vehicles == null) {
			return "Invalid parameters...";
		} else {
			return vehicles.toString();
		}
		
	}
}
