package com.vehiclebeans;

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
	private int bodyType;

	@XmlElement
	private Paint paint;

	@XmlElement
	private int fuelType;

	@XmlElement
	private int transmission;

	@XmlElement
	private VehicleLocation vehicleLocation;

	@XmlElementWrapper(name = "vseEquipments")
	@XmlElement(name = "vseEquipment")
	private List<Equipment> equipmentList;

	@XmlElement
	private int firstRegistrationDate;

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

	public int getBodyType() {
		return bodyType;
	}

	public void setBodyType(int bodyType) {
		this.bodyType = bodyType;
	}

	public Paint getPaint() {
		return paint;
	}

	public void setPaint(Paint paint) {
		this.paint = paint;
	}

	public int getFuelType() {
		return fuelType;
	}

	public void setFuelType(int fuelType) {
		this.fuelType = fuelType;
	}

	public int getTransmission() {
		return transmission;
	}

	public void setTransmission(int transmission) {
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

	public void setEquipmentList(List<Equipment> equipment) {
		this.equipmentList = equipment;
	}

	public int getFirstRegistrationDate() {
		return firstRegistrationDate;
	}

	public void setFirstRegistrationDate(int firstRegistrationDate) {
		this.firstRegistrationDate = firstRegistrationDate;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", priceInformation=" + priceInformation + ", bodyType=" + bodyType + ", paint="
				+ paint + ", fuelType=" + fuelType + ", transmission=" + transmission + ", vehicleLocation="
				+ vehicleLocation + ", equipment=" + equipmentList + ", firstRegistrationDate=" + firstRegistrationDate
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bodyType;
		result = prime * result + ((equipmentList == null) ? 0 : equipmentList.hashCode());
		result = prime * result + firstRegistrationDate;
		result = prime * result + fuelType;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((paint == null) ? 0 : paint.hashCode());
		result = prime * result + ((priceInformation == null) ? 0 : priceInformation.hashCode());
		result = prime * result + transmission;
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
		if (bodyType != other.bodyType)
			return false;
		if (equipmentList == null) {
			if (other.equipmentList != null)
				return false;
		} else if (!equipmentList.equals(other.equipmentList))
			return false;
		if (firstRegistrationDate != other.firstRegistrationDate)
			return false;
		if (fuelType != other.fuelType)
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
		if (transmission != other.transmission)
			return false;
		if (vehicleLocation == null) {
			if (other.vehicleLocation != null)
				return false;
		} else if (!vehicleLocation.equals(other.vehicleLocation))
			return false;
		return true;
	}

}
