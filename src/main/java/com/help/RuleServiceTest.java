package com.help;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.rulebeans.Configurations;
import com.rulebeans.Corrections;
import com.services.RuleService;

@Path("/rule")
public class RuleServiceTest {

	@Inject
	RuleService ruleService;

	@GET
	public void insertRules() {

		Configurations sysConf = new Configurations();
		sysConf.setCountry("ch");
		sysConf.setVehicleCategory("used");
		sysConf.setCode("acceptedLanguages");
		sysConf.setValue("gr");

		ruleService.persistRule(sysConf);

		Corrections cor = new Corrections();
		cor.setCountry("fr");
		cor.setVehicleCategory("used");
		cor.setLanguage("hu");
		cor.setType("BODY_TYPE");
		cor.setCode("69");
		cor.setValue("bla bla");

		ruleService.persistRule(cor);
	}

	@GET
	@Path("/conf/{country}/{vehicleCategory}")
	public Response getConfigurationsRules(@PathParam("country") String country,
			@PathParam("vehicleCategory") String vehicleCategory) {

		List<Configurations> confList = ruleService.getConfigurationRules(country, vehicleCategory);

		return Response.ok(confList.toString()).build();
	}

	@GET
	@Path("/corr/{country}/{vehicleCategory}")
	public Response getCorrectionsRules(@PathParam("country") String country,
			@PathParam("vehicleCategory") String vehicleCategory) {

		List<Corrections> corrList = ruleService.getCorrectionRules(country, vehicleCategory);

		return Response.ok(corrList.toString()).build();
	}

}
