//package controllers.common;
//
//import java.util.ArrayList;
//
//import database.DAO.common.SegmentTypeDAO;
//import database.databaseRecordPojos.common.SegmentTypeRecord;
//import properties.Local;
//
//public class SegmentTypeController {
//
//	SegmentTypeDAO errorCodeSegmentTypeDAO = new SegmentTypeDAO();
//
//	public void insertDefaultErrorCodeSegmentTypes() {
//		errorCodeSegmentTypeDAO.insertErrorCodeSegmentType(buildRecordObject(Local.LineOfBusiness, Local.ROOT, "Line Of Business"));
//		errorCodeSegmentTypeDAO.insertErrorCodeSegmentType(buildRecordObject(Local.Product, Local.LineOfBusiness, "Product"));
//		errorCodeSegmentTypeDAO.insertErrorCodeSegmentType(buildRecordObject(Local.Function, Local.Product, "Function"));
//		errorCodeSegmentTypeDAO.insertErrorCodeSegmentType(buildRecordObject(Local.UniqueID, Local.Function, "Unique ID + Severity Grade"));
//	}
//	
//	
//	public ArrayList<SegmentTypeRecord> getDefaultErrorCodeSegmentTypes() {
//		return errorCodeSegmentTypeDAO.getAll();
//	}
//
//	private SegmentTypeRecord buildRecordObject(int id, int parentID, String segment) {
//		SegmentTypeRecord record = new SegmentTypeRecord();
//		record.setId(id);
//		record.setParentID(parentID);
//		record.setSegment(segment);
//		return record;
//	}
//
//	public SegmentTypeRecord getParentSegmentOfID(int id) {
//		SegmentTypeRecord current = errorCodeSegmentTypeDAO.getByID(id);
//		if (current != null) {
//			int parentID = current.getParentID();
//			SegmentTypeRecord parent = errorCodeSegmentTypeDAO.getByID(parentID);
//			return parent;
//		}
//		return null;
//	}
//
//	public ArrayList<SegmentTypeRecord> getChildSegmentsOfID(int id) {
//		return errorCodeSegmentTypeDAO.getChildrenOfID(id);
//
//	}
//
//}