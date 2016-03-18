package com.drajer.mutualTLS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class MutualTLS {

	static String keyStoreLocation = "C:/Projects/Dragon/NIST/keyAndCert/keyAndCert/bad.jks";
	static String trustStoreLocation = "C:/Projects/Dragon/NIST/keyAndCert/keyAndCert/clientTrustStore";
	
	public static void main(String[] args) {
		
		setSystemProperties();
		SSLSocketFactory sslsocketfactory =(SSLSocketFactory) SSLSocketFactory.getDefault();  
        try {  
        	SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(
                    "52.91.99.59", 8080);

        	sslsocket.startHandshake(); 
        	
        	/*URL url = new URL("https://52.91.99.59:8080");  
             HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();  
             //Diabled hostname verifier so that Java wont match hostnames with Cert names.
             conn.setHostnameVerifier(new HostnameVerifier() {
				
				public boolean verify(String arg0, SSLSession arg1) {
					// TODO Auto-generated method stub
					return true;
				}
			}); 
             conn.setSSLSocketFactory(sslsocketfactory);  
             InputStream inputstream = conn.getInputStream();  
             InputStreamReader inputstreamreader = new InputStreamReader(inputstream);  
             BufferedReader bufferedreader = new BufferedReader(inputstreamreader);  
             String string = null;  
             while ((string = bufferedreader.readLine()) != null) {  
               //System.out.println("Received " + string);  
             }  */
        } catch (MalformedURLException e) {  
             e.printStackTrace();  
        } catch (IOException e) {  
             e.printStackTrace();  
        }  
   }  
	
	
	public static void setSystemProperties()
	{
		 System.setProperty("javax.net.ssl.trustStoreType", "jks");
		 System.setProperty("javax.net.ssl.keyStore", keyStoreLocation);
		 System.setProperty("javax.net.ssl.trustStore", trustStoreLocation);
		 System.setProperty("javax.net.debug", "ssl");
		 System.setProperty("javax.net.ssl.keyStorePassword", "changeit");
		 System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
	}

}
