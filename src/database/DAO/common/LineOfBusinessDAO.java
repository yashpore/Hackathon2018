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
import database.databaseRecordPojos.common.LineOfBusinessRecord;


public class LineOfBusinessDAO {

	DatabaseAttributes dbAttributes = new DatabaseAttributes();
	Gson gson = new Gson();

	@SuppressWarnings("deprecation")
	private DBCollection getCollection() {
		MongoClient client = DatabaseUtility.getClient();
		DB db = client.getDB(DatabaseUtility.getSchema());
		return db.getCollection(DatabaseTables.LineOfBusiness.getTable());
	}

	public ArrayList<LineOfBusinessRecord> getAll() {
		DBCollection collection = getCollection();
		DBCursor cursor = collection.find();
		ArrayList<LineOfBusinessRecord> records = new ArrayList<LineOfBusinessRecord>();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			String dbObjectJSON = gson.toJson(dbObject);
			LineOfBusinessRecord record = gson.fromJson(dbObjectJSON, LineOfBusinessRecord.class);
			records.add(record);
		}
		return records;
	}
	
	public LineOfBusinessRecord getByID(int id) {
		DBCollection collection = getCollection();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("id", id);
		DBCursor cursor = collection.find(whereQuery);
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			String dbObjectJSON = gson.toJson(dbObject);
			return gson.fromJson(dbObjectJSON, LineOfBusinessRecord.class);
		}
		return null;
	}
	

	public LineOfBusinessRecord insertLineOfBusiness(LineOfBusinessRecord record) {
		DBCollection collection = getCollection();
		record.setId(DatabaseUtility.getNextID(DatabaseTables.LineOfBusiness.getTable(), dbAttributes.primaryKey));
		DBObject dbObject = (DBObject) JSON.parse(gson.toJson(record,LineOfBusinessRecord.class));
		collection.insert(dbObject);
		String dbObjectJSON = gson.toJson(dbObject);
		record = gson.fromJson(dbObjectJSON, LineOfBusinessRecord.class);
		return record;
	}
	
	public void deleteLineOfBusinessRecord(int id) {
		DBCollection collection = getCollection();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put(dbAttributes.primaryKey, id);
		collection.remove(whereQuery);
	}

}
