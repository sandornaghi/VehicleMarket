package com.services;

import java.util.ArrayList;
import java.util.List;

import com.rulebeans.Configuration;
import com.transformedvehicles.TBodyType;
import com.transformedvehicles.TEquipment;
import com.transformedvehicles.TFuelType;
import com.transformedvehicles.TLanguageAndVehicles;
import com.transformedvehicles.TPaint;
import com.transformedvehicles.TPrice;
import com.transformedvehicles.TTransmission;
import com.transformedvehicles.TVehicle;
import com.transformedvehicles.TVehicleLocation;
import com.transformedvehicles.TVehicles;
import com.vsevehiclebeans.Equipment;
import com.vsevehiclebeans.Vehicle;
import com.vsevehiclebeans.Vehicles;

public class TransformationService {

	public TVehicles setLanguages(Vehicles vseVehicles, List<Configuration> configurations) {

		List<String> languageList = getRuleLanguages(configurations);

		TVehicles tVehicles = new TVehicles();
		tVehicles.setCountryCode(vseVehicles.getCountryCode());
		tVehicles.setVehicleCategory(vseVehicles.getVehicleCategory());

		List<TLanguageAndVehicles> tLanguageList = new ArrayList<>();

		// populate the list
		for (String s : languageList) {

			TLanguageAndVehicles tLanguage = new TLanguageAndVehicles();
			tLanguage.setLanguage(s);

			List<TVehicle> tVehicleList = new ArrayList<>();
			for (Vehicle vehicle : vseVehicles.getVehicleList()) {
				TVehicle tVehicle = new TVehicle();
				tVehicle.setId(vehicle.getId());
				tVehicle.setPriceInformation(new TPrice(vehicle.getPriceInformation()));

				tVehicle.setBodyType(new TBodyType(vehicle.getBodyType()));
				tVehicle.setPaint(new TPaint(vehicle.getPaint().getCode(), vehicle.getPaint().getGroupCode()));
				tVehicle.setFuelType(new TFuelType(vehicle.getFuelType()));
				tVehicle.setTransmission(new TTransmission(vehicle.getTransmission()));
				tVehicle.setVehicleLocation(new TVehicleLocation(vehicle.getVehicleLocation()));

				List<TEquipment> equipmentList = new ArrayList<>();
				for (Equipment equipment : vehicle.getEquipmentList()) {
					equipmentList.add(new TEquipment(equipment.getCode(), equipment.getDescription()));
				}
				tVehicle.setEquipmentList(equipmentList);

				tVehicle.setFirstRegistrationDate(vehicle.getFirstRegistrationDate());

				tVehicleList.add(tVehicle);
			}

			tLanguage.settVehicleList(tVehicleList);

			tLanguageList.add(tLanguage);
		}

		tVehicles.setVehicleList(tLanguageList);

		return tVehicles;
	}

	/**
	 * Extract the languages from MySql database, and create a Set of all
	 * languages.
	 * 
	 * @param configurations
	 *            List of configurations from where we extract the languages.
	 * @return List of languages.
	 */
	private List<String> getRuleLanguages(List<Configuration> configurations) {

		// building an array of acceptedLanguages
		String[] configLanguages = null;
		for (Configuration configuration : configurations) {
			if (configuration.getCode().equals("acceptedLanguages")) {
				configLanguages = configuration.getValue().split(",");
			}
		}

		// transform the languages in a List, after trimming
		List<String> languageList = new ArrayList<>();
		for (String s : configLanguages) {
			languageList.add(s.trim());
		}

		return languageList;
	}

}
