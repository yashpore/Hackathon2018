package properties;


import java.util.Stack;

public class Local {

	/*
	 * This class is the current place holder for all system
	 * properties
	 */
	

	
	public static final int ROOT = 0;
	public static final int LineOfBusiness = 1;
	public static final int Product = 2;
	public static final int Function = 3;
	public static final int UniqueID = 4;
	
	
	
	//Database connection properties
	public static final int mongo_local_port = 27017;
	public static final String mongo_local_host = "10.146.194.27";
	public static final String mongo_local_schema = "Hackathon";


	
	public static final int port = 8080;
	public static final String proxyName = "authproxy.pncbank.com";
	
	public static String user = "";
	public static String pass = "";
	
	//TODO These should get moved to the database
	public static boolean batchPaused = true;
	public static boolean batchInitiated = false;
	public static Stack<String> last50Logs = new Stack<String>();
	public static Stack<String> last10Errors = new Stack<String>();

}
