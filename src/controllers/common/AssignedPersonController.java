package controllers.common;

import java.util.ArrayList;

import database.DAO.common.AssignedPersonDAO;
import database.databaseRecordPojos.common.AssignedPersonRecord;

public class AssignedPersonController {

	AssignedPersonDAO assignedPersonDAO = new AssignedPersonDAO();

	public ArrayList<AssignedPersonRecord> getByLineOfBusiness(int id) {
		return assignedPersonDAO.getByLineOfBusiness(id);
	}

	public ArrayList<AssignedPersonRecord> getByProduct(int id) {
		return assignedPersonDAO.getByProduct(id);
	}

	public ArrayList<AssignedPersonRecord> getByFunction(int id) {
		return assignedPersonDAO.getByFunction(id);
	}

	public ArrayList<AssignedPersonRecord> getByUniqueError(int id) {
		return assignedPersonDAO.getByUniqueError(id);
	}

	public AssignedPersonRecord insert(AssignedPersonRecord record) {
		return assignedPersonDAO.insert(record);
	}

	public void delete(int id) {
		 assignedPersonDAO.delete(id);
	}
}
