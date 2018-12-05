package database.DAO.common;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

import database.common.DatabaseAttributes;
import database.common.DatabaseTables;
import database.common.DatabaseUtility;
import database.databaseRecordPojos.common.ErrorCodeAuditRecord;



public class ErrorCodeAuditDAO {

	DatabaseAttributes dbAttributes = new DatabaseAttributes();
	Gson gson = new Gson();

	@SuppressWarnings("deprecation")
	private DBCollection getCollection() {
		MongoClient client = DatabaseUtility.getClient();
		DB db = client.getDB(DatabaseUtility.getSchema());
		return db.getCollection(DatabaseTables.ErrorCodeAudit.getTable());
	}

//	public ArrayList<ErrorCodeRecord> getAll() {
//		DBCollection collection = getCollection();
//		DBCursor cursor = collection.find();
//		ArrayList<ErrorCodeRecord> records = new ArrayList<ErrorCodeRecord>();
//		while (cursor.hasNext()) {
//			DBObject dbObject = cursor.next();
//			String dbObjectJSON = gson.toJson(dbObject);
//			ErrorCodeRecord record = gson.fromJson(dbObjectJSON, ErrorCodeRecord.class);
//			records.add(record);
//		}
//		return records;
//	}

//	public ArrayList<ErrorCodeRecord> getRecordsByParentID(int id) {
//		DBCollection collection = getCollection();
//		BasicDBObject whereQuery = new BasicDBObject();
//		whereQuery.put(dbAttributes.errorCodeSegment_ParentID, id);
//		DBCursor cursor = collection.find(whereQuery);
//		ArrayList<ErrorCodeRecord> records = new ArrayList<ErrorCodeRecord>();
//		while (cursor.hasNext()) {
//			DBObject dbObject = cursor.next();
//			String dbObjectJSON = gson.toJson(dbObject);
//			ErrorCodeRecord record = gson.fromJson(dbObjectJSON, ErrorCodeRecord.class);
//			records.add(record);
//		}
//		// client.close();
//		return records;
//	}

	
	
	public ArrayList<ErrorCodeAuditRecord> getByErrorAuditID(int auditID) {
		DBCollection collection = getCollection();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put(dbAttributes.errorCodeAudit_ErrorID, auditID);
		DBCursor cursor = collection.find(whereQuery);
		ArrayList<ErrorCodeAuditRecord> records = new ArrayList<ErrorCodeAuditRecord>();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			String dbObjectJSON = gson.toJson(dbObject);
			ErrorCodeAuditRecord record = gson.fromJson(dbObjectJSON, ErrorCodeAuditRecord.class);
			records.add(record);
		}
		return records;
	}
	
	public ArrayList<ErrorCodeAuditRecord> getBySegmentTypeAndValue(int segment, String value) {
		DBCollection collection = getCollection();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put(dbAttributes.errorCodeAudit_Code, value);
		whereQuery.put(dbAttributes.errorCodeAudit_segmentTypeID, segment);
		DBCursor cursor = collection.find(whereQuery);
		ArrayList<ErrorCodeAuditRecord> records = new ArrayList<ErrorCodeAuditRecord>();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			String dbObjectJSON = gson.toJson(dbObject);
			ErrorCodeAuditRecord record = gson.fromJson(dbObjectJSON, ErrorCodeAuditRecord.class);
			records.add(record);
		}
		return records;
	}
	public ErrorCodeAuditRecord insertErrorCode(ErrorCodeAuditRecord record) {
		DBCollection collection = getCollection();
		record.setId(DatabaseUtility.getNextID(DatabaseTables.ErrorCodeAudit.getTable(), dbAttributes.primaryKey));
		DBObject dbObject = (DBObject) JSON.parse(gson.toJson(record, ErrorCodeAuditRecord.class));
		collection.insert(dbObject);
		String dbObjectJSON = gson.toJson(dbObject);
		record = gson.fromJson(dbObjectJSON, ErrorCodeAuditRecord.class);
		return record;
	}

}
