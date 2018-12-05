package database.databaseRecordPojos.common;

public class ErrorCodeAuditRecord {

	int id;
	int segmentTypeID;
	String code;
	int errorID;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public int getSegmentTypeID() {
		return segmentTypeID;
	}

	public void setSegmentTypeID(int segmentTypeID) {
		this.segmentTypeID = segmentTypeID;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getErrorID() {
		return errorID;
	}

	public void setErrorID(int errorID) {
		this.errorID = errorID;
	}

}
