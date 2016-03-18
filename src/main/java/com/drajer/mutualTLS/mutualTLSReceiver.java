package com.drajer.mutualTLS;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.SSLSocket;

public class mutualTLSReceiver {
	
	static String keyStoreLocation = "C:/Projects/NIST/keyAndCert/keystore";
	static String trustStoreLocation = "C:/Projects/NIST/keyAndCert/clientTrustStore";
	
	public static void main(String[] args) {
		
		setSystemProperties();
		SSLSocketFactory sslsocketfactory =(SSLSocketFactory) SSLSocketFactory.getDefault();  
        try {  
        	SSLSocket sslsocket = (SSLSocket) sslsocketfactory.createSocket(
                    "52.91.99.59", 8080);

        	sslsocket.startHandshake(); 
        	
        	
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
