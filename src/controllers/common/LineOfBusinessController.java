package controllers.common;

import java.util.ArrayList;

import database.DAO.common.LineOfBusinessDAO;
import database.databaseRecordPojos.common.LineOfBusinessRecord;

public class LineOfBusinessController {

	LineOfBusinessDAO lineOfBusinessDAO = new LineOfBusinessDAO();
	
	public ArrayList<LineOfBusinessRecord> getAll() {
		return lineOfBusinessDAO.getAll();
	}

	public LineOfBusinessRecord getByID(int id) {
		return lineOfBusinessDAO.getByID(id);
	}
	
	public LineOfBusinessRecord insertLineOfBusiness(LineOfBusinessRecord record) {
		return lineOfBusinessDAO.insertLineOfBusiness(record);
	}
	
	public void deleteLineOfBusinessRecord(int id) {
		lineOfBusinessDAO.deleteLineOfBusinessRecord(id);
	}
}
