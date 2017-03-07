package com.services;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.response.ElasticsearchResponse;
import com.response.VSEResponse;

/**
 * This class is used for REST Services, to communicate with the front through
 * json objects.
 * 
 * @author sandor.naghi
 */
@Path("/")
public class VehicleRestService {

	@Inject
	private ImportService importService;
	
	@Inject
	private SearchService searchService;

	@GET
	@Path("importvehicle/{country}/{vehicleCategory}")
	@Produces("application/json")
	public Response importVehiclesFromVSE(@PathParam("country") String country,
			@PathParam("vehicleCategory") String vehicleCategory) {

		VSEResponse response = importService.importVseVehicle(country, vehicleCategory);

		switch (response.getCode()) {
		case 200:
			return Response.status(200).entity(response).build();
		case 401:
			return Response.status(200).entity(response).build();
		default:
			return Response.status(400).entity(response).build();
		}
	}

	@GET
	@Path("searchvehicle/{country}/{vehicleCategory}/{language}")
	@Produces("application/json")
	public Response searchVehicles(@PathParam("country") String country,
			@PathParam("vehicleCategory") String vehicleCategory, @PathParam("language") String language) {

		ElasticsearchResponse elasticResponse = searchService.searchTVehicles(country, vehicleCategory, language);
				
		return Response.status(200).entity(elasticResponse).build();
	}
}
