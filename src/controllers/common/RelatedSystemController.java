package controllers.common;

import java.util.ArrayList;

import database.DAO.common.RelatedSystemDAO;
import database.databaseRecordPojos.common.RelatedSystemRecord;

public class RelatedSystemController {

	RelatedSystemDAO dao = new RelatedSystemDAO();

	public ArrayList<RelatedSystemRecord> getAllByUniqueErrorID(int id) {
		return dao.getByUniqueErrorID(id);
	}

	public ArrayList<RelatedSystemRecord> getAll() {
		return dao.getAll();
	}
	
	
	public RelatedSystemRecord insertFunction(RelatedSystemRecord record) {
		return dao.insert(record);
	}

	public void delete(int id) {
		dao.delete(id);
	}
}
