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
import database.databaseRecordPojos.common.AssignedPersonRecord;

public class AssignedPersonDAO {

	DatabaseAttributes dbAttributes = new DatabaseAttributes();
	Gson gson = new Gson();

	@SuppressWarnings("deprecation")
	private DBCollection getCollection() {
		MongoClient client = DatabaseUtility.getClient();
		DB db = client.getDB(DatabaseUtility.getSchema());
		return db.getCollection(DatabaseTables.AssignedPerson.getTable());
	}


	
	public ArrayList<AssignedPersonRecord> getByLineOfBusiness(int id) {
		DBCollection collection = getCollection();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("lobID", id);
		DBCursor cursor = collection.find(whereQuery);
		ArrayList<AssignedPersonRecord> records = new ArrayList<AssignedPersonRecord>();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			String dbObjectJSON = gson.toJson(dbObject);
			AssignedPersonRecord record = gson.fromJson(dbObjectJSON, AssignedPersonRecord.class);
			records.add(record);
		}
		return records;
	}

	public ArrayList<AssignedPersonRecord> getByProduct(int id) {
		DBCollection collection = getCollection();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("productID", id);
		DBCursor cursor = collection.find(whereQuery);
		ArrayList<AssignedPersonRecord> records = new ArrayList<AssignedPersonRecord>();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			String dbObjectJSON = gson.toJson(dbObject);
			AssignedPersonRecord record = gson.fromJson(dbObjectJSON, AssignedPersonRecord.class);
			records.add(record);
		}
		return records;
	}
	
	
	public ArrayList<AssignedPersonRecord> getByFunction(int id) {
		DBCollection collection = getCollection();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("functionID", id);
		DBCursor cursor = collection.find(whereQuery);
		ArrayList<AssignedPersonRecord> records = new ArrayList<AssignedPersonRecord>();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			String dbObjectJSON = gson.toJson(dbObject);
			AssignedPersonRecord record = gson.fromJson(dbObjectJSON, AssignedPersonRecord.class);
			records.add(record);
		}
		return records;
	}
	
	
	public ArrayList<AssignedPersonRecord> getByUniqueError(int id) {
		DBCollection collection = getCollection();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("uniqueErrorID", id);
		DBCursor cursor = collection.find(whereQuery);
		ArrayList<AssignedPersonRecord> records = new ArrayList<AssignedPersonRecord>();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			String dbObjectJSON = gson.toJson(dbObject);
			AssignedPersonRecord record = gson.fromJson(dbObjectJSON, AssignedPersonRecord.class);
			records.add(record);
		}
		return records;
	}
	
	public AssignedPersonRecord insert(AssignedPersonRecord record) {
		DBCollection collection = getCollection();
		record.setId(DatabaseUtility.getNextID(DatabaseTables.AssignedPerson.getTable(), dbAttributes.primaryKey));
		DBObject dbObject = (DBObject) JSON.parse(gson.toJson(record, AssignedPersonRecord.class));
		collection.insert(dbObject);
		String dbObjectJSON = gson.toJson(dbObject);
		record = gson.fromJson(dbObjectJSON, AssignedPersonRecord.class);
		return record;
	}

	public void delete(int id) {
		DBCollection collection = getCollection();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put(dbAttributes.primaryKey, id);
		collection.remove(whereQuery);
	}
	
}
