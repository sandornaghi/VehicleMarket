package com.transformedvehicles;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vsevehiclebeans.VehicleLocation;

public class TVehicleLocation {

	@JsonProperty
	private String postalCode;

	private TLocation location;

	public TVehicleLocation() {

	}

	public TVehicleLocation(VehicleLocation vehicleLocation) {
		this.postalCode = vehicleLocation.getPostalCode();
		this.location = new TLocation(vehicleLocation.getLocation());
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public TLocation getLocation() {
		return location;
	}

	public void setLocation(TLocation location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "TVehicleLocation [postalCode=" + postalCode + ", location=" + location + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
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
		TVehicleLocation other = (TVehicleLocation) obj;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (postalCode == null) {
			if (other.postalCode != null)
				return false;
		} else if (!postalCode.equals(other.postalCode))
			return false;
		return true;
	}
}