package com.elasticsearchfacets;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ElasticFacetWithPriceResponse {

	@JsonProperty
	private long vehicleNumbers;

	public long getVehicleNumbers() {
		return vehicleNumbers;
	}

	public void setVehicleNumbers(long vehicleNumbers) {
		this.vehicleNumbers = vehicleNumbers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (vehicleNumbers ^ (vehicleNumbers >>> 32));
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
		ElasticFacetWithPriceResponse other = (ElasticFacetWithPriceResponse) obj;
		if (vehicleNumbers != other.vehicleNumbers)
			return false;
		return true;
	}
}
