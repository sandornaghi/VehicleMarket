package com.helper;

import java.util.List;

import com.rulebeans.Configuration;
import com.rulebeans.Correction;
import com.vsevehiclebeans.Vehicles;

public class TVehicleHelper {

	private String country;

	private String vehicleCategory;

	private Vehicles vehicles;

	private List<Correction> corrections;

	private List<Configuration> configurations;

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

	public Vehicles getVehicles() {
		return vehicles;
	}

	public void setVehicles(Vehicles vehicles) {
		this.vehicles = vehicles;
	}

	public List<Correction> getCorrections() {
		return corrections;
	}

	public void setCorrections(List<Correction> corrections) {
		this.corrections = corrections;
	}

	public List<Configuration> getConfigurations() {
		return configurations;
	}

	public void setConfigurations(List<Configuration> configurations) {
		this.configurations = configurations;
	}

	public TVehicleHelper(String country, String vehicleCategory, Vehicles vehicles, List<Correction> corrections,
			List<Configuration> configurations) {
		super();
		this.country = country;
		this.vehicleCategory = vehicleCategory;
		this.vehicles = vehicles;
		this.corrections = corrections;
		this.configurations = configurations;
	}

	@Override
	public String toString() {
		return "TVehicleHelper [country=" + country + ", vehicleCategory=" + vehicleCategory + ", vehicles=" + vehicles
				+ ", corrections=" + corrections + ", configurations=" + configurations + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((configurations == null) ? 0 : configurations.hashCode());
		result = prime * result + ((corrections == null) ? 0 : corrections.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((vehicleCategory == null) ? 0 : vehicleCategory.hashCode());
		result = prime * result + ((vehicles == null) ? 0 : vehicles.hashCode());
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
		TVehicleHelper other = (TVehicleHelper) obj;
		if (configurations == null) {
			if (other.configurations != null)
				return false;
		} else if (!configurations.equals(other.configurations))
			return false;
		if (corrections == null) {
			if (other.corrections != null)
				return false;
		} else if (!corrections.equals(other.corrections))
			return false;
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
		if (vehicles == null) {
			if (other.vehicles != null)
				return false;
		} else if (!vehicles.equals(other.vehicles))
			return false;
		return true;
	}

}
