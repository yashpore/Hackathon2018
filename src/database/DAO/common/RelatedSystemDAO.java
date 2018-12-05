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
import database.databaseRecordPojos.common.RelatedSystemRecord;

public class RelatedSystemDAO {

	DatabaseAttributes dbAttributes = new DatabaseAttributes();
	Gson gson = new Gson();

	@SuppressWarnings("deprecation")
	private DBCollection getCollection() {
		MongoClient client = DatabaseUtility.getClient();
		DB db = client.getDB(DatabaseUtility.getSchema());
		return db.getCollection(DatabaseTables.RelatedSystem.getTable());
	}

	public ArrayList<RelatedSystemRecord> getByUniqueErrorID(int id) {
		DBCollection collection = getCollection();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("uniqueErrorID", id);
		DBCursor cursor = collection.find(whereQuery);
		ArrayList<RelatedSystemRecord> records = new ArrayList<RelatedSystemRecord>();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			String dbObjectJSON = gson.toJson(dbObject);
			RelatedSystemRecord record = gson.fromJson(dbObjectJSON, RelatedSystemRecord.class);
			records.add(record);
		}
		return records;
	}
	
	public ArrayList<RelatedSystemRecord> getAll() {
		DBCollection collection = getCollection();
		DBCursor cursor = collection.find();
		ArrayList<RelatedSystemRecord> records = new ArrayList<RelatedSystemRecord>();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			String dbObjectJSON = gson.toJson(dbObject);
			RelatedSystemRecord record = gson.fromJson(dbObjectJSON, RelatedSystemRecord.class);
			records.add(record);
		}
		return records;
	}
	

	public RelatedSystemRecord insert(RelatedSystemRecord record) {
		DBCollection collection = getCollection();
		record.setId(DatabaseUtility.getNextID(DatabaseTables.RelatedSystem.getTable(), dbAttributes.primaryKey));
		DBObject dbObject = (DBObject) JSON.parse(gson.toJson(record, RelatedSystemRecord.class));
		collection.insert(dbObject);
		String dbObjectJSON = gson.toJson(dbObject);
		record = gson.fromJson(dbObjectJSON, RelatedSystemRecord.class);
		return record;
	}

	public void delete(int id) {
		DBCollection collection = getCollection();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put(dbAttributes.primaryKey, id);
		collection.remove(whereQuery);
	}
}
