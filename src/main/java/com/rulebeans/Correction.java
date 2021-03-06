package com.rulebeans;

/**
 * This class is an extension of the Rule class, and has fields like language, type, code and value,
 * which are pulled out from the "translations_corrections" named table from the MySQL database.
 * The "CorrectionRules" named query extract all correction rules that will be applied on the Vehicles.
 * @author sandor.naghi
 */
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "translations_corrections")
@NamedQueries({
		@NamedQuery(name = "CorrectionRules", query = "SELECT c FROM Correction c WHERE c.country = :country AND c.vehicleCategory = :vehicleCategory AND c.language = :language"),
		@NamedQuery(name = "ContextHasRules", query = "SELECT DISTINCT c.language FROM Correction c "
				+ "WHERE c.country = :country AND c.vehicleCategory = :vehicleCategory AND c.language = :language") })
public class Correction extends Rule {

	private String language;
	private String type;
	private String code;
	private String value;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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
		return "Corrections [id=" + getId() + ", country=" + getCountry() + ", vehicleCategory=" + getVehicleCategory()
				+ ", language=" + language + ", type=" + type + ", code=" + code + ", value=" + value + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Correction other = (Correction) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}