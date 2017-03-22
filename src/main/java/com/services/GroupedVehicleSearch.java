package com.services;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.elasticsearchfacets.ElasticsearchFacetResponse;

/**
 * This class create a response for the front end of the application.
 * The response is a json object.
 * 
 * @author sandor.naghi
 *
 */
@Path("/facets")
public class GroupedVehicleSearch {

	@Inject
	private ElasticsearchVehicleService elasticService;

	@POST
	@Path("{country}/{vehicleCategory}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getFacetReponse(@PathParam("country") String country,
			@PathParam("vehicleCategory") String vehicleCategory, String input) {

		ElasticsearchFacetResponse elasticResponse = elasticService.getFacetsForVehicles(country, vehicleCategory,
				input);

		return Response.status(Status.OK).entity(elasticResponse).build();
	}
}
