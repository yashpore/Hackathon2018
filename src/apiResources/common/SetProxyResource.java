package apiResources.common;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import properties.Local;

@Path("/proxy")
public class SetProxyResource {

	Gson gson = new Gson();

	@PUT
	@Path("/set")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCurrent(String data, @HeaderParam("username") String username,
			@HeaderParam("password") String password) {
		Local.user = username;
		Local.pass = password;
		String output = gson.toJson("true");
		return Response.status(200).entity(output).build();
	}

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCurrent() {
		String output = "User is: " + Local.user;
		return Response.status(200).entity(output).build();
	}
	
}
