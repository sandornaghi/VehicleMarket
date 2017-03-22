package com.elasticsearchfacets;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ElasticFacetTerms {

	@JsonProperty
	private String value;

	@JsonProperty
	private int vehicleNumbers;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		ElasticFacetTerms other = (ElasticFacetTerms) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		if (vehicleNumbers != other.vehicleNumbers)
			return false;
		return true;
	}
}
