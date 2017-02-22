package com.help;

import com.rulebeans.Configurations;
import com.services.RuleService;

public class RuleTest {

	public static void main(String[] args) {
		Configurations sysConf = new Configurations();
		sysConf.setId(4);
		sysConf.setCountry("de");
		sysConf.setVehicleCategory("new");
		sysConf.setCode("acceptedLanguages");
		sysConf.setValue("ro");
		
		RuleService rs = new RuleService();
		
		rs.persistRule(sysConf);
	}
}
