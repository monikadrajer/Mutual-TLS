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
import javax.net.ssl.SSLSocketFactory;

public class MutualTLSSender {

	static String keyStoreLocation = "C:/Projects/NIST/keyAndCert/keystore";
	static String trustStoreLocation = "C:/Projects/NIST/keyAndCert/clientTrustStore";
	
	public static void main(String[] args) {
		
		setSystemProperties();
		SSLSocketFactory sslsocketfactory =(SSLSocketFactory) SSLSocketFactory.getDefault();  
        try {  
        	
        	URL url = new URL("https://hit-dev.nist.gov:11084/xdstools3/sim/ett/6/docrec/prb");  
            
            HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();  
            
            //Diabled hostname verifier so that Java wont match hostnames with Cert names.
             
            conn.setHostnameVerifier(new HostnameVerifier() {
				
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}
			}); 
             
            conn.setSSLSocketFactory(sslsocketfactory);  
            InputStream inputstream = conn.getInputStream();  
            InputStreamReader inputstreamreader = new InputStreamReader(inputstream);  
            BufferedReader bufferedreader = new BufferedReader(inputstreamreader);  
            String string = null;  
            while ((string = bufferedreader.readLine()) != null) {  
               System.out.println("Received " + string);  
             }  
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
