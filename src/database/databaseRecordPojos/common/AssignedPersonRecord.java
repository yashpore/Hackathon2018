package database.databaseRecordPojos.common;

public class AssignedPersonRecord {

	int id;
	int personID;
	
	int lobID;
	int productID;
	int functionID;
	int uniqueErrorID;
	
	
	boolean call;
	boolean text;
	boolean email;
	boolean other;
	
	int thresholdLasTenMinutes;
	int thresholdLastThirtyMinutes;
	int thresholdLastHour;
	int thresholdLastEightHours;
	int thresholdLastTwentyFourHours;
	int thresholdLastSevenDays;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPersonID() {
		return personID;
	}
	public void setPersonID(int personID) {
		this.personID = personID;
	}

	public int getLobID() {
		return lobID;
	}
	public void setLobID(int lobID) {
		this.lobID = lobID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getFunctionID() {
		return functionID;
	}
	public void setFunctionID(int functionID) {
		this.functionID = functionID;
	}
	public int getUniqueErrorID() {
		return uniqueErrorID;
	}
	public void setUniqueErrorID(int uniqueErrorID) {
		this.uniqueErrorID = uniqueErrorID;
	}
	public boolean isCall() {
		return call;
	}
	public void setCall(boolean call) {
		this.call = call;
	}
	public boolean isText() {
		return text;
	}
	public void setText(boolean text) {
		this.text = text;
	}
	public boolean isEmail() {
		return email;
	}
	public void setEmail(boolean email) {
		this.email = email;
	}
	public boolean isOther() {
		return other;
	}
	public void setOther(boolean other) {
		this.other = other;
	}
	public int getThresholdLasTenMinutes() {
		return thresholdLasTenMinutes;
	}
	public void setThresholdLasTenMinutes(int thresholdLasTenMinutes) {
		this.thresholdLasTenMinutes = thresholdLasTenMinutes;
	}
	public int getThresholdLastThirtyMinutes() {
		return thresholdLastThirtyMinutes;
	}
	public void setThresholdLastThirtyMinutes(int thresholdLastThirtyMinutes) {
		this.thresholdLastThirtyMinutes = thresholdLastThirtyMinutes;
	}
	public int getThresholdLastHour() {
		return thresholdLastHour;
	}
	public void setThresholdLastHour(int thresholdLastHour) {
		this.thresholdLastHour = thresholdLastHour;
	}
	public int getThresholdLastEightHours() {
		return thresholdLastEightHours;
	}
	public void setThresholdLastEightHours(int thresholdLastEightHours) {
		this.thresholdLastEightHours = thresholdLastEightHours;
	}
	public int getThresholdLastTwentyFourHours() {
		return thresholdLastTwentyFourHours;
	}
	public void setThresholdLastTwentyFourHours(int thresholdLastTwentyFourHours) {
		this.thresholdLastTwentyFourHours = thresholdLastTwentyFourHours;
	}
	public int getThresholdLastSevenDays() {
		return thresholdLastSevenDays;
	}
	public void setThresholdLastSevenDays(int thresholdLastSevenDays) {
		this.thresholdLastSevenDays = thresholdLastSevenDays;
	}
	
	

}
