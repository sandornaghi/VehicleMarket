package com.services.rest;

import javax.inject.Inject;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.esfacets.FacetResponse;
import com.esfacets.UserInput;
import com.services.ElasticsearchVehicleService;

/**
 * This class create a response for the front end of the application. The
 * response is a json object.
 * 
 * @author sandor.naghi
 *
 */
@Path("/facets")
public class FacetVehicleSearch {

	@Inject
	private ElasticsearchVehicleService elasticService;

	@POST
	@Path("{country}/{vehicleCategory}")
	@Consumes("application/json")
	@Produces("application/json")
	public Response getFacetReponse(@PathParam("country") String country,
			@PathParam("vehicleCategory") String vehicleCategory, UserInput userInput) {

		String alias = country + "_" + vehicleCategory;

		FacetResponse facetResponse = elasticService.getFacetsForVehicles(alias, userInput);

		return Response.status(Status.OK).entity(facetResponse).build();
	}
}