package controllers.common;

import java.util.ArrayList;

import database.databaseRecordPojos.common.FunctionRecord;
import database.databaseRecordPojos.common.LineOfBusinessRecord;
import database.databaseRecordPojos.common.ProductRecord;
import database.databaseRecordPojos.common.RelatedSystemRecord;
import database.databaseRecordPojos.common.UniqueIDRecord;

public class FullStructureMappingController {

	LineOfBusinessController lineOfBusinessController = new LineOfBusinessController();
	ProductController productController = new ProductController();
	FunctionController functionController = new FunctionController();
	UniqueIDController uniqueIDController = new UniqueIDController();
	RelatedSystemController relatedSystemController = new RelatedSystemController();
	
	public ArrayList<LineOfBusinessRecord> getMap() {

		ArrayList<LineOfBusinessRecord> lobs = lineOfBusinessController.getAll();
		for (LineOfBusinessRecord lob : lobs) {
			String fullErrorCode = lob.getCode();
			lob.setFullCodeSoFar(fullErrorCode);

			ArrayList<ProductRecord> products = productController.getAllByLineOfBusinessID(lob.getId());
			for (ProductRecord product : products) {
				fullErrorCode = lob.getCode() + product.getCode();
				product.setFullCodeSoFar(fullErrorCode);

				ArrayList<FunctionRecord> functions = functionController.getAllByProductID(product.getId());
				for (FunctionRecord function : functions) {
					fullErrorCode = lob.getCode() + product.getCode() + function.getCode();
					function.setFullCodeSoFar(fullErrorCode);

					ArrayList<UniqueIDRecord> ids = uniqueIDController.getAllByFunctionID(function.getId());
					for (UniqueIDRecord id : ids) {
						fullErrorCode = lob.getCode() + product.getCode() + function.getCode() + id.getCode();
						id.setFullCodeSoFar(fullErrorCode);
						
						
						ArrayList<RelatedSystemRecord> relatedSystems = relatedSystemController.getAllByUniqueErrorID(id.getId());
						id.setRelatedSystems(relatedSystems);
					}

					function.setUniqueErrorCodes(ids);
				}
				product.setFunctions(functions);
			}
			lob.setProducts(products);

		}
		return lobs;
	}

	//
}
