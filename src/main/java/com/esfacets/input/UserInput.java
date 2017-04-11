package com.esfacets.input;

public class UserInput {

	private UserQuery userQuery;

	private UserFacet userFacet;

	public UserQuery getUserQuery() {
		return userQuery;
	}

	public void setUserQuery(UserQuery userQuery) {
		this.userQuery = userQuery;
	}

	public UserFacet getUserFacet() {
		return userFacet;
	}

	public void setUserFacet(UserFacet userFacet) {
		this.userFacet = userFacet;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userFacet == null) ? 0 : userFacet.hashCode());
		result = prime * result + ((userQuery == null) ? 0 : userQuery.hashCode());
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
		UserInput other = (UserInput) obj;
		if (userFacet == null) {
			if (other.userFacet != null)
				return false;
		} else if (!userFacet.equals(other.userFacet))
			return false;
		if (userQuery == null) {
			if (other.userQuery != null)
				return false;
		} else if (!userQuery.equals(other.userQuery))
			return false;
		return true;
	}
}