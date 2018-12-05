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
import database.databaseRecordPojos.common.CompletedCombinedSegmentAttributesRecord;

public class CompletedCombinedSegmentAttributesDAO {

	DatabaseAttributes dbAttributes = new DatabaseAttributes();
	Gson gson = new Gson();

	@SuppressWarnings("deprecation")
	private DBCollection getCollection() {
		MongoClient client = DatabaseUtility.getClient();
		DB db = client.getDB(DatabaseUtility.getSchema());
		return db.getCollection(DatabaseTables.CompletedCombinedSegmentAttributes.getTable());
	}

	public ArrayList<CompletedCombinedSegmentAttributesRecord> getBySegmentID(int id) {
		DBCollection collection = getCollection();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put(dbAttributes.completedCombinedSegmentAttribute_segmentID, id);
		DBCursor cursor = collection.find(whereQuery);
		ArrayList<CompletedCombinedSegmentAttributesRecord> records = new ArrayList<CompletedCombinedSegmentAttributesRecord>();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			String dbObjectJSON = gson.toJson(dbObject);
			CompletedCombinedSegmentAttributesRecord record = gson.fromJson(dbObjectJSON, CompletedCombinedSegmentAttributesRecord.class);
			records.add(record);
		}
		return records;
	}
	
	


	public CompletedCombinedSegmentAttributesRecord insert(CompletedCombinedSegmentAttributesRecord record) {
		DBCollection collection = getCollection();
		record.setId(DatabaseUtility.getNextID(DatabaseTables.CompletedCombinedSegmentAttributes.getTable(), dbAttributes.primaryKey));
		DBObject dbObject = (DBObject) JSON.parse(gson.toJson(record, CompletedCombinedSegmentAttributesRecord.class));
		collection.insert(dbObject);
		String dbObjectJSON = gson.toJson(dbObject);
		record = gson.fromJson(dbObjectJSON, CompletedCombinedSegmentAttributesRecord.class);
		return record;
	}

}
