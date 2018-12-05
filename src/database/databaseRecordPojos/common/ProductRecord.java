package database.databaseRecordPojos.common;

import java.util.ArrayList;
import java.util.Date;

public class ProductRecord {

	int id;
	int lineOfBusinessID;
	String code;
	String name;
	String description;
	Date dateAdded;
	String addedBy;
	int warningThreshold;
	int dangerThreshold;
	String fullCodeSoFar;
	ArrayList<FunctionRecord> functions;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLineOfBusinessID() {
		return lineOfBusinessID;
	}
	public void setLineOfBusinessID(int lineOfBusinessID) {
		this.lineOfBusinessID = lineOfBusinessID;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
	public String getAddedBy() {
		return addedBy;
	}
	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}
	public int getWarningThreshold() {
		return warningThreshold;
	}
	public void setWarningThreshold(int warningThreshold) {
		this.warningThreshold = warningThreshold;
	}
	public int getDangerThreshold() {
		return dangerThreshold;
	}
	public void setDangerThreshold(int dangerThreshold) {
		this.dangerThreshold = dangerThreshold;
	}
	public String getFullCodeSoFar() {
		return fullCodeSoFar;
	}
	public void setFullCodeSoFar(String fullCodeSoFar) {
		this.fullCodeSoFar = fullCodeSoFar;
	}
	public ArrayList<FunctionRecord> getFunctions() {
		return functions;
	}
	public void setFunctions(ArrayList<FunctionRecord> functions) {
		this.functions = functions;
	}


}