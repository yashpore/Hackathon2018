package database.common;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import database.databaseRecordPojos.common.PrimaryKey;
import properties.Local;

public class DatabaseUtility {
	
	private static MongoClient client = null;
	
	private static final int local_port = Local.mongo_local_port;
	private static final String local_host = Local.mongo_local_host;
	private static final String local_schema = Local.mongo_local_schema;
	
	public static MongoClient getClient() {
		if (client == null){
			client = new MongoClient(local_host, local_port);
		} 
		return client;
	}

	public static String getSchema() {
		return local_schema;
	}
	
	// Retrieve the next ID for the database field, ID
	// Note that there are better ways of doing this,
	// such as using the predefined database id
	public static int getNextID(String table, String idColumn) {
		Gson gson = new Gson();

		MongoClient client = getClient();
		@SuppressWarnings("deprecation")
		DB db = client.getDB(getSchema());

		DBCollection collection = db.getCollection(table);

		BasicDBObject whereQuery = new BasicDBObject(idColumn, -1);
		DBCursor cursor = collection.find().sort(whereQuery).limit(1);

		if (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			String dbObjectJSON = gson.toJson(dbObject);	
			PrimaryKey pk = gson.fromJson(dbObjectJSON, PrimaryKey.class); 
			return pk.getId() + 1;
		} else {
			return 1;
		}
	}
}


