package com.response;

public class ResponseCodeAndDescription {

	// response code and description for successful import
	public static final int SUCCESS_IMPORT = 200;
	private static final String SUCCES_IMPORT_TEXT = "Successful import.";

	// response codes and description for VSE system starting with 300
	public static final int VSE_INEXISTENT = 301;
	private static final String VSE_TEXT_INEXISTENT = "Invalid input, inexistent country or vehicle category in the VSE system.";
	public static final int VSE_FAILED = 302;
	private static final String VSE_TEXT_FAILED = "Import vehicles from VSE system failed.";
	public static final int VSE_NO_RULES = 303;
	private static final String VSE_TEXT_NO_RULES = "Inexistent rules for this context (country and vehicle category).";

	// response code and description for Elasticsearch operation
	public static final int ELASTIC_DUPLICATE_INDEX = 401;
	private static final String ELASTIC_TEXT_DUPLICATE_INDEX = "Insertion to Elasticsearch failed due the existence of same index.";
	public static final int ELASTIC_INTSERTION_ERROR = 402;
	private static final String ELASTIC_TEXT_INSERTION_ERROR = "Insertion to Elasticsearch failed due an internal error.";

	// response code and description for some internal error...
	public static final int INTERNAL_ERROR = 501;
	private static final String INTERNAL_ERROR_TEXT = "Internal error.";

	// response code and description for file reading errors
	public static final int FILE_NOT_FOUND = 601;
	private static final String FILE_NOT_FOUND_TEXT = "File for this context not found.";

	private int code;
	private String text;

	public ResponseCodeAndDescription() {

	}

	public ResponseCodeAndDescription(int code) {

		switch (code) {
		case SUCCESS_IMPORT:
			this.code = SUCCESS_IMPORT;
			this.text = SUCCES_IMPORT_TEXT;
			break;

		case VSE_INEXISTENT:
			this.code = VSE_INEXISTENT;
			this.text = VSE_TEXT_INEXISTENT;
			break;

		case VSE_FAILED:
			this.code = VSE_FAILED;
			this.text = VSE_TEXT_FAILED;
			break;

		case VSE_NO_RULES:
			this.code = VSE_NO_RULES;
			this.text = VSE_TEXT_NO_RULES;
			break;

		case ELASTIC_DUPLICATE_INDEX:
			this.code = ELASTIC_DUPLICATE_INDEX;
			this.text = ELASTIC_TEXT_DUPLICATE_INDEX;
			break;

		case ELASTIC_INTSERTION_ERROR:
			this.code = ELASTIC_INTSERTION_ERROR;
			this.text = ELASTIC_TEXT_INSERTION_ERROR;
			break;

		case FILE_NOT_FOUND:
			this.code = FILE_NOT_FOUND;
			this.text = FILE_NOT_FOUND_TEXT;
			break;

		default:
			this.code = INTERNAL_ERROR;
			this.text = INTERNAL_ERROR_TEXT;
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
