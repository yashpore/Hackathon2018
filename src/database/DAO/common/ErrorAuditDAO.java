package database.DAO.common;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

import database.common.DatabaseAttributes;
import database.common.DatabaseTables;
import database.common.DatabaseUtility;
import database.databaseRecordPojos.common.ErrorAuditRecord;

public class ErrorAuditDAO {

	DatabaseAttributes dbAttributes = new DatabaseAttributes();
	Gson gson = new Gson();

	@SuppressWarnings("deprecation")
	private DBCollection getCollection() {
		MongoClient client = DatabaseUtility.getClient();
		DB db = client.getDB(DatabaseUtility.getSchema());
		return db.getCollection(DatabaseTables.ErrorAudit.getTable());
	}

	public ArrayList<ErrorAuditRecord> getAll() {
		DBCollection collection = getCollection();
		DBCursor cursor = collection.find();
		ArrayList<ErrorAuditRecord> records = new ArrayList<ErrorAuditRecord>();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			String dbObjectJSON = gson.toJson(dbObject);
			ErrorAuditRecord record = gson.fromJson(dbObjectJSON, ErrorAuditRecord.class);
			records.add(record);
		}
		return records;
	}
	
	


	public ErrorAuditRecord insert(ErrorAuditRecord record) {
		DBCollection collection = getCollection();
		record.setId(DatabaseUtility.getNextID(DatabaseTables.ErrorAudit.getTable(), dbAttributes.primaryKey));
		DBObject dbObject = (DBObject) JSON.parse(gson.toJson(record, ErrorAuditRecord.class));
		collection.insert(dbObject);
		String dbObjectJSON = gson.toJson(dbObject);
		record = gson.fromJson(dbObjectJSON, ErrorAuditRecord.class);
		return record;
	}

}
