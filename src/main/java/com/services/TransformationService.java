package com.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.rulebeans.Configuration;
import com.rulebeans.Correction;
import com.vehiclebeans.Vehicle;
//import com.vehiclebeans.Equipment;
//import com.vehiclebeans.Paint;
//import com.vehiclebeans.Vehicle;
import com.vehiclebeans.Vehicles;

public class TransformationService {

	// private Vehicles applyCorrections(Vehicles vseVehicles, List<Correction>
	// corrections) {
	//
	// for (Vehicle vehicle : vseVehicles.getVehicleList()) {
	//
	// for (Correction correction : corrections) {
	//
	// switch (correction.getType()) {
	// // case "PRICE_INFORMATION":
	// // if
	// //
	// (vehicle.getPriceInformation().getCurrency().equals(correction.getCode()))
	// // {
	// //
	// vehicle.getPriceInformation().setBasePrice(Long.parseLong(correction.getValue()));
	// // }
	// // break;
	// case "BODY_TYPE":
	// if (vehicle.getBodyType().equals(correction.getCode())) {
	// vehicle.setBodyType(correction.getValue());
	// }
	// break;
	// case "PAINT":
	// Paint paint = vehicle.getPaint();
	// if (paint.getCode().equals(correction.getCode())) {
	// paint.setCode(correction.getValue());
	// }
	// vehicle.setPaint(paint);
	// break;
	// case "PAINT_GROUP":
	// Paint paintCode = vehicle.getPaint();
	// if (paintCode.getGroupCode().equals(correction.getCode())) {
	// paintCode.setGroupCode(correction.getValue());
	// }
	// vehicle.setPaint(paintCode);
	// break;
	// case "FUEL_TYPE":
	// if (vehicle.getFuelType().equals(correction.getCode())) {
	// vehicle.setFuelType(correction.getValue());
	// }
	// break;
	// case "TRANSMISSION":
	// if (vehicle.getTransmission().equals(correction.getCode())) {
	// vehicle.setTransmission(correction.getValue());
	// }
	// break;
	// case "VEHICLE_LOCATION":
	//
	// break;
	// case "EQUIPMENT":
	// List<Equipment> equipments = new ArrayList<>();
	// for (Equipment equipment : vehicle.getEquipmentList()) {
	// if (equipment.getCode().equals(correction.getCode())) {
	// equipment.setDescription(correction.getValue());
	// }
	// equipments.add(equipment);
	// }
	// vehicle.setEquipmentList(equipments);
	// break;
	// default:
	// break;
	// }
	// }
	// }
	//
	// return vseVehicles;
	// }

	public List<Vehicles> setLanguages(Vehicles vseVehicles, List<Configuration> configurations) {

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

		// create a list of vehicles
		List<Vehicles> vehiclesList = new ArrayList<>();

		// List<Vehicles> vehiclesList = new ArrayList<>();
		// populate the list
		for (String s : languageList) {
			Vehicles newVehicles = new Vehicles();
			newVehicles.setCountryCode(vseVehicles.getCountryCode());
			newVehicles.setVehicleCategory(vseVehicles.getVehicleCategory());
			newVehicles.setLanguage(s);
			newVehicles.setVehicleList(vseVehicles.getVehicleList());
			
			vehiclesList.add(newVehicles);
		}

		return vehiclesList;
	}

	
	public List<Vehicles> applyRules(List<Vehicles> configuredVehicles, List<Correction> corrections) {
		Set<String> correctionLanguages = setCorrectionLanguages(corrections);

		List<Vehicles> finalVehicles = new ArrayList<>();

		for (Vehicles vehicles : configuredVehicles) {

			if (!correctionLanguages.contains(vehicles.getLanguage())) {

				finalVehicles.add(vehicles);
			} else {
				
				// cand aplic acest apply imi modifica toate vehicolele nu numai cele cu acest language...
				
				apply(vehicles, corrections);
				
				finalVehicles.add(vehicles);
			}

		}

		return finalVehicles;
	}

	private Vehicles apply(Vehicles vehicles, List<Correction> corrections) {
		Vehicles v = vehicles;

		for (Vehicle vehicle : v.getVehicleList()) {
			for (Correction correction : corrections) {
				switch (correction.getType()) {
				case "TRANSMISSION":
					if (vehicle.getTransmission().equals(correction.getCode())) {
						vehicle.setTransmission(correction.getValue());
					}
					break;
				}
			}
		}

		return v;
	}

	private Set<String> setCorrectionLanguages(List<Correction> corrections) {
		Set<String> s = new HashSet<>();
		for (Correction c : corrections) {
			s.add(c.getLanguage());
		}
		return s;
	}

}
