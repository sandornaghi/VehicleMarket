package com.esfacets.input;

public class UserFacet {

	private PriceInformation priceInformation;

	private FirstRegistrationDate firstRegistrationDate;

	public PriceInformation getPriceInformation() {
		return priceInformation;
	}

	public void setPriceInformation(PriceInformation priceInformation) {
		this.priceInformation = priceInformation;
	}

	public FirstRegistrationDate getFirstRegistrationDate() {
		return firstRegistrationDate;
	}

	public void setFirstRegistrationDate(FirstRegistrationDate firstRegistrationDate) {
		this.firstRegistrationDate = firstRegistrationDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstRegistrationDate == null) ? 0 : firstRegistrationDate.hashCode());
		result = prime * result + ((priceInformation == null) ? 0 : priceInformation.hashCode());
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
		UserFacet other = (UserFacet) obj;
		if (firstRegistrationDate == null) {
			if (other.firstRegistrationDate != null)
				return false;
		} else if (!firstRegistrationDate.equals(other.firstRegistrationDate))
			return false;
		if (priceInformation == null) {
			if (other.priceInformation != null)
				return false;
		} else if (!priceInformation.equals(other.priceInformation))
			return false;
		return true;
	}
}