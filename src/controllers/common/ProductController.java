package controllers.common;

import java.util.ArrayList;

import database.DAO.common.ProductDAO;
import database.databaseRecordPojos.common.ProductRecord;

public class ProductController {

	 ProductDAO productDAO = new  ProductDAO();
	
	public ArrayList<ProductRecord> getAllByLineOfBusinessID(int lobID) {
		return  productDAO.getAllByLOB(lobID);
	}

	public ProductRecord getByID(int id) {
		return productDAO.getByID(id);
	}
	
	public  ProductRecord insertProduct( ProductRecord record) {
		return productDAO.insertProduct(record);
	}
	
	public void deleteProductRecord(int id) {
		productDAO.deleteProductRecord(id);
	}
}
