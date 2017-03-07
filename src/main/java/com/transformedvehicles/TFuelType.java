package com.transformedvehicles;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TFuelType {

	@JsonProperty
	private String fuelTypeCode;

	@JsonProperty
	private String fuelTypeDescription;

	public TFuelType() {

	}

	public TFuelType(String fuelTypeCode) {
		this.fuelTypeCode = fuelTypeCode;
	}

	public String getFuelTypeCode() {
		return fuelTypeCode;
	}

	public void setFuelTypeCode(String fuelTypeCode) {
		this.fuelTypeCode = fuelTypeCode;
	}

	public String getFuelTypeDescription() {
		return fuelTypeDescription;
	}

	public void setFuelTypeDescription(String fuelTypeDescription) {
		this.fuelTypeDescription = fuelTypeDescription;
	}

	@Override
	public String toString() {
		return "TFuelType [fuelTypeCode=" + fuelTypeCode + ", fuelTypeDescription=" + fuelTypeDescription + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fuelTypeCode == null) ? 0 : fuelTypeCode.hashCode());
		result = prime * result + ((fuelTypeDescription == null) ? 0 : fuelTypeDescription.hashCode());
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
		TFuelType other = (TFuelType) obj;
		if (fuelTypeCode == null) {
			if (other.fuelTypeCode != null)
				return false;
		} else if (!fuelTypeCode.equals(other.fuelTypeCode))
			return false;
		if (fuelTypeDescription == null) {
			if (other.fuelTypeDescription != null)
				return false;
		} else if (!fuelTypeDescription.equals(other.fuelTypeDescription))
			return false;
		return true;
	}
}
