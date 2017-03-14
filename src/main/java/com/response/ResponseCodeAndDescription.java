package com.response;

public class ResponseCodeAndDescription {

	// response code and description for successful import
	private static final int SUCCESS_IMPORT_CODE = 200;
	private static final String SUCCES_IMPORT_TEXT = "Successful import.";

	// response codes and description for VSE system starting with 300
	private static final int VSE_CODE_INEXISTENT = 301;
	private static final String VSE_TEXT_INEXISTENT = "Invalid input, inexistent country or vehicle category in the VSE system.";
	private static final int VSE_CODE_FAILED = 302;
	private static final String VSE_TEXT_FAILED = "Import vehicles from VSE system failed.";
	private static final int VSE_CODE_NORULES = 303;
	private static final String VSE_TEXT_NORULES = "Inexistent rules for this context (country and vehicle category).";

	// response code and description for Elasticsearch operation
	private static final int ELASTIC_CODE_DUPLICATE_INDEX = 401;
	private static final String ELASTIC_TEXT_DUPLICATE_INDEX = "Insertion to Elasticsearch failed due the existence of same index.";
	private static final int ELASTIC_CODE_INTSERTION_ERROR = 402;
	private static final String ELASTIC_TEXT_INSERTION_ERROR = "Insertion to Elasticsearch failed due an internal error.";

	// response code and description for some internal error...
	private static final int INTERNAL_ERROR_CODE = 501;
	private static final String INTERNAL_ERROR_TEXT = "Internal error.";

	// response code and description for file reading errors
	private static final int FILE_NOT_FOUND_CODE = 601;
	private static final String FILE_NOT_FOUND_TEXT = "File for this context not found.";

	private int code;
	private String text;

	public ResponseCodeAndDescription(int code) {

		switch (code) {
		case SUCCESS_IMPORT_CODE:
			this.code = SUCCESS_IMPORT_CODE;
			this.text = SUCCES_IMPORT_TEXT;
			break;

		case VSE_CODE_INEXISTENT:
			this.code = VSE_CODE_INEXISTENT;
			this.text = VSE_TEXT_INEXISTENT;
			break;

		case VSE_CODE_FAILED:
			this.code = VSE_CODE_FAILED;
			this.text = VSE_TEXT_FAILED;
			break;

		case VSE_CODE_NORULES:
			this.code = VSE_CODE_NORULES;
			this.text = VSE_TEXT_NORULES;
			break;

		case ELASTIC_CODE_DUPLICATE_INDEX:
			this.code = ELASTIC_CODE_DUPLICATE_INDEX;
			this.text = ELASTIC_TEXT_DUPLICATE_INDEX;
			break;

		case ELASTIC_CODE_INTSERTION_ERROR:
			this.code = ELASTIC_CODE_INTSERTION_ERROR;
			this.text = ELASTIC_TEXT_INSERTION_ERROR;
			break;

		case FILE_NOT_FOUND_CODE:
			this.code = FILE_NOT_FOUND_CODE;
			this.text = FILE_NOT_FOUND_TEXT;
			break;

		default:
			this.code = INTERNAL_ERROR_CODE;
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
