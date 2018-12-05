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
import database.databaseRecordPojos.common.ProductRecord;

public class ProductDAO {

	DatabaseAttributes dbAttributes = new DatabaseAttributes();
	Gson gson = new Gson();

	@SuppressWarnings("deprecation")
	private DBCollection getCollection() {
		MongoClient client = DatabaseUtility.getClient();
		DB db = client.getDB(DatabaseUtility.getSchema());
		return db.getCollection(DatabaseTables.Product.getTable());
	}

	public ArrayList<ProductRecord> getAllByLOB(int lobID) {
		DBCollection collection = getCollection();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("lineOfBusinessID", lobID);
		DBCursor cursor = collection.find(whereQuery);
		ArrayList<ProductRecord> records = new ArrayList<ProductRecord>();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			String dbObjectJSON = gson.toJson(dbObject);
			ProductRecord record = gson.fromJson(dbObjectJSON, ProductRecord.class);
			records.add(record);
		}
		return records;
	}
	
	public ProductRecord getByID(int id) {
		DBCollection collection = getCollection();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("id", id);
		DBCursor cursor = collection.find(whereQuery);
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			String dbObjectJSON = gson.toJson(dbObject);
			return gson.fromJson(dbObjectJSON, ProductRecord.class);
			
		}
		return null;
	}
	

	public ProductRecord insertProduct(ProductRecord record) {
		DBCollection collection = getCollection();
		record.setId(DatabaseUtility.getNextID(DatabaseTables.Product.getTable(), dbAttributes.primaryKey));
		DBObject dbObject = (DBObject) JSON.parse(gson.toJson(record,ProductRecord.class));
		collection.insert(dbObject);
		String dbObjectJSON = gson.toJson(dbObject);
		record = gson.fromJson(dbObjectJSON, ProductRecord.class);
		return record;
	}
	
	public void deleteProductRecord(int id) {
		DBCollection collection = getCollection();
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put(dbAttributes.primaryKey, id);
		collection.remove(whereQuery);
	}

}
