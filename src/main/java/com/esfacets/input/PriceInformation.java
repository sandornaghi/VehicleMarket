package com.esfacets.input;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PriceInformation {

	@JsonProperty
	private long min;

	@JsonProperty
	private long max;

	public long getMin() {
		return min;
	}

	public void setMin(long min) {
		this.min = min;
	}

	public long getMax() {
		return max;
	}

	public void setMax(long max) {
		this.max = max;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (max ^ (max >>> 32));
		result = prime * result + (int) (min ^ (min >>> 32));
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
		PriceInformation other = (PriceInformation) obj;
		if (max != other.max)
			return false;
		if (min != other.min)
			return false;
		return true;
	}
}
