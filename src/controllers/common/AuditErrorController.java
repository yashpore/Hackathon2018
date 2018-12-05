package controllers.common;

import java.util.ArrayList;
import java.util.Date;

import org.osgi.framework.ServiceException;

import apiResources.requestPojos.AuditError;
import controllers.pojos.ErrorAuditDetails;
import controllers.pojos.ErrorAuditReport;
import controllers.pojos.RelatedSystemErrors;
import database.DAO.common.ErrorAuditDAO;
import database.databaseRecordPojos.common.ErrorAuditRecord;
import database.databaseRecordPojos.common.ErrorCodeAuditRecord;
import database.databaseRecordPojos.common.FunctionRecord;
import database.databaseRecordPojos.common.LineOfBusinessRecord;
import database.databaseRecordPojos.common.ProductRecord;
import database.databaseRecordPojos.common.RelatedSystemRecord;
import database.databaseRecordPojos.common.UniqueIDRecord;

public class AuditErrorController {

	LineOfBusinessController lobController = new LineOfBusinessController();
	ProductController productController = new ProductController();
	UniqueIDController uniqueIDController = new UniqueIDController();
	FunctionController functionController = new FunctionController();

	// SegmentItemController segmentItemController = new SegmentItemController();

	public void auditError(AuditError auditError) throws ServiceException {

		String code = auditError.getCode();
		if (code.length() != 10) {

			// throw ServiceException();
		}
		String lobCode = code.substring(0, 1);
		String productCode = code.substring(1, 4);
		String functionCode = code.substring(4, 8);
		String uniqueIDCode = code.substring(8, 13);

		// get LOB
		ArrayList<LineOfBusinessRecord> lobs = lobController.getAll();
		LineOfBusinessRecord matchedLOB = new LineOfBusinessRecord();
		for (LineOfBusinessRecord lob : lobs) {
			if (lob.getCode().equalsIgnoreCase(lobCode)) {
				matchedLOB = lob;
			}
		}

		// get product
		ArrayList<ProductRecord> products = productController.getAllByLineOfBusinessID(matchedLOB.getId());
		ProductRecord matchedProduct = new ProductRecord();
		for (ProductRecord product : products) {
			if (product.getCode().equalsIgnoreCase(productCode)) {
				matchedProduct = product;
			}
		}

		// get function
		ArrayList<FunctionRecord> functions = functionController.getAllByProductID(matchedProduct.getId());
		FunctionRecord matchedFunction = new FunctionRecord();
		for (FunctionRecord function : functions) {
			if (function.getCode().equalsIgnoreCase(functionCode)) {
				matchedFunction = function;
			}
		}

		// get uniqueID

		ArrayList<UniqueIDRecord> uniqueIDs = uniqueIDController.getAllByFunctionID(matchedFunction.getId());
		UniqueIDRecord matchedUniqueID = new UniqueIDRecord();
		for (UniqueIDRecord uniqueID : uniqueIDs) {
			if (uniqueID.getCode().equalsIgnoreCase(uniqueIDCode)) {
				matchedUniqueID = uniqueID;
			}
		}

		ErrorAuditRecord record = new ErrorAuditRecord();
		record.setAddedBy(auditError.getAddedBy());
		record.setDateAdded(new Date());
		record.setRecipient(auditError.getRecipient());
		record.setStatement(auditError.getStatement());
		record.setFullCode(auditError.getCode());
		record.setLobID(matchedLOB.getId());
		record.setProductID(matchedProduct.getId());
		record.setFunctionID(matchedFunction.getId());
		record.setUniqueErrorID(matchedUniqueID.getId());

		ErrorAuditDAO errorDAO = new ErrorAuditDAO();
		record = errorDAO.insert(record);

	}
//
//	public ArrayList<ErrorAuditDetails> getAuditDetails() {
//
//		ErrorAuditDAO errorDAO = new ErrorAuditDAO();
//		ErrorCodeAuditDAO errorCodeDAO = new ErrorCodeAuditDAO();
//
//		ArrayList<ErrorAuditDetails> allErrorsWithDetails = new ArrayList<ErrorAuditDetails>();
//		ArrayList<ErrorAuditRecord> allErrors = errorDAO.getAll();
//
//		for (ErrorAuditRecord errorAudit : allErrors) {
//			ArrayList<ErrorCodeAuditRecord> errorCodePortions = errorCodeDAO.getByErrorAuditID(errorAudit.getId());
//			ErrorAuditDetails errorAuditDetails = new ErrorAuditDetails();
//			errorAuditDetails.setErrorAudit(errorAudit);
//			errorAuditDetails.setErrorCodePortions(errorCodePortions);
//			allErrorsWithDetails.add(errorAuditDetails);
//		}
//
//		return allErrorsWithDetails;
//	}

	public ArrayList<RelatedSystemErrors> getAllBySystem() {
		RelatedSystemController relatedSystemController = new RelatedSystemController();
		ErrorAuditDAO errorDAO = new ErrorAuditDAO();

		ArrayList<RelatedSystemRecord> allStoredRelatedSystems = relatedSystemController.getAll();
		ArrayList<ErrorAuditRecord> allErrors = errorDAO.getAll();
		ArrayList<RelatedSystemErrors> allDiscoveredErrorsBySystem = new ArrayList<RelatedSystemErrors>();

		
		int tenMinuteCount = 0;
		int thirtyMinuteCount = 0;
		int oneHourCount = 0;
		int eightHourCount = 0;
		int twentyFourHourCount = 0;
		int sevenDayCount = 0;
		int twentyEightDayCount = 0;

		int tenMinutes = 1000 * 60 * 10;
		int thirtyMinutes = tenMinutes * 3;
		int oneHour = thirtyMinutes * 2;
		int eightHours = oneHour * 8;
		int twentyFourHours = eightHours * 3;
		int sevenDays = twentyFourHours * 7;
		int twentyEightDays = sevenDays * 4;
		
		
		for (RelatedSystemRecord storedRelatedSystem_iter : allStoredRelatedSystems) {
			//For each system stored
			
			RelatedSystemErrors systemWithErrorDetails = new RelatedSystemErrors();
			ArrayList<ErrorAuditDetails>  errorsForTheSystem;
			boolean newSystem = true;
			for (RelatedSystemErrors errorsBySystemIter : allDiscoveredErrorsBySystem) {
				if (storedRelatedSystem_iter.getSystem().equalsIgnoreCase(errorsBySystemIter.getSystem())) {			
					systemWithErrorDetails = errorsBySystemIter;
					newSystem = false;
					
				}
			}

			if (newSystem) {
				systemWithErrorDetails.setSystem(storedRelatedSystem_iter.getSystem());
				systemWithErrorDetails.setErrorAuditDetails(new ArrayList<ErrorAuditDetails>());
			}
			
			ArrayList<ErrorAuditDetails> errors = systemWithErrorDetails.getErrorAuditDetails();
	
			for (ErrorAuditRecord errorAudit : allErrors) {
				if (errorAudit.getUniqueErrorID() == storedRelatedSystem_iter.getUniqueErrorID()) {
					//This is another error that matches the system			
					ErrorAuditDetails errorAuditDetails = new ErrorAuditDetails();
					errorAuditDetails.setLineOfBusines(lobController.getByID(errorAudit.getLobID()));
					errorAuditDetails.setProduct(productController.getByID(errorAudit.getProductID()));
					errorAuditDetails.setFunction(functionController.getByID(errorAudit.getFunctionID()));
					errorAuditDetails.setUniqueError((uniqueIDController.getByID(errorAudit.getUniqueErrorID())));
					errorAuditDetails.setErrorAudit(errorAudit);
					errors.add(errorAuditDetails);
					
					
					Date dateAdded = errorAudit.getDateAdded();
					Date thisMoment = new Date();

					Long then = dateAdded.getTime();
					Long now = thisMoment.getTime();

					if (withinTheTime(now, then, tenMinutes)) {
						tenMinuteCount++;
						systemWithErrorDetails.setTenMinuteCount(tenMinuteCount);
					}
					if (withinTheTime(now, then, thirtyMinutes)) {
						thirtyMinuteCount++;
						systemWithErrorDetails.setThirtyMinuteCount(thirtyMinuteCount);
					}
					if (withinTheTime(now, then, oneHour)) {
						oneHourCount++;
						systemWithErrorDetails.setOneHourCount(oneHourCount);
					}
					if (withinTheTime(now, then, eightHours)) {
						eightHourCount++;
						systemWithErrorDetails.setEightHourCount(eightHourCount);
					}
					if (withinTheTime(now, then, twentyFourHours)) {
						twentyFourHourCount++;
						systemWithErrorDetails.setTwentyFourHourCount(twentyFourHourCount);
					}
					if (withinTheTime(now, then, sevenDays)) {
						sevenDayCount++;
						systemWithErrorDetails.setSevenDayCount(sevenDayCount);
					}
					if (withinTheTime(now, then, twentyEightDays)) {
						twentyEightDayCount++;
						systemWithErrorDetails.setTwentyEightDayCount(twentyEightDayCount);
					}
					
				}
			}
			
			if (newSystem) {
				allDiscoveredErrorsBySystem.add(systemWithErrorDetails);
			}
			

			

		}

		return allDiscoveredErrorsBySystem;

	}

	public ArrayList<ErrorAuditDetails> getAudits(String beginsWith, boolean includeAllDetail) {

		ErrorAuditDAO errorDAO = new ErrorAuditDAO();
		ArrayList<ErrorAuditRecord> allErrors = errorDAO.getAll();

		ArrayList<ErrorAuditDetails> allErrorsWithDetails = new ArrayList<ErrorAuditDetails>();

		for (ErrorAuditRecord errorAudit : allErrors) {

			boolean include = true;
			if (beginsWith != null) {
				if (errorAudit.getFullCode().startsWith(beginsWith)) {

				} else {
					include = false;
				}
			}

			ErrorAuditDetails errorAuditDetails = new ErrorAuditDetails();

			if (include) {
				if (includeAllDetail) {
					errorAuditDetails.setLineOfBusines(lobController.getByID(errorAudit.getLobID()));
					errorAuditDetails.setProduct(productController.getByID(errorAudit.getProductID()));
					errorAuditDetails.setFunction(functionController.getByID(errorAudit.getFunctionID()));
					errorAuditDetails.setUniqueError((uniqueIDController.getByID(errorAudit.getUniqueErrorID())));
				}

				errorAuditDetails.setErrorAudit(errorAudit);
				allErrorsWithDetails.add(errorAuditDetails);
			}
		}

		return allErrorsWithDetails;
	}

	private boolean withinTheTime(long currentTime, long eventTime, long timeRange) {
		long bottomRange = currentTime - timeRange;
		return eventTime > bottomRange;
	}

	private double trend(int currentRangeCount, int nextRangeCount, int multiplier) {
		int onPar = currentRangeCount * multiplier;
		if (nextRangeCount == 0 && currentRangeCount > 0) {
			return 1;
		}

		if (nextRangeCount == 0 && currentRangeCount == 0) {
			return 0;
		}

		double trend = (double) onPar / (double) nextRangeCount;
		return trend;
	}

	public ErrorAuditReport getErrorMappedDetails(String startsWith) {
		ErrorAuditReport errorAuditReport = new ErrorAuditReport();
		ArrayList<ErrorAuditDetails> errors = getAudits(startsWith, true);
		errorAuditReport.setErrors(errors);

		int tenMinuteCount = 0;
		int thirtyMinuteCount = 0;
		int oneHourCount = 0;
		int eightHourCount = 0;
		int twentyFourHourCount = 0;
		int sevenDayCount = 0;
		int twentyEightDayCount = 0;

		int tenMinutes = 1000 * 60 * 10;
		int thirtyMinutes = tenMinutes * 3;
		int oneHour = thirtyMinutes * 2;
		int eightHours = oneHour * 8;
		int twentyFourHours = eightHours * 3;
		int sevenDays = twentyFourHours * 7;
		int twentyEightDays = sevenDays * 4;

		for (ErrorAuditDetails error : errors) {
			Date dateAdded = error.getErrorAudit().getDateAdded();
			Date thisMoment = new Date();

			Long then = dateAdded.getTime();
			Long now = thisMoment.getTime();

			if (withinTheTime(now, then, tenMinutes)) {
				tenMinuteCount++;
			}
			if (withinTheTime(now, then, thirtyMinutes)) {
				thirtyMinuteCount++;
			}
			if (withinTheTime(now, then, oneHour)) {
				oneHourCount++;
			}
			if (withinTheTime(now, then, eightHours)) {
				eightHourCount++;
			}
			if (withinTheTime(now, then, twentyFourHours)) {
				twentyFourHourCount++;
			}
			if (withinTheTime(now, then, sevenDays)) {
				sevenDayCount++;
			}
			if (withinTheTime(now, then, twentyEightDays)) {
				twentyEightDayCount++;
			}
		}

		errorAuditReport.setTenMinuteCount(tenMinuteCount);
		errorAuditReport.setThirtyMinuteCount(thirtyMinuteCount);
		errorAuditReport.setOneHourCount(oneHourCount);
		errorAuditReport.setEightHourCount(eightHourCount);
		errorAuditReport.setTwentyFourHourCount(twentyFourHourCount);
		errorAuditReport.setSevenDayCount(sevenDayCount);
		errorAuditReport.setTwentyEightDayCount(twentyEightDayCount);

		errorAuditReport.setTenMinuteTrend(trend(tenMinuteCount, thirtyMinuteCount, 3));
		errorAuditReport.setThirtyMinuteTrend(trend(thirtyMinuteCount, oneHourCount, 2));
		errorAuditReport.setOneHourTrend(trend(oneHourCount, eightHourCount, 8));
		errorAuditReport.setEightHourTrend(trend(eightHourCount, twentyFourHourCount, 3));
		errorAuditReport.setTwentyFourHourTrend(trend(twentyFourHourCount, sevenDayCount, 7));
		errorAuditReport.setSevenDayTrend(trend(sevenDayCount, twentyEightDayCount, 4));

		return errorAuditReport;

	}

//	private String getValueForSegmentType(ErrorAuditDetails details, int segmentTypeID) {
//		for (ErrorCodeAuditRecord portion : details.getErrorCodePortions()) {
//			if (segmentTypeID == portion.getSegmentTypeID()) {
//				return portion.getCode();
//			}
//		}
//		return "";
//	}

//	public HashMap<String, ArrayList<ErrorAuditDetails>> getMap(int level) {
//		ArrayList<ErrorAuditDetails> errors = getAuditDetails();
//		// int levels = 4;
//		// for (int level = 0; level < levels; level++) {
//
//		// int level = 1;
//		HashMap<String, ArrayList<ErrorAuditDetails>> errorsWithCode = new HashMap<String, ArrayList<ErrorAuditDetails>>();
//		for (ErrorAuditDetails error : errors) {
//			String code = getValueForSegmentType(error, level);
//			ArrayList<ErrorAuditDetails> existingErrorsWithCode;
//			if (errorsWithCode.containsKey(code)) {
//				existingErrorsWithCode = errorsWithCode.get(code);
//			} else {
//				existingErrorsWithCode = new ArrayList<ErrorAuditDetails>();
//
//			}
//			existingErrorsWithCode.add(error);
//			errorsWithCode.put(code, existingErrorsWithCode);
//		}
//
//		return errorsWithCode;
//
//	}

	private ErrorCodeAuditRecord buildErrorCodeAuditRecordObject(int masterID, String value, int segmentTypeID) {
		ErrorCodeAuditRecord rec = new ErrorCodeAuditRecord();
		rec.setCode(value);
		rec.setSegmentTypeID(segmentTypeID);
		rec.setErrorID(masterID);
		return rec;
	}

}
