package controllers.common;

import java.util.ArrayList;

import database.DAO.common.FunctionDAO;
import database.databaseRecordPojos.common.FunctionRecord;

public class FunctionController {

	FunctionDAO functionDAO = new  FunctionDAO();
	
	public ArrayList<FunctionRecord> getAllByProductID(int productID) {
		return  functionDAO.getAllByProduct(productID);
	}
	
	public FunctionRecord getByID(int id) {
		return functionDAO.getByID(id);
	}

	public  FunctionRecord insertFunction( FunctionRecord record) {
		return functionDAO.insertFunction(record);
	}
	
	public void deleteFunctionRecord(int id) {
		functionDAO.deleteProductRecord(id);
	}
}
