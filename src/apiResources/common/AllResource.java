
package apiResources.common;

import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

@Path("/api")
public class AllResource {

	Gson gson = new Gson();

	@POST
	@Path("/init")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateStatus() {
		// SegmentItemController controller = new SegmentTypeController();
		// controller.insertDefaultErrorCodeSegmentTypes();
		String output = gson.toJson("done");
		return Response.status(200).entity(output).build();
	}

	@GET
	@Path("/getAllActiveIncidents")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllActiveIncidents() {

		HttpGet request;
		CloseableHttpResponse response = null;
		CloseableHttpClient client = null;
		String output = "";
		try {
			CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
			Credentials credentials = new UsernamePasswordCredentials("harshal.mulherkar", "PNc!12345");
			credentialsProvider.setCredentials(AuthScope.ANY, credentials);
			client = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();
			URIBuilder uriBuilder = new URIBuilder("https://pncmelliniumfalcon.service-now.com/api/now/table/incident");
			uriBuilder.addParameter("sysparm_query", "active%3Dtrue%5Ecategory!%3Dinquiry%5Eincident_state%3D2")
					.addParameter("sysparm_fields",
							"parent%2Ccaused_by%2Cnumber%2Cstate%2Cu_mnemonic%2Csys_created_by%2Ccmdb_ci%2Cimpact%2Cactive%2Cpriority%2Cshort_description%2Cwork_start%2Cparent_incident%2Cassigned_to%2Cuser_input%2Ccaller_id%2Curgency%2Ccategory")
					.addParameter("sysparm_limit", "10");

			URI uri = uriBuilder.build();
			System.out.println(uri.toString());
			request = new HttpGet(uri);
			request.addHeader("Accept", "application/json");
			request.addHeader("Content-Type", "application/json");
			request.addHeader("Postman-Token", "00280249-0a32-414c-8d0f-c3750b2b436b");
			System.out.println(request.getRequestLine());
			response = client.execute(request);
			output = gson.toJson(EntityUtils.toString(response.getEntity()));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null)
					response.close();
				if (client != null)
					client.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return Response.status(200).entity(output).build();
	}

}
