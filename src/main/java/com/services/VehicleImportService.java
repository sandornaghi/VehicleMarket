package com.services;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.response.ResponseCodeAndDescription;

/**
 * This class is used for REST Service, get the country and the category from
 * the front, through the path. It produces a json object for response.
 * 
 * @author sandor.naghi
 */
@Path("/importvehicle")
public class VehicleImportService {

	@Inject
	private ImportService importService;

	@GET
	@Path("/{country}/{vehicleCategory}")
	@Produces("application/json")
	public Response importVehiclesFromVSE(@PathParam("country") String country,
			@PathParam("vehicleCategory") String vehicleCategory) {

		ResponseCodeAndDescription response = importService.importVseVehicle(country, vehicleCategory);

		return Response.status(200).entity(response).build();
	}
}
