package database.common;

public class DatabaseAttributes {

	public final String primaryKey = "id";

	public final String segmentItem_ParentID = "parentID";
	public final String segmentItem_segmentTypeID = "segmentTypeID";
	public final String segmentItem_Code = "code";
	public final String segmentItem_Name = "name";
	public final String segmentItem_Description = "description";
	public final String segmentItem_DateAdded = "dateAdded";
	public final String segmentItem_AddedBy = "addedBy";
	public final String segmentItem_WarningThreshold = "warningThreshold";
	public final String segmentItem_DangerThreshold = "dangerThreshold";
	public final String segmentItem_IsLast = "isLast";
	public final String segmentItem_fullCodeSoFar = "fullCodeSoFar";
	
	public final String segmentType_Segment = "segment";
	public final String segmentType_ParentID = "parentID";	

	public final String errorCodeAudit_segmentTypeID = "segmentTypeID";
	public final String errorCodeAudit_Code = "code";
	public final String errorCodeAudit_ErrorID = "errorID";

	public final String errorAudit_Statement = "statement";
	public final String errorAudit_DateAdded = "dateAdded";
	public final String errorAudit_AddedBy = "addedBy";
	public final String errorAudit_Recipient = "recipient";
	
	public final String completedCombinedSegmentAttribute_segmentID = "segmentID";
	public final String completedCombinedSegmentAttribute_oldErrorMapped = "oldErrorMapped";
	
	public final String person_emailAddress = "emailAddress";
	public final String person_phoneNumber = "phoneNumber";
	public final String person_name = "name";
	
	public final String assignedPerson_segmentID = "segmentID";
	public final String assignedPerson_personID = "personID";
	public final String assignedPerson_call = "call";
	public final String assignedPerson_text = "text";
	public final String assignedPerson_email = "email";
	public final String assignedPerson_other = "other";
	public final String assignedPerson_thresholdLasTenMinutes = "thresholdLasTenMinutes";
	public final String assignedPerson_thresholdLastThirtyMinutes = "thresholdLastThirtyMinutes";
	public final String assignedPerson_thresholdLastHour = "thresholdLastHour";
	public final String assignedPerson_thresholdLastEightHours = "thresholdLastEightHours";
	public final String assignedPerson_thresholdLastTwentyFourHours = "thresholdLastTwentyFourHours";
	public final String assignedPerson_thresholdLastSevenDays = "thresholdLastSevenDays";
	
	public final String relatedSystem_segmentID = "segmentID";
	public final String relatedSystem_system = "system";
	
	
	
	
}
