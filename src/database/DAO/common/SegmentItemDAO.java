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
//import database.databaseRecordPojos.common.SegmentItemRecord;
//
//
//public class SegmentItemDAO {
//
//	DatabaseAttributes dbAttributes = new DatabaseAttributes();
//	Gson gson = new Gson();
//
//	@SuppressWarnings("deprecation")
//	private DBCollection getCollection() {
//		MongoClient client = DatabaseUtility.getClient();
//		DB db = client.getDB(DatabaseUtility.getSchema());
//		return db.getCollection(DatabaseTables.SegmentItem.getTable());
//	}
//
//	public ArrayList<SegmentItemRecord> getAll() {
//		DBCollection collection = getCollection();
//		DBCursor cursor = collection.find();
//		ArrayList<SegmentItemRecord> records = new ArrayList<SegmentItemRecord>();
//		while (cursor.hasNext()) {
//			DBObject dbObject = cursor.next();
//			String dbObjectJSON = gson.toJson(dbObject);
//			SegmentItemRecord record = gson.fromJson(dbObjectJSON, SegmentItemRecord.class);
//			records.add(record);
//		}
//		return records;
//	}
//
//	public ArrayList<SegmentItemRecord> getRecordsByParentID(int id) {
//		DBCollection collection = getCollection();
//		BasicDBObject whereQuery = new BasicDBObject();
//		whereQuery.put(dbAttributes.segmentItem_ParentID, id);
//		DBCursor cursor = collection.find(whereQuery);
//		ArrayList<SegmentItemRecord> records = new ArrayList<SegmentItemRecord>();
//		while (cursor.hasNext()) {
//			DBObject dbObject = cursor.next();
//			String dbObjectJSON = gson.toJson(dbObject);
//			SegmentItemRecord record = gson.fromJson(dbObjectJSON, SegmentItemRecord.class);
//			records.add(record);
//		}
//		// client.close();
//		return records;
//	}
//	
//	
//
//	
//	
//	public ArrayList<SegmentItemRecord> getRecordsBySegmentTypeID(int id) {
//		DBCollection collection = getCollection();
//		BasicDBObject whereQuery = new BasicDBObject();
//		whereQuery.put(dbAttributes.segmentItem_segmentTypeID, id);
//		DBCursor cursor = collection.find(whereQuery);
//		ArrayList<SegmentItemRecord> records = new ArrayList<SegmentItemRecord>();
//		while (cursor.hasNext()) {
//			DBObject dbObject = cursor.next();
//			String dbObjectJSON = gson.toJson(dbObject);
//			SegmentItemRecord record = gson.fromJson(dbObjectJSON, SegmentItemRecord.class);
//			records.add(record);
//		}
//		// client.close();
//		return records;
//	}
//	
//	public ArrayList<SegmentItemRecord> getRecordsBySegmentID(int id) {
//		DBCollection collection = getCollection();
//		BasicDBObject whereQuery = new BasicDBObject();
//		whereQuery.put(dbAttributes.primaryKey, id);
//		DBCursor cursor = collection.find(whereQuery);
//		ArrayList<SegmentItemRecord> records = new ArrayList<SegmentItemRecord>();
//		while (cursor.hasNext()) {
//			DBObject dbObject = cursor.next();
//			String dbObjectJSON = gson.toJson(dbObject);
//			SegmentItemRecord record = gson.fromJson(dbObjectJSON, SegmentItemRecord.class);
//			records.add(record);
//		}
//		// client.close();
//		return records;
//	}
//	
//	
//	public SegmentItemRecord getRecordByID(int id) {
//		DBCollection collection = getCollection();
//		BasicDBObject whereQuery = new BasicDBObject();
//		whereQuery.put(dbAttributes.segmentItem_segmentTypeID, id);
//		DBCursor cursor = collection.find(whereQuery);
//		while (cursor.hasNext()) {
//			DBObject dbObject = cursor.next();
//			String dbObjectJSON = gson.toJson(dbObject);
//			SegmentItemRecord record = gson.fromJson(dbObjectJSON, SegmentItemRecord.class);
//			return record;
//		}
//		// client.close();
//		return null;
//	}
//	
//	public SegmentItemRecord getRecordBySegmentTypeIDAndCode(int id, String code) {
//		DBCollection collection = getCollection();
//		BasicDBObject whereQuery = new BasicDBObject();
//		whereQuery.put(dbAttributes.segmentItem_segmentTypeID, id);
//		whereQuery.put(dbAttributes.segmentItem_Code, code);
//		DBCursor cursor = collection.find(whereQuery);
//		while (cursor.hasNext()) {
//			DBObject dbObject = cursor.next();
//			String dbObjectJSON = gson.toJson(dbObject);
//			SegmentItemRecord record = gson.fromJson(dbObjectJSON, SegmentItemRecord.class);
//			return record;
//		}
//		// client.close();
//		return null;
//	}
//	
//	public SegmentItemRecord getRecordBySegmentTypeIDCodeAndParentID(int id, String code, int parentID) {
//		DBCollection collection = getCollection();
//		BasicDBObject whereQuery = new BasicDBObject();
//		whereQuery.put(dbAttributes.segmentItem_segmentTypeID, id);
//		whereQuery.put(dbAttributes.segmentItem_Code, code);
//		whereQuery.put(dbAttributes.segmentItem_ParentID, parentID);
//		DBCursor cursor = collection.find(whereQuery);
//		while (cursor.hasNext()) {
//			DBObject dbObject = cursor.next();
//			String dbObjectJSON = gson.toJson(dbObject);
//			SegmentItemRecord record = gson.fromJson(dbObjectJSON, SegmentItemRecord.class);
//			return record;
//		}
//		// client.close();
//		return null;
//	}
//
//	public SegmentItemRecord insertErrorCodeSegment(SegmentItemRecord record) {
//		DBCollection collection = getCollection();
//		record.setId(DatabaseUtility.getNextID(DatabaseTables.SegmentItem.getTable(), dbAttributes.primaryKey));
//		DBObject dbObject = (DBObject) JSON.parse(gson.toJson(record,SegmentItemRecord.class));
//		collection.insert(dbObject);
//		String dbObjectJSON = gson.toJson(dbObject);
//		record = gson.fromJson(dbObjectJSON, SegmentItemRecord.class);
//		return record;
//	}
//	
//	public void deleteSegmentItemRecord(int id) {
//		DBCollection collection = getCollection();
//		BasicDBObject whereQuery = new BasicDBObject();
//		whereQuery.put(dbAttributes.primaryKey, id);
//		collection.remove(whereQuery);
//	}
//
//}
