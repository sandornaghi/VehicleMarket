package com.rulebeans;

/**
 * This class extends the Rule base class, 
 * and holds the code and the value fields, that is extracted from the MySQL.
 * The "ConfigurationRules" named query pulls out every row from the database table named "sys_config".
 * Here can be found every accepted language for every car category (if has one...).
 * @author sandor.naghi
 */
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "sys_config")
@NamedQueries({
		@NamedQuery(name = "ConfigurationRules", query = " SELECT c FROM Configuration c WHERE c.country = :country AND c.vehicleCategory = :vehicleCategory "),
		@NamedQuery(name = "AcceptedLanguages", query = " SELECT c.value FROM Configuration c WHERE c.country = :country AND c.vehicleCategory = :vehicleCategory AND c.code = 'acceptedLanguages' "),
		@NamedQuery(name = "ImportTimeInterval", query = "SELECT c FROM Configuration c WHERE c.code = 'importTimeInterval' ")
})
public class Configuration extends Rule {

	private String code;
	private String value;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Configurations [id=" + getId() + ", country=" + getCountry() + ", vehicleCategory="
				+ getVehicleCategory() + ", code=" + code + ", value=" + value + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Configuration other = (Configuration) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}
