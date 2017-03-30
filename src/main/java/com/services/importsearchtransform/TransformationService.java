package com.services.importsearchtransform;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.rulebeans.Correction;
import com.services.RuleService;
import com.transformedvehicles.TBodyType;
import com.transformedvehicles.TEquipment;
import com.transformedvehicles.TFuelType;
import com.transformedvehicles.TPaint;
import com.transformedvehicles.TPrice;
import com.transformedvehicles.TTransmission;
import com.transformedvehicles.TVehicle;
import com.transformedvehicles.TVehicleLocation;
import com.vsevehiclebeans.Equipment;
import com.vsevehiclebeans.Vehicle;
import com.vsevehiclebeans.Vehicles;

/**
 * Sets up in which languages can be found information about the Vehicle.
 * Transform the vehicles, apply the rules from the Mysql database.
 */
public class TransformationService {

	@Inject
	private RuleService ruleService;

	/**
	 * Set up the country, vehicle category and the languages for the Vehicles.
	 * 
	 * @param country
	 *            From which market are we import.
	 * @param vehicleCategory
	 *            Category of the Vehicle, new or used.
	 * @param vehicles
	 *            The vehicles that are transformed.
	 * @return List of transformed vehicles, with language setup, and rules
	 *         applied.
	 */
	public List<TVehicle> transformVehicles(String country, String vehicleCategory, Vehicles vehicles) {

		List<TVehicle> tVehicleList = new ArrayList<>();

		List<String> acceptedLanguages = ruleService.acceptedLanguages(country, vehicleCategory);

		if (!acceptedLanguages.isEmpty()) {
			for (String language : acceptedLanguages) {
				tVehicleList.addAll(applyChanges(vehicles, language, country, vehicleCategory));
			}
		}

		return tVehicleList;
	}

	private List<TVehicle> applyChanges(Vehicles vseVehicles, String language, String country, String vehicleCategory) {

		List<TVehicle> tVehicleList = new ArrayList<>();
		for (Vehicle vehicle : vseVehicles.getVehicleList()) {

			TVehicle tVehicle = transformSingleVehicle(vehicle);
			tVehicle.setLanguage(language);

			if (ruleService.hasRules(country, vehicleCategory, tVehicle.getLanguage())) {
				setRules(tVehicle, ruleService.getCorrectionRules(country, vehicleCategory, tVehicle.getLanguage()));
			}

			tVehicleList.add(tVehicle);
		}

		return tVehicleList;

	}

	private TVehicle transformSingleVehicle(Vehicle vehicle) {

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

		return tVehicle;
	}

	private void setRules(TVehicle tVehicle, List<Correction> corrections) {

		for (Correction correction : corrections) {
			switch (correction.getType().toUpperCase()) {
			case "PRICE_INFORMATION":
				TPrice tPrice = new TPrice();
				tPrice.setCurrency(tVehicle.getPriceInformation().getCurrency());

				if (tPrice.getCurrency().equalsIgnoreCase(correction.getCode())) {
					tPrice.setBasePrice(Long.parseLong(correction.getValue()));
					tVehicle.setPriceInformation(tPrice);
				}

				break;
			case "BODY_TYPE":
				TBodyType tBody = new TBodyType(tVehicle.getBodyType().getCode());

				if (tBody.getCode().equalsIgnoreCase(correction.getCode())) {
					tBody.setDescription(correction.getValue());

					tVehicle.setBodyType(tBody);
				}

				break;
			case "PAINT":
				TPaint tPaint = tVehicle.getPaint();

				if (tPaint.getPaintCode().equalsIgnoreCase(correction.getCode())) {
					tPaint.setPaintDescription(correction.getValue());

					tVehicle.setPaint(tPaint);
				}

				break;
			case "PAINT_GROUP":
				TPaint tPaintGroup = tVehicle.getPaint();

				if (tPaintGroup.getGroupCode().equalsIgnoreCase(correction.getCode())) {
					tPaintGroup.setGroupDescription(correction.getValue());

					tVehicle.setPaint(tPaintGroup);
				}

				break;
			case "FUEL_TYPE":
				TFuelType tFuel = new TFuelType(tVehicle.getFuelType().getFuelTypeCode());

				if (tFuel.getFuelTypeCode().equalsIgnoreCase(correction.getCode())) {
					tFuel.setFuelTypeDescription(correction.getValue());
					tVehicle.setFuelType(tFuel);
				}

				break;
			case "TRANSMISSION":
				TTransmission transmission = new TTransmission(tVehicle.getTransmission().getTransmissionCode());

				if (transmission.getTransmissionCode().equalsIgnoreCase(correction.getCode())) {
					transmission.setTransmissionDescription(correction.getValue());
					tVehicle.setTransmission(transmission);
				}

				break;
			case "EQUIPMENT":
				List<TEquipment> equipments = new ArrayList<>();

				for (TEquipment equipment : tVehicle.getEquipmentList()) {
					if (equipment.getCode().equalsIgnoreCase(correction.getCode())) {
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
}
