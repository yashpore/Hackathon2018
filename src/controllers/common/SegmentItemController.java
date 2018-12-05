//package controllers.common;
//
//import java.util.ArrayList;
//import java.util.Date;
//
//import apiResources.ResponsePojos.SegmentItemDetails;
//import database.DAO.common.SegmentItemDAO;
//import database.DAO.common.SegmentTypeDAO;
//import database.databaseRecordPojos.common.SegmentItemRecord;
//import database.databaseRecordPojos.common.SegmentTypeRecord;
//import properties.Local;
//
//public class SegmentItemController {
//
//	SegmentItemDAO segmentItemDAO = new SegmentItemDAO();
//	SegmentTypeDAO segmentTypeDAO = new SegmentTypeDAO();
//
//	public void insertItem(int parentID, int segment, String code, String name, String description, String addedBy) {
//		insertSegmentItem(buildRecordObject(parentID, segment, code, name, description, addedBy));
//	}
//
//	public SegmentItemRecord insertSegmentItem(SegmentItemRecord errorCodeSegmentRecord) {
//		return segmentItemDAO.insertErrorCodeSegment(errorCodeSegmentRecord);
//	}
//
//	
//	
//	public ArrayList<SegmentItemRecord> geSegmentItemsByParentID(int id) {
//		return segmentItemDAO.getRecordsByParentID(id);
//	}
//
//	public ArrayList<SegmentItemRecord> getSegmentItemsBySegmentTypeID(int id) {
//		return segmentItemDAO.getRecordsBySegmentTypeID(id);
//	}
//	
//	public ArrayList<SegmentItemRecord> getSegmentItemsBySegmentID(int id) {
//		return segmentItemDAO.getRecordsBySegmentID(id);
//	}
//	
//	public SegmentItemRecord getSegmentID(int id) {
//		return segmentItemDAO.getRecordByID(id);
//	}
//	
//	
//	public SegmentItemRecord getLineOfBusinessByCode(String lobCode) {
//		return segmentItemDAO.getRecordBySegmentTypeIDAndCode(Local.LineOfBusiness, lobCode); 
//	}
//	
//	public SegmentItemRecord getProductByLOBCodeAndProductCode(String lobCode, String productCode) {
//		SegmentItemRecord lobRecord = getLineOfBusinessByCode(lobCode); 
//		if (lobRecord == null) {
//			return null;
//		}
//		int parentID = lobRecord.getId();		
//		return segmentItemDAO.getRecordBySegmentTypeIDCodeAndParentID(Local.Product, productCode, parentID); 
//	}
//	
//	public SegmentItemRecord getFunctionByLOBCodeProductCodeAndFunctionCode(String lobCode, String productCode, String functionCode) {
//		SegmentItemRecord productRecord = getProductByLOBCodeAndProductCode( lobCode, productCode);
//		if (productRecord == null) {
//			return null;
//		}
//		int parentID = productRecord.getId();		
//		return segmentItemDAO.getRecordBySegmentTypeIDCodeAndParentID(Local.Function, functionCode, parentID); 
//	}
//		
//	
//	public SegmentItemDetails getAllDetailsBySegmentTypeID(int id) {
//		SegmentItemDetails segmentItemDetails = new SegmentItemDetails();
//		segmentItemDetails.setChildrenTypes(segmentTypeDAO.getChildrenOfID(id));
//		segmentItemDetails.setType(segmentTypeDAO.getByID(id));
//		
//		segmentItemDetails.setItems(getSegmentItemsBySegmentTypeID(id));
//		
//		SegmentTypeRecord currentType = segmentTypeDAO.getByID(id);
//		if (currentType != null) {
//			segmentItemDetails.setParentType(segmentTypeDAO.getByID(currentType.getParentID()));	
//		}
//		return segmentItemDetails;
//	}
//
//	
//	public SegmentItemDetails getAllDetailsBySegmentIDAndType(int id) {
//		SegmentItemDetails segmentItemDetails = new SegmentItemDetails();
//		
//		
//		SegmentItemRecord segment = getSegmentID(id);
//		
//		segmentItemDetails.setChildrenTypes(segmentTypeDAO.getChildrenOfID(segment.getSegmentTypeID()));
//		segmentItemDetails.setType(segmentTypeDAO.getByID(segment.getSegmentTypeID()));
//		
//		segmentItemDetails.setItems(getSegmentItemsBySegmentID(id));
//		
//	
//		segmentItemDetails.setParentType(segmentTypeDAO.getByID(segment.getParentID()));	
//		
//		return segmentItemDetails;
//	}
//	
//	public void deleteSegmentItemRecord(int id) {
//		segmentItemDAO.deleteSegmentItemRecord(id);
//	}
//
//	
//	private SegmentItemRecord buildRecordObject(int parentID, int segment, String code, String name, String description,
//			String addedBy) {
//		SegmentItemRecord record = new SegmentItemRecord();
//		record.setParentID(parentID);
//		record.setSegmentTypeID(segment);
//		record.setCode(code);
//		record.setName(name);
//		record.setAddedBy(addedBy);
//		record.setDescription(description);
//		record.setDateAdded(new Date());
//		return record;
//	}
//
//}