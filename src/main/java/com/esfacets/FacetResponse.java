package com.esfacets;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FacetResponse {

	private List<ElasticFacetsAndTerms> termsList;
	
	@JsonProperty
	private int vehicleNumbers;

	@JsonProperty
	private double minPrice;

	@JsonProperty
	private double maxPrice;

	public List<ElasticFacetsAndTerms> getTermsList() {
		return termsList;
	}

	public void setTermsList(List<ElasticFacetsAndTerms> termsList) {
		this.termsList = termsList;
	}

	public int getVehicleNumbers() {
		return vehicleNumbers;
	}

	public void setVehicleNumbers(int vehicleNumbers) {
		this.vehicleNumbers = vehicleNumbers;
	}

	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(maxPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(minPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((termsList == null) ? 0 : termsList.hashCode());
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
		FacetResponse other = (FacetResponse) obj;
		if (Double.doubleToLongBits(maxPrice) != Double.doubleToLongBits(other.maxPrice))
			return false;
		if (Double.doubleToLongBits(minPrice) != Double.doubleToLongBits(other.minPrice))
			return false;
		if (termsList == null) {
			if (other.termsList != null)
				return false;
		} else if (!termsList.equals(other.termsList))
			return false;
		if (vehicleNumbers != other.vehicleNumbers)
			return false;
		return true;
	}
}