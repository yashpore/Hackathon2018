package apiResources.common;

import org.glassfish.jersey.server.ResourceConfig;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("restservices")
public class RestApplication extends ResourceConfig {
	public RestApplication() {
		packages("apiResources");
	}
}