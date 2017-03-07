package com.elasticsearch;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Context {

	@JsonProperty
	private String country;

	@JsonProperty
	private String vehicleCategory;

	@JsonProperty
	private String language;

	public Context() {

	}

	public Context(String country, String vehicleCategory, String language) {
		super();
		this.country = country;
		this.vehicleCategory = vehicleCategory;
		this.language = language;
	}

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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((vehicleCategory == null) ? 0 : vehicleCategory.hashCode());
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
		Context other = (Context) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
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
		return true;
	}

}
