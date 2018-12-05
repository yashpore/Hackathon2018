
package apiResources.common;

import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import apiResources.ResponsePojos.BadResponse;
import apiResources.requestPojos.AuditError;
import controllers.common.AssignedPersonController;
import controllers.common.AuditErrorController;
import controllers.common.FullStructureMappingController;
import controllers.common.FunctionController;
import controllers.common.LineOfBusinessController;
import controllers.common.ProductController;
import controllers.common.RelatedSystemController;
import controllers.common.UniqueIDController;
import controllers.pojos.ErrorAuditReport;
import controllers.pojos.RelatedSystemErrors;
import database.databaseRecordPojos.common.AssignedPersonRecord;
import database.databaseRecordPojos.common.FunctionRecord;
import database.databaseRecordPojos.common.LineOfBusinessRecord;
import database.databaseRecordPojos.common.ProductRecord;
import database.databaseRecordPojos.common.RelatedSystemRecord;
import database.databaseRecordPojos.common.UniqueIDRecord;

@Path("/api")
public class AllResource {

	Gson gson = new Gson();

	
	private String getBadRequestResponse() {
		BadResponse bad = new BadResponse();
		bad.setCode(400);
		bad.setError(true);
		bad.setMessage("The request could not be completed. There was an issue with the request");
		String output = gson.toJson(bad);
				return output;
	}
	
	@GET
	@Path("/fullyMappedErrorStructure")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFullStructureMapping() {
		FullStructureMappingController controller = new FullStructureMappingController();
		ArrayList<LineOfBusinessRecord> response = controller.getMap();
		String output = gson.toJson(response);
		return Response.status(200).entity(output).build();
	}

	@GET
	@Path("/getLineOfBusinessDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLineOfBusinessDetails() {
		LineOfBusinessController controller = new LineOfBusinessController();
		ArrayList<LineOfBusinessRecord> response = controller.getAll();
		String output = gson.toJson(response);
		return Response.status(200).entity(output).build();
	}

	@GET
	@Path("/getProductDetailsForLineOfBusiness/{lobID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductDetails(@PathParam("lobID") int lobID) {
		ProductController controller = new ProductController();
		ArrayList<ProductRecord> response = controller.getAllByLineOfBusinessID(lobID);
		String output = gson.toJson(response);
		return Response.status(200).entity(output).build();
	}

	@GET
	@Path("/getUniqueErrorDetailsForFunction/{functionID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUniqueIDDetails(@PathParam("functionID") int functionID) {
		UniqueIDController controller = new UniqueIDController();
		ArrayList<UniqueIDRecord> response = controller.getAllByFunctionID(functionID);
		String output = gson.toJson(response);
		return Response.status(200).entity(output).build();
	}

	@GET
	@Path("/getFunctionDetailsForProduct/{productID}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getFunctionDetails(@PathParam("productID") int productID) {
		FunctionController controller = new FunctionController();
		ArrayList<FunctionRecord> response = controller.getAllByProductID(productID);
		String output = gson.toJson(response);
		return Response.status(200).entity(output).build();
	}

	@POST
	@Path("/addLineOfBusinessDetail")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createLineOfBusinessDetail(LineOfBusinessRecord record) {
		LineOfBusinessController controller = new LineOfBusinessController();
		LineOfBusinessRecord response = controller.insertLineOfBusiness(record);
		String output = gson.toJson(response);
		return Response.status(200).entity(output).build();
	}

	@POST
	@Path("/addProductDetailForLineOfBusiness")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProductDetail(ProductRecord record) {
		ProductController controller = new ProductController();
		ProductRecord response = controller.insertProduct(record);
		String output = gson.toJson(response);
		return Response.status(200).entity(output).build();
	}

	@POST
	@Path("/addFunctionDetailForProduct")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createFunctionDetail(FunctionRecord record) {
		FunctionController controller = new FunctionController();
		FunctionRecord response = controller.insertFunction(record);
		String output = gson.toJson(response);
		return Response.status(200).entity(output).build();
	}

	@POST
	@Path("/addUniqueErrorDetailForFunction")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUniqueIDDetail(UniqueIDRecord record) {
		UniqueIDController controller = new UniqueIDController();
		UniqueIDRecord response = controller.insertFunction(record);
		String output = gson.toJson(response);
		return Response.status(200).entity(output).build();
	}

	@DELETE
	@Path("/lineOfBusiness/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createLineOfBusinessDetail(@PathParam("id") int id) {
		LineOfBusinessController controller = new LineOfBusinessController();
		controller.deleteLineOfBusinessRecord(id);
		String output = gson.toJson("tbd");
		return Response.status(200).entity(output).build();
	}

	@DELETE
	@Path("/product/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProductDetail(@PathParam("id") int id) {
		ProductController controller = new ProductController();
		controller.deleteProductRecord(id);
		;
		String output = gson.toJson("tbd");
		return Response.status(200).entity(output).build();
	}

	@DELETE
	@Path("/function/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteFunctionDetail(@PathParam("id") int id) {
		FunctionController controller = new FunctionController();
		controller.deleteFunctionRecord(id);
		String output = gson.toJson("tbd");
		return Response.status(200).entity(output).build();
	}

	@DELETE
	@Path("/uniqueError/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUniqueIDDetail(@PathParam("id") int id) {
		UniqueIDController controller = new UniqueIDController();
		controller.deleteFunctionRecord(id);
		String output = gson.toJson("tbd");
		return Response.status(200).entity(output).build();
	}

// Assign person
	@POST
	@Path("/assignPerson")
	@Produces(MediaType.APPLICATION_JSON)
	public Response assignPerson(AssignedPersonRecord record) {
		AssignedPersonController controller = new AssignedPersonController();
		AssignedPersonRecord response = controller.insert(record);
		String output = gson.toJson(response);
		return Response.status(200).entity(output).build();
	}
	
	@POST
	@Path("/unassignPerson/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response unassignPerson(@PathParam("id") int id) {
		AssignedPersonController controller = new AssignedPersonController();
		controller.delete(id);
		String output = gson.toJson("tbd");
		return Response.status(200).entity(output).build();
	}
	
	
	@GET
	@Path("/assignedPersons/lob/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAssignedPersonLOB(@PathParam("id") int id) {
		AssignedPersonController controller = new AssignedPersonController();
		ArrayList<AssignedPersonRecord> response = controller.getByLineOfBusiness(id);
		String output = gson.toJson(response);
		return Response.status(200).entity(output).build();
	}
	
	@GET
	@Path("/assignedPersons/product/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAssignedPersonProduct(@PathParam("id") int id) {
		AssignedPersonController controller = new AssignedPersonController();
		ArrayList<AssignedPersonRecord> response = controller.getByProduct(id);
		String output = gson.toJson(response);
		return Response.status(200).entity(output).build();
	}
	
	@GET
	@Path("/assignedPersons/function/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAssignedPersonFunction(@PathParam("id") int id) {
		AssignedPersonController controller = new AssignedPersonController();
		ArrayList<AssignedPersonRecord> response = controller.getByFunction(id);
		String output = gson.toJson(response);
		return Response.status(200).entity(output).build();
	}
	
	@GET
	@Path("/assignedPersons/uniqueError/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAssignedPersonUID(@PathParam("id") int id) {
		AssignedPersonController controller = new AssignedPersonController();
		ArrayList<AssignedPersonRecord> response = controller.getByUniqueError(id);
		String output = gson.toJson(response);
		return Response.status(200).entity(output).build();
	}
////
	
	
	@POST
	@Path("/associateSystem")
	@Produces(MediaType.APPLICATION_JSON)
	public Response associateSystem(RelatedSystemRecord record) {
		RelatedSystemController controller = new RelatedSystemController();
		RelatedSystemRecord response = controller.insertFunction(record);
		String output = gson.toJson(response);
		return Response.status(200).entity(output).build();
	}
	
	/////
	
	
	@POST
	@Path("/auditError")
	@Produces(MediaType.APPLICATION_JSON)
	public Response audit(AuditError auditError) {
		AuditErrorController controller = new AuditErrorController();
		
		if (auditError == null || auditError.getCode() == null || auditError.getCode().length() != 13) {
			return Response.status(400).entity(getBadRequestResponse()).build();
		}
		
		controller.auditError(auditError);
		String output = gson.toJson("tbd");
		return Response.status(200).entity(output).build();
	}

	@GET
	@Path("/error")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getErrorsBySegmentTypeID(@QueryParam("startsWith") String startsWith) {
		AuditErrorController controller = new AuditErrorController();
		ErrorAuditReport response = controller.getErrorMappedDetails(startsWith);
		String output = gson.toJson(response);
		return Response.status(200).entity(output).build();
	}
	
	@GET
	@Path("/errorBySystem")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getErrorsBySystem() {
		AuditErrorController controller = new AuditErrorController();
		 ArrayList<RelatedSystemErrors> response = controller.getAllBySystem();
		String output = gson.toJson(response);
		return Response.status(200).entity(output).build();
	}
	

	///////


	

		
		
		
	@POST
	@Path("/init")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateStatus() {
		// SegmentItemController controller = new SegmentTypeController();
		// controller.insertDefaultErrorCodeSegmentTypes();
		String output = gson.toJson("done");
		return Response.status(200).entity(output).build();
	}

}
