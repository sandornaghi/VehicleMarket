package com.elasticsearchfacets;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ElasticsearchFacetResponse {

	@JsonProperty
	private int minPrice;

	@JsonProperty
	private int maxPrice;

	@JsonProperty
	private int vehicleNumbers;

	private List<ElasticFacetTerms> facetList;

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	public int getVehicleNumbers() {
		return vehicleNumbers;
	}

	public void setVehicleNumbers(int vehicleNumbers) {
		this.vehicleNumbers = vehicleNumbers;
	}

	public List<ElasticFacetTerms> getFacetList() {
		return facetList;
	}

	public void setFacetList(List<ElasticFacetTerms> facetList) {
		this.facetList = facetList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((facetList == null) ? 0 : facetList.hashCode());
		result = prime * result + maxPrice;
		result = prime * result + minPrice;
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
		ElasticsearchFacetResponse other = (ElasticsearchFacetResponse) obj;
		if (facetList == null) {
			if (other.facetList != null)
				return false;
		} else if (!facetList.equals(other.facetList))
			return false;
		if (maxPrice != other.maxPrice)
			return false;
		if (minPrice != other.minPrice)
			return false;
		if (vehicleNumbers != other.vehicleNumbers)
			return false;
		return true;
	}
}
