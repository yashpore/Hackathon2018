package database.databaseRecordPojos.common;

public class RelatedSystemRecord {

	int id;
	String system;
	int uniqueErrorID;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public int getUniqueErrorID() {
		return uniqueErrorID;
	}

	public void setUniqueErrorID(int uniqueErrorID) {
		this.uniqueErrorID = uniqueErrorID;
	}

}
