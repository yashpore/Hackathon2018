package database.databaseRecordPojos.common;

import java.util.Date;

public class ErrorAuditRecord {

	int id;
	String statement;
	Date dateAdded;
	String addedBy;
	String recipient;
	
	String fullCode;
	int lobID;
	int productID;
	int functionID;
	int uniqueErrorID;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
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
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getFullCode() {
		return fullCode;
	}
	public void setFullCode(String fullCode) {
		this.fullCode = fullCode;
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

}
