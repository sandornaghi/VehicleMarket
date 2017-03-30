package com.vsevehiclebeans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class Vehicle {

	@XmlElement
	private String id;

	@XmlElement
	private Price priceInformation;

	@XmlElement
	private String bodyType;

	@XmlElement
	private Paint paint;

	@XmlElement
	private String fuelType;

	@XmlElement
	private String transmission;

	@XmlElement
	private VehicleLocation vehicleLocation;

	@XmlElementWrapper(name = "vseEquipments")
	@XmlElement(name = "vseEquipment")
	private List<Equipment> equipmentList;

	@XmlElement()
	private String firstRegistrationDate;

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", priceInformation=" + priceInformation + ", bodyType=" + bodyType + ", paint="
				+ paint + ", fuelType=" + fuelType + ", transmission=" + transmission + ", vehicleLocation="
				+ vehicleLocation + ", equipmentList=" + equipmentList + ", firstRegistrationDate="
				+ firstRegistrationDate + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Price getPriceInformation() {
		return priceInformation;
	}

	public void setPriceInformation(Price priceInformation) {
		this.priceInformation = priceInformation;
	}

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}

	public Paint getPaint() {
		return paint;
	}

	public void setPaint(Paint paint) {
		this.paint = paint;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public VehicleLocation getVehicleLocation() {
		return vehicleLocation;
	}

	public void setVehicleLocation(VehicleLocation vehicleLocation) {
		this.vehicleLocation = vehicleLocation;
	}

	public List<Equipment> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(List<Equipment> equipmentList) {
		this.equipmentList = equipmentList;
	}

	public String getFirstRegistrationDate() {
		return firstRegistrationDate;
	}

	public void setFirstRegistrationDate(String firstRegistrationDate) {
		this.firstRegistrationDate = firstRegistrationDate;
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
		Vehicle other = (Vehicle) obj;
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
