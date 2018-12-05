package controllers.pojos;

import java.util.ArrayList;

import database.databaseRecordPojos.common.ErrorAuditRecord;
import database.databaseRecordPojos.common.ErrorCodeAuditRecord;
import database.databaseRecordPojos.common.FunctionRecord;
import database.databaseRecordPojos.common.LineOfBusinessRecord;
import database.databaseRecordPojos.common.ProductRecord;
import database.databaseRecordPojos.common.UniqueIDRecord;

public class ErrorAuditDetails {
	String fullErrorCode;
	LineOfBusinessRecord lineOfBusines;
	ProductRecord product;
	FunctionRecord function;
	UniqueIDRecord uniqueError;

	ErrorAuditRecord errorAudit;
	ArrayList<ErrorCodeAuditRecord> errorCodePortions;
	public String getFullErrorCode() {
		return fullErrorCode;
	}
	public void setFullErrorCode(String fullErrorCode) {
		this.fullErrorCode = fullErrorCode;
	}
	public LineOfBusinessRecord getLineOfBusines() {
		return lineOfBusines;
	}
	public void setLineOfBusines(LineOfBusinessRecord lineOfBusines) {
		this.lineOfBusines = lineOfBusines;
	}
	public ProductRecord getProduct() {
		return product;
	}
	public void setProduct(ProductRecord product) {
		this.product = product;
	}
	public FunctionRecord getFunction() {
		return function;
	}
	public void setFunction(FunctionRecord function) {
		this.function = function;
	}
	public UniqueIDRecord getUniqueError() {
		return uniqueError;
	}
	public void setUniqueError(UniqueIDRecord uniqueError) {
		this.uniqueError = uniqueError;
	}
	public ErrorAuditRecord getErrorAudit() {
		return errorAudit;
	}
	public void setErrorAudit(ErrorAuditRecord errorAudit) {
		this.errorAudit = errorAudit;
	}
	public ArrayList<ErrorCodeAuditRecord> getErrorCodePortions() {
		return errorCodePortions;
	}
	public void setErrorCodePortions(ArrayList<ErrorCodeAuditRecord> errorCodePortions) {
		this.errorCodePortions = errorCodePortions;
	}

	
}
