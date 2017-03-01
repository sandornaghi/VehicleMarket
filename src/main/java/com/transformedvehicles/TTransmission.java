package com.transformedvehicles;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TTransmission {

	@JsonProperty("transmission_code")
	private String transmissionCode;

	@JsonProperty("transamission_description")
	private String transmissionDescription;

	public TTransmission() {

	}

	public TTransmission(String transmissionCode) {
		this.transmissionCode = transmissionCode;
	}

	public String getTransmissionCode() {
		return transmissionCode;
	}

	public void setTransmissionCode(String transmissionCode) {
		this.transmissionCode = transmissionCode;
	}

	public String getTransmissionDescription() {
		return transmissionDescription;
	}

	public void setTransmissionDescription(String transmissionDescription) {
		this.transmissionDescription = transmissionDescription;
	}

	@Override
	public String toString() {
		return "TTransmission [transmissionCode=" + transmissionCode + ", transmissionDescription="
				+ transmissionDescription + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((transmissionCode == null) ? 0 : transmissionCode.hashCode());
		result = prime * result + ((transmissionDescription == null) ? 0 : transmissionDescription.hashCode());
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
		TTransmission other = (TTransmission) obj;
		if (transmissionCode == null) {
			if (other.transmissionCode != null)
				return false;
		} else if (!transmissionCode.equals(other.transmissionCode))
			return false;
		if (transmissionDescription == null) {
			if (other.transmissionDescription != null)
				return false;
		} else if (!transmissionDescription.equals(other.transmissionDescription))
			return false;
		return true;
	}

}
