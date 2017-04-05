package com.esfacets;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.transformedvehicles.TVehicle;

public class FacetResponse {

	@JsonProperty
	private int totalVehicles;

	private List<ElasticFacetsAndTerms> termsList;

	@JsonProperty
	private int vehicleNumWithinPrices;

	@JsonProperty
	private int vehicleNumWithinDates;

	@JsonProperty
	private List<TVehicle> tVehicleList;

	public int getTotalVehicles() {
		return totalVehicles;
	}

	public void setTotalVehicles(int totalVehicles) {
		this.totalVehicles = totalVehicles;
	}

	public List<ElasticFacetsAndTerms> getTermsList() {
		return termsList;
	}

	public void setTermsList(List<ElasticFacetsAndTerms> termsList) {
		this.termsList = termsList;
	}

	public int getVehicleNumWithinPrices() {
		return vehicleNumWithinPrices;
	}

	public void setVehicleNumWithinPrices(int vehicleNumWithinPrices) {
		this.vehicleNumWithinPrices = vehicleNumWithinPrices;
	}

	public int getVehicleNumWithinDates() {
		return vehicleNumWithinDates;
	}

	public void setVehicleNumWithinDates(int vehicleNumWithinDates) {
		this.vehicleNumWithinDates = vehicleNumWithinDates;
	}

	public List<TVehicle> gettVehicleList() {
		return tVehicleList;
	}

	public void settVehicleList(List<TVehicle> tVehicleList) {
		this.tVehicleList = tVehicleList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tVehicleList == null) ? 0 : tVehicleList.hashCode());
		result = prime * result + ((termsList == null) ? 0 : termsList.hashCode());
		result = prime * result + totalVehicles;
		result = prime * result + vehicleNumWithinDates;
		result = prime * result + vehicleNumWithinPrices;
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
		if (tVehicleList == null) {
			if (other.tVehicleList != null)
				return false;
		} else if (!tVehicleList.equals(other.tVehicleList))
			return false;
		if (termsList == null) {
			if (other.termsList != null)
				return false;
		} else if (!termsList.equals(other.termsList))
			return false;
		if (totalVehicles != other.totalVehicles)
			return false;
		if (vehicleNumWithinDates != other.vehicleNumWithinDates)
			return false;
		if (vehicleNumWithinPrices != other.vehicleNumWithinPrices)
			return false;
		return true;
	}
}