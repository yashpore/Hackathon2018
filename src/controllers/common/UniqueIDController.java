package controllers.common;

import java.util.ArrayList;

import database.DAO.common.UniqueIDDAO;
import database.databaseRecordPojos.common.RelatedSystemRecord;
import database.databaseRecordPojos.common.UniqueIDRecord;

public class UniqueIDController {

	UniqueIDDAO uniqueIDDAO = new UniqueIDDAO();

	public ArrayList<UniqueIDRecord> getAllByFunctionID(int functionID) {
		RelatedSystemController relatedSystemController = new RelatedSystemController();
		ArrayList<UniqueIDRecord> ids = uniqueIDDAO.getAllByFunction(functionID);
		
		for (UniqueIDRecord id : ids)  {
			ArrayList<RelatedSystemRecord> relatedSystems = relatedSystemController.getAllByUniqueErrorID(id.getId());
			id.setRelatedSystems(relatedSystems);
		}

		return ids;
	}

	public UniqueIDRecord getByID(int id) {
		return uniqueIDDAO.getByID(id);
	}

	public UniqueIDRecord insertFunction(UniqueIDRecord record) {
		return uniqueIDDAO.insertFunction(record);
	}

	public void deleteFunctionRecord(int id) {
		uniqueIDDAO.deleteUniqueIDRecord(id);
	}
}
