package com.transformedvehicles;

import java.util.List;

public class TVehicles {

	private String country;

	private String vehicleCategory;

	private List<TLanguageAndVehicles> vehicleList;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getVehicleCategory() {
		return vehicleCategory;
	}

	public void setVehicleCategory(String vehicleCategory) {
		this.vehicleCategory = vehicleCategory;
	}

	public List<TLanguageAndVehicles> getVehicleList() {
		return vehicleList;
	}

	public void setVehicleList(List<TLanguageAndVehicles> vehicleList) {
		this.vehicleList = vehicleList;
	}

	@Override
	public String toString() {
		return "TVehicles [country=" + country + ", vehicleCategory=" + vehicleCategory + ", vehicleList=" + vehicleList
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
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
		TVehicles other = (TVehicles) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
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
