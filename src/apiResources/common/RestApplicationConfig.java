package apiResources.common;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class RestApplicationConfig extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(CORSResponseFilter.class);
		return classes;
	}
}