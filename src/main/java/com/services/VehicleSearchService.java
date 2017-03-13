package com.services;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.response.ElasticsearchResponse;

/**
 * This class is used for REST Service, get the country, category and language
 * from the front, through the path. It produces a json object for response.
 * 
 * @author sandor.naghi
 */
@Path("/searchvehicle")
public class VehicleSearchService {

	@Inject
	private SearchService searchService;

	@GET
	@Path("/{country}/{vehicleCategory}/{language}")
	@Produces("application/json")
	public Response searchVehicles(@PathParam("country") String country,
			@PathParam("vehicleCategory") String vehicleCategory, @PathParam("language") String language) {

		ElasticsearchResponse elasticResponse = searchService.searchTVehicles(country, vehicleCategory, language);

		return Response.status(200).entity(elasticResponse).build();
	}
}