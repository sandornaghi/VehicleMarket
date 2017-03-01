package com.transformedvehicles;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TPaint {

	@JsonProperty("paintCode")
	private String paintCode;

	@JsonProperty("paintDescription")
	private String paintDescription;

	@JsonProperty("paintGroup_code")
	private String groupCode;
	
	@JsonProperty("paintGroup_description")
	private String groupDescription;

	public TPaint() {

	}

	public TPaint(String paintCode, String groupCode) {
		this.paintCode = paintCode;
		this.groupCode = groupCode;
	}

	public String getPaintCode() {
		return paintCode;
	}

	public void setPaintCode(String paintCode) {
		this.paintCode = paintCode;
	}

	public String getPaintDescription() {
		return paintDescription;
	}

	public void setPaintDescription(String paintDescription) {
		this.paintDescription = paintDescription;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	@Override
	public String toString() {
		return "TPaint [paintCode=" + paintCode + ", paintDescription=" + paintDescription + ", groupCode=" + groupCode
				+ ", groupDescription=" + groupDescription + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((groupCode == null) ? 0 : groupCode.hashCode());
		result = prime * result + ((groupDescription == null) ? 0 : groupDescription.hashCode());
		result = prime * result + ((paintCode == null) ? 0 : paintCode.hashCode());
		result = prime * result + ((paintDescription == null) ? 0 : paintDescription.hashCode());
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
		TPaint other = (TPaint) obj;
		if (groupCode == null) {
			if (other.groupCode != null)
				return false;
		} else if (!groupCode.equals(other.groupCode))
			return false;
		if (groupDescription == null) {
			if (other.groupDescription != null)
				return false;
		} else if (!groupDescription.equals(other.groupDescription))
			return false;
		if (paintCode == null) {
			if (other.paintCode != null)
				return false;
		} else if (!paintCode.equals(other.paintCode))
			return false;
		if (paintDescription == null) {
			if (other.paintDescription != null)
				return false;
		} else if (!paintDescription.equals(other.paintDescription))
			return false;
		return true;
	}

}
