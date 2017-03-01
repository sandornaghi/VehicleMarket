package com.transformedvehicles;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TVehicle {

	@JsonProperty
	private String id;

	@JsonProperty
	private String language;

	@JsonIgnoreProperties(ignoreUnknown = true)
	private TPrice priceInformation;

	@JsonIgnoreProperties(ignoreUnknown = true)
	private TBodyType bodyType;

	@JsonIgnoreProperties(ignoreUnknown = true)
	private TPaint paint;

	@JsonIgnoreProperties(ignoreUnknown = true)
	private TFuelType fuelType;

	@JsonIgnoreProperties(ignoreUnknown = true)
	private TTransmission transmission;

	@JsonIgnoreProperties(ignoreUnknown = true)
	private TVehicleLocation vehicleLocation;

	@JsonIgnoreProperties(ignoreUnknown = true)
	private List<TEquipment> equipmentList;

	@JsonProperty
	private String firstRegistrationDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public TPrice getPriceInformation() {
		return priceInformation;
	}

	public void setPriceInformation(TPrice priceInformation) {
		this.priceInformation = priceInformation;
	}

	public TBodyType getBodyType() {
		return bodyType;
	}

	public void setBodyType(TBodyType bodyType) {
		this.bodyType = bodyType;
	}

	public TPaint getPaint() {
		return paint;
	}

	public void setPaint(TPaint paint) {
		this.paint = paint;
	}

	public TFuelType getFuelType() {
		return fuelType;
	}

	public void setFuelType(TFuelType fuelType) {
		this.fuelType = fuelType;
	}

	public TTransmission getTransmission() {
		return transmission;
	}

	public void setTransmission(TTransmission transmission) {
		this.transmission = transmission;
	}

	public TVehicleLocation getVehicleLocation() {
		return vehicleLocation;
	}

	public void setVehicleLocation(TVehicleLocation vehicleLocation) {
		this.vehicleLocation = vehicleLocation;
	}

	public List<TEquipment> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<TEquipment> equipmentList) {
		this.equipmentList = equipmentList;
	}

	public String getFirstRegistrationDate() {
		return firstRegistrationDate;
	}

	public void setFirstRegistrationDate(String firstRegistrationDate) {
		this.firstRegistrationDate = firstRegistrationDate;
	}

	@Override
	public String toString() {
		return "TVehicle [id=" + id + ", language=" + language + ", priceInformation=" + priceInformation
				+ ", bodyType=" + bodyType + ", paint=" + paint + ", fuelType=" + fuelType + ", transmission="
				+ transmission + ", vehicleLocation=" + vehicleLocation + ", equipmentList=" + equipmentList
				+ ", firstRegistrationDate=" + firstRegistrationDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bodyType == null) ? 0 : bodyType.hashCode());
		result = prime * result + ((equipmentList == null) ? 0 : equipmentList.hashCode());
		result = prime * result + ((firstRegistrationDate == null) ? 0 : firstRegistrationDate.hashCode());
		result = prime * result + ((fuelType == null) ? 0 : fuelType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((paint == null) ? 0 : paint.hashCode());
		result = prime * result + ((priceInformation == null) ? 0 : priceInformation.hashCode());
		result = prime * result + ((transmission == null) ? 0 : transmission.hashCode());
		result = prime * result + ((vehicleLocation == null) ? 0 : vehicleLocation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TVehicle other = (TVehicle) obj;
		if (bodyType == null) {
			if (other.bodyType != null)
				return false;
		} else if (!bodyType.equals(other.bodyType))
			return false;
		if (equipmentList == null) {
			if (other.equipmentList != null)
				return false;
		} else if (!equipmentList.equals(other.equipmentList))
			return false;
		if (firstRegistrationDate == null) {
			if (other.firstRegistrationDate != null)
				return false;
		} else if (!firstRegistrationDate.equals(other.firstRegistrationDate))
			return false;
		if (fuelType == null) {
			if (other.fuelType != null)
				return false;
		} else if (!fuelType.equals(other.fuelType))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (paint == null) {
			if (other.paint != null)
				return false;
		} else if (!paint.equals(other.paint))
			return false;
		if (priceInformation == null) {
			if (other.priceInformation != null)
				return false;
		} else if (!priceInformation.equals(other.priceInformation))
			return false;
		if (transmission == null) {
			if (other.transmission != null)
				return false;
		} else if (!transmission.equals(other.transmission))
			return false;
		if (vehicleLocation == null) {
			if (other.vehicleLocation != null)
				return false;
		} else if (!vehicleLocation.equals(other.vehicleLocation))
			return false;
		return true;
	}

}
