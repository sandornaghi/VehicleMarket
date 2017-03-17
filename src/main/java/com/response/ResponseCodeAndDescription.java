package com.response;

public class ResponseCodeAndDescription {

	// response code and description for successful import
	private static final String SUCCES_IMPORT_DESCRIPTION = "Successful import.";
	public static final int SUCCESS_IMPORT = 200;
	
	// response codes and description for VSE system starting with 300
	private static final String VSE_INEXISTENT_DESCRIPTION = "Invalid input, inexistent country or vehicle category in the VSE system.";
	private static final String VSE_FAILED_DESCRIPTION = "Import vehicles from VSE system failed.";
	private static final String VSE_NO_RULES_DESCRIPTION = "Inexistent rules for this context (country and vehicle category).";
	public static final int VSE_INEXISTENT = 301;
	public static final int VSE_FAILED = 302;
	public static final int VSE_NO_RULES = 303;
	
	// response code and description for Elasticsearch operation
	private static final String ELASTIC_DUPLICATE_INDEX_DESCRIPTION = "Insertion to Elasticsearch failed due the existence of same index.";
	private static final String ELASTIC_INSERTION_ERROR_DESCRIPTION = "Insertion to Elasticsearch failed due an internal error.";
	public static final int ELASTIC_DUPLICATE_INDEX = 401;
	public static final int ELASTIC_INTSERTION_ERROR = 402;
	
	// response code and description for some internal error...
	private static final String INTERNAL_ERROR_DESCRIPTION = "Internal error.";
	public static final int INTERNAL_ERROR = 501;

	// response code and description for file reading errors
	private static final String FILE_NOT_FOUND_DESCRIPTION = "File for this context not found.";
	public static final int FILE_NOT_FOUND = 601;

	private int code;
	private String text;

	public ResponseCodeAndDescription(int code) {

		switch (code) {
		case SUCCESS_IMPORT:
			this.code = SUCCESS_IMPORT;
			this.text = SUCCES_IMPORT_DESCRIPTION;
			break;

		case VSE_INEXISTENT:
			this.code = VSE_INEXISTENT;
			this.text = VSE_INEXISTENT_DESCRIPTION;
			break;

		case VSE_FAILED:
			this.code = VSE_FAILED;
			this.text = VSE_FAILED_DESCRIPTION;
			break;

		case VSE_NO_RULES:
			this.code = VSE_NO_RULES;
			this.text = VSE_NO_RULES_DESCRIPTION;
			break;

		case ELASTIC_DUPLICATE_INDEX:
			this.code = ELASTIC_DUPLICATE_INDEX;
			this.text = ELASTIC_DUPLICATE_INDEX_DESCRIPTION;
			break;

		case ELASTIC_INTSERTION_ERROR:
			this.code = ELASTIC_INTSERTION_ERROR;
			this.text = ELASTIC_INSERTION_ERROR_DESCRIPTION;
			break;

		case FILE_NOT_FOUND:
			this.code = FILE_NOT_FOUND;
			this.text = FILE_NOT_FOUND_DESCRIPTION;
			break;

		default:
			this.code = INTERNAL_ERROR;
			this.text = INTERNAL_ERROR_DESCRIPTION;
		}
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		ResponseCodeAndDescription other = (ResponseCodeAndDescription) obj;
		if (code != other.code)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}
}
