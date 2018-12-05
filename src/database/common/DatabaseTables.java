package database.common;


/*
 * This class defines each table within the 
 * Mongo database schema
 */
public enum DatabaseTables {
	
	LineOfBusiness("LineOfBusiness"), 
	Product("Product"), 
	Function("Function"), 
	UniqueID("UniqueID"),
	
	AssignedPerson("AssignedPerson"),
	Person("Person"),
	
	//SegmentItem("SegmentItem"), 
	//SegmentType("SegmentType"),
	ErrorCodeAudit("ErrorCodeAudit"),
	ErrorAudit("ErrorAudit"),
	CompletedCombinedSegmentAttributes("EntireError"),
	
	RelatedSystem("RelatedSystem");
	
	private final String table;

	private DatabaseTables(String table){
		this.table = table;
	}

	public String getTable() {
		return table;
	}

	public static final DatabaseTables getEnum(String table) {
		DatabaseTables enumType = null;
		for (DatabaseTables t : values()) {
			if (t.getTable() == (table)) {
				enumType = t;
			}
		}
		return enumType;
	}

	public boolean equalsName(String otherTable) {
		return table == (otherTable);
	}

}