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
import database.databaseRecordPojos.common.UniqueIDRecord;


public class UniqueIDDAO {

	DatabaseAttributes dbAttributes = new DatabaseAttributes();
	Gson gson = new Gson();

	@SuppressWarnings("deprecation")
	private DBCollection getCollection() {
		MongoClient client = DatabaseUtility.getClient();
		DB db = client.getDB(DatabaseUtility.getSchema());
		return db.getCollection(DatabaseTables.UniqueID.getTable());
	}

	public ArrayList<UniqueIDRecord> getAllByFunction(int functionID) {
		DBCollection collection = getCollection();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("functionID", functionID);
		DBCursor cursor = collection.find(whereQuery);
		ArrayList<UniqueIDRecord> records = new ArrayList<UniqueIDRecord>();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			String dbObjectJSON = gson.toJson(dbObject);
			UniqueIDRecord record = gson.fromJson(dbObjectJSON, UniqueIDRecord.class);
			records.add(record);
		}
		return records;
	}

	public UniqueIDRecord getByID(int id) {
		DBCollection collection = getCollection();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("id", id);
		DBCursor cursor = collection.find(whereQuery);
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			String dbObjectJSON = gson.toJson(dbObject);
			return gson.fromJson(dbObjectJSON, UniqueIDRecord.class);
			
		}
		return null;
	}
	
	public UniqueIDRecord insertFunction(UniqueIDRecord record) {
		DBCollection collection = getCollection();
		record.setId(DatabaseUtility.getNextID(DatabaseTables.UniqueID.getTable(), dbAttributes.primaryKey));
		DBObject dbObject = (DBObject) JSON.parse(gson.toJson(record,UniqueIDRecord.class));
		collection.insert(dbObject);
		String dbObjectJSON = gson.toJson(dbObject);
		record = gson.fromJson(dbObjectJSON, UniqueIDRecord.class);
		return record;
	}
	
	public void deleteUniqueIDRecord(int id) {
		DBCollection collection = getCollection();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put(dbAttributes.primaryKey, id);
		collection.remove(whereQuery);
	}

}
