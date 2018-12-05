package network;

import java.net.Authenticator;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.util.Base64;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;


import properties.Local;

public class CommonNetwork {

	private int port = Local.port;
	private String proxyName = Local.proxyName;
	SSLSocketFactory originalFactory;
	HostnameVerifier originalVerfier;
	CookieHandler originalCookieHandler;

	public long getCurrentTime() {
		return System.currentTimeMillis();
	}

	public long getGeneratedHiddenTime() {
		return getCurrentTime() + 1209600000;
	}

	
	public void setSystemPreferences() {
		System.setProperty("http.proxyHost", proxyName);
		System.setProperty("http.proxyPort", port + "");
		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		System.setProperty("java.net.useSystemProxies", "true");
	}

	public void setProxyPreferences() {
		System.setProperty("http.proxyUser", Local.user);
		System.setProperty("http.proxyPassword", Local.pass);
		
		  Authenticator.setDefault(
		          new Authenticator() {
		            public PasswordAuthentication getPasswordAuthentication() {
		              return new PasswordAuthentication(
		            		  Local.user, Local.pass.toCharArray()
		              );
		            }
		           }
		         );
		            
	}
	
	public Proxy getProxy() {
		return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyName, port));
	}

	public void acceptCookies() {
		originalCookieHandler = CookieHandler.getDefault();
		CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
	}

	public void dontAcceptCookies() {
		CookieHandler.setDefault(originalCookieHandler);
	}

	public void trustAllCertificates() {
		try {
			originalFactory = javax.net.ssl.HttpsURLConnection.getDefaultSSLSocketFactory();
			originalVerfier = javax.net.ssl.HttpsURLConnection.getDefaultHostnameVerifier();

			javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[] {
					new javax.net.ssl.X509TrustManager() {
						public java.security.cert.X509Certificate[] getAcceptedIssuers() {
							return null;
						}

						public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
								throws java.security.cert.CertificateException {
							return;
						}

						public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
								throws java.security.cert.CertificateException {
							return;
						}
					} };
			javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			javax.net.ssl.HostnameVerifier hv = new javax.net.ssl.HostnameVerifier() {
				public boolean verify(String urlHostName, javax.net.ssl.SSLSession session) {
					if (!urlHostName.equalsIgnoreCase(session.getPeerHost())) {
					
					}
					return true;
				}
			};
			javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(hv);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void dontTrustAllCertificates() {
		javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(originalFactory);
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(originalVerfier);
	}

	public String getEncryptedAuthentication(String user, String pass){
		String toEncode = user + ":" + pass;
		String encrypted = Base64.getEncoder().encodeToString(toEncode.getBytes());

		return encrypted;
	}
	
	
}