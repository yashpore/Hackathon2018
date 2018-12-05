//package database.DAO.common;
//
//import java.util.ArrayList;
//
//import com.google.gson.Gson;
//import com.mongodb.BasicDBObject;
//import com.mongodb.DB;
//import com.mongodb.DBCollection;
//import com.mongodb.DBCursor;
//import com.mongodb.DBObject;
//import com.mongodb.MongoClient;
//import com.mongodb.util.JSON;
//
//import database.common.DatabaseAttributes;
//import database.common.DatabaseTables;
//import database.common.DatabaseUtility;
//import database.databaseRecordPojos.common.SegmentTypeRecord;
//
//public class SegmentTypeDAO {
//
//	DatabaseAttributes dbAttributes = new DatabaseAttributes();
//	Gson gson = new Gson();
//
//	@SuppressWarnings("deprecation")
//	private DBCollection getCollection() {
//		MongoClient client = DatabaseUtility.getClient();
//		DB db = client.getDB(DatabaseUtility.getSchema());
//		return db.getCollection(DatabaseTables.SegmentType.getTable());
//	}
//
//	public ArrayList<SegmentTypeRecord> getAll() {
//		DBCollection collection = getCollection();
//		DBCursor cursor = collection.find();
//		ArrayList<SegmentTypeRecord> records = new ArrayList<SegmentTypeRecord>();
//		while (cursor.hasNext()) {
//			DBObject dbObject = cursor.next();
//			String dbObjectJSON = gson.toJson(dbObject);
//			SegmentTypeRecord record = gson.fromJson(dbObjectJSON,SegmentTypeRecord.class);
//			records.add(record);
//		}
//		return records;
//	}
//	
//	
//	public SegmentTypeRecord getByID(int id) {
//		DBCollection collection = getCollection();
//		BasicDBObject whereQuery = new BasicDBObject();
//		whereQuery.put(dbAttributes.primaryKey, id);
//		DBCursor cursor = collection.find(whereQuery);
//		while (cursor.hasNext()) {
//			DBObject dbObject = cursor.next();
//			String dbObjectJSON = gson.toJson(dbObject);
//			SegmentTypeRecord record = gson.fromJson(dbObjectJSON,SegmentTypeRecord.class);
//			return record;
//		}
//		return null;
//	}
//	
//	
////	public SegmentTypeRecord getBySegmentID(int id) {
////	DBCollection collection = getCollection();
////	BasicDBObject whereQuery = new BasicDBObject();
////	whereQuery.put(dbAttributes.segmentItem_segmentTypeID, id);
////	DBCursor cursor = collection.find(whereQuery);
////	while (cursor.hasNext()) {
////		DBObject dbObject = cursor.next();
////		String dbObjectJSON = gson.toJson(dbObject);
////		SegmentTypeRecord record = gson.fromJson(dbObjectJSON,SegmentTypeRecord.class);
////		return record;
////	}
////	return null;
////}
//	
//	
//	public ArrayList<SegmentTypeRecord> getChildrenOfID(int id) {
//		DBCollection collection = getCollection();
//		BasicDBObject whereQuery = new BasicDBObject();
//		whereQuery.put(dbAttributes.segmentType_ParentID, id);
//		DBCursor cursor = collection.find(whereQuery);
//		ArrayList<SegmentTypeRecord> records = new ArrayList<SegmentTypeRecord>();
//		while (cursor.hasNext()) {
//			DBObject dbObject = cursor.next();
//			String dbObjectJSON = gson.toJson(dbObject);
//			SegmentTypeRecord record = gson.fromJson(dbObjectJSON,SegmentTypeRecord.class);
//			records.add(record);
//		}
//		return records;
//	}
//	
//	
//	public SegmentTypeRecord insertErrorCodeSegmentType(SegmentTypeRecord record) {
//		DBCollection collection = getCollection();
//		//Manual ID
//		//record.setId(DatabaseUtility.getNextID(DatabaseTables.ErrorCodeSegmentType.getTable(), dbAttributes.primaryKey));
//		DBObject dbObject = (DBObject) JSON.parse(gson.toJson(record, SegmentTypeRecord.class));
//		collection.insert(dbObject);
//		String dbObjectJSON = gson.toJson(dbObject);
//		record = gson.fromJson(dbObjectJSON, SegmentTypeRecord.class);
//		return record;
//	}
//
//}
