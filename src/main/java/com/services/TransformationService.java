package com.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rulebeans.Configuration;
import com.rulebeans.Correction;
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

	/**
	 * Transform the vehicle from vse system, into vehicles with the language
	 * configured.
	 * 
	 * @param vseVehicles
	 *            Vehicles from the vse system.
	 * @param configurations
	 *            List of configurations, from where we extract the languages.
	 * @return The transformed vehicles, with the language configured.
	 */
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
		if (configLanguages != null) {
			for (String s : configLanguages) {
				languageList.add(s.trim());
			}
		}

		return languageList;
	}

	/**
	 * Apply the rules for the vehicles with languages configured, rules
	 * extracted from the mysql database.
	 * 
	 * @param configured
	 *            TVehicle Vehicles that will be transformed.
	 * @param corrections
	 *            List of rules from the mysql database.
	 * @return The Vehicle with the rules applied.
	 */
	public TVehicles applyRules(TVehicles configuredTVehicle, List<Correction> corrections) {

		Set<String> correctionLanguages = getLanguages(corrections);

		for (TLanguageAndVehicles langVegicle : configuredTVehicle.getVehicleList()) {

			if (correctionLanguages.contains(langVegicle.getLanguage().toLowerCase())) {
				for (TVehicle tVehicle : langVegicle.gettVehicleList()) {
					setRules(tVehicle, corrections, langVegicle.getLanguage());
				}
			}
		}
		return configuredTVehicle;
	}

	/**
	 * Set the rules on a vehicle
	 * 
	 * @param tVehicle
	 *            TVehicle transformed.
	 * @param corrections
	 *            Correction list from mysql database.
	 */
	private void setRules(TVehicle tVehicle, List<Correction> corrections, String language) {

		for (Correction correction : corrections) {
			switch (correction.getType()) {
			case "PRICE_INFORMATION":
				TPrice tPrice = new TPrice();
				tPrice.setCurrency(tVehicle.getPriceInformation().getCurrency());

				if (tPrice.getCurrency().equalsIgnoreCase(correction.getCode())
						&& correction.getLanguage().equalsIgnoreCase(language)) {
					tPrice.setBasePrice(Long.parseLong(correction.getValue()));
					tVehicle.setPriceInformation(tPrice);
				}

				break;
			case "BODY_TYPE":
				TBodyType tBody = new TBodyType(tVehicle.getBodyType().getCode());

				if (tBody.getCode().equalsIgnoreCase(correction.getCode())
						&& correction.getLanguage().equalsIgnoreCase(language)) {
					tBody.setDescription(correction.getValue());

					tVehicle.setBodyType(tBody);
				}

				break;
			case "PAINT":
				TPaint tPaint = tVehicle.getPaint();

				if (tPaint.getPaintCode().equalsIgnoreCase(correction.getCode())
						&& correction.getLanguage().equalsIgnoreCase(language)) {
					tPaint.setPaintDescription(correction.getValue());

					tVehicle.setPaint(tPaint);
				}

				break;
			case "PAINT_GROUP":
				TPaint tPaintGroup = tVehicle.getPaint();

				if (tPaintGroup.getGroupCode().equalsIgnoreCase(correction.getCode())
						&& correction.getLanguage().equalsIgnoreCase(language)) {
					tPaintGroup.setGroupDescription(correction.getValue());

					tVehicle.setPaint(tPaintGroup);
				}

				break;
			case "FUEL_TYPE":
				TFuelType tFuel = new TFuelType(tVehicle.getFuelType().getFuelTypeCode());

				if (tFuel.getFuelTypeCode().equalsIgnoreCase(correction.getCode())
						&& correction.getLanguage().equalsIgnoreCase(language)) {
					tFuel.setFuelTypeDescription(correction.getValue());
					tVehicle.setFuelType(tFuel);
				}

				break;
			case "TRANSMISSION":
				TTransmission transmission = new TTransmission(tVehicle.getTransmission().getTransmissionCode());

				if (transmission.getTransmissionCode().equalsIgnoreCase(correction.getCode())
						&& correction.getLanguage().equalsIgnoreCase(language)) {
					transmission.setTransmissionDescription(correction.getValue());
					tVehicle.setTransmission(transmission);
				}

				break;
			case "EQUIPMENT":
				List<TEquipment> equipments = new ArrayList<>();

				for (TEquipment equipment : tVehicle.getEquipmentList()) {
					if (equipment.getCode().equalsIgnoreCase(correction.getCode())
							&& correction.getLanguage().equalsIgnoreCase(language)) {
						equipment.setDescription(correction.getValue());

					}
					equipments.add(equipment);
				}

				tVehicle.setEquipmentList(equipments);
				break;
			default:
				break;
			}
		}

	}

	/**
	 * Create a Set of languages extracted from the mysql database.
	 * 
	 * @param corrections
	 *            List of all corrections available in mysql database.
	 * @return A Set of language(s) existing in the correction list.
	 */
	private Set<String> getLanguages(List<Correction> corrections) {

		Set<String> result = new HashSet<>();

		for (Correction correction : corrections) {
			result.add(correction.getLanguage().toLowerCase());
		}

		return result;
	}

}
