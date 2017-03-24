package com.esfacets;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ElasticFacetsAndTerms {

	@JsonProperty
	private String price;

	@JsonProperty
	private int vehicleNumbers;

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getVehicleNumbers() {
		return vehicleNumbers;
	}

	public void setVehicleNumbers(int vehicleNumbers) {
		this.vehicleNumbers = vehicleNumbers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + vehicleNumbers;
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
		ElasticFacetsAndTerms other = (ElasticFacetsAndTerms) obj;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (vehicleNumbers != other.vehicleNumbers)
			return false;
		return true;
	}

}
