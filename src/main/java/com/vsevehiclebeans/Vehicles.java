package com.vsevehiclebeans;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "vseVehicles")
@XmlAccessorType(XmlAccessType.FIELD)
public class Vehicles {

	@XmlElement(name = "country")
	private int countryCode;

	@XmlElement
	private String vehicleCategory;

	private String language;

	@XmlElement(name = "vseVehicle")
	private List<Vehicle> vehicleList;

	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}

	public String getVehicleCategory() {
		return vehicleCategory;
	}

	public void setVehicleCategory(String vehicleCategory) {
		this.vehicleCategory = vehicleCategory;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<Vehicle> getVehicleList() {
		return vehicleList;
	}

	public void setVehicleList(List<Vehicle> vehicleList) {
		this.vehicleList = vehicleList;
	}

	@Override
	public String toString() {
		return "Vehicles [countryCode=" + countryCode + ", vehicleCategory=" + vehicleCategory + ", language="
				+ language + ", vehicleList=" + vehicleList + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + countryCode;
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((vehicleCategory == null) ? 0 : vehicleCategory.hashCode());
		result = prime * result + ((vehicleList == null) ? 0 : vehicleList.hashCode());
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
		Vehicles other = (Vehicles) obj;
		if (countryCode != other.countryCode)
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (vehicleCategory == null) {
			if (other.vehicleCategory != null)
				return false;
		} else if (!vehicleCategory.equals(other.vehicleCategory))
			return false;
		if (vehicleList == null) {
			if (other.vehicleList != null)
				return false;
		} else if (!vehicleList.equals(other.vehicleList))
			return false;
		return true;
	}

}