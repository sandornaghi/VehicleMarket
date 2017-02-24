package com.services;

import java.util.ArrayList;
import java.util.List;

import com.rulebeans.Configuration;
import com.rulebeans.Correction;
import com.vehiclebeans.Equipment;
import com.vehiclebeans.Vehicle;
import com.vehiclebeans.Vehicles;

public class TransformationService {

	public Vehicles applyCorrections(Vehicles vseVehicles, List<Correction> corrections) {

		for (Vehicle vehicle : vseVehicles.getVehicleList()) {

			for (Correction correction : corrections) {

				switch (correction.getType()) {
				case "PRICE_INFORMATION":
					if (vehicle.getPriceInformation().getCurrency().equals(correction.getCode())) {
						vehicle.getPriceInformation().setBasePrice(Long.parseLong(correction.getValue()));
					}
					break;
				case "BODY_TYPE":
					if (vehicle.getBodyType().equals(correction.getCode())) {
						vehicle.setBodyType(correction.getValue());
					}
					break;
				case "PAINT":
					
					break;
				case "FUEL_TYPE":
					if (vehicle.getFuelType().equals(correction.getCode())) {
						vehicle.setFuelType(correction.getValue());
					}
					break;
				case "TRANSMISSION":
					if (vehicle.getTransmission().equals(correction.getCode())) {
						vehicle.setTransmission(correction.getValue());
					}
					break;
				case "VEHICLE_LOCATION":
					
					break;
				case "EQUIPMENT":
					List<Equipment> equipments = new ArrayList<>();
					for (Equipment equipment : vehicle.getEquipmentList()) {
						if (equipment.getCode().equals(correction.getCode())) {
							equipment.setDescription(correction.getValue());
						}
						equipments.add(equipment);
					}
					vehicle.setEquipmentList(equipments);
					break;
				default:
					break;
				}
			}
		}

		return vseVehicles;
	}

	// not implemented yet...
	public Vehicles applyConfiguartions(Vehicles vseVehicles, List<Configuration> configurations) {
		return vseVehicles;
	}
}
