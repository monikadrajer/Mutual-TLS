package com.drajer.mutualTLS;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;

public class MutualTLSServer {

	public static void main(String[] args) {
		
		 String keyStoreLocation = "C:/Projects/Dragon/NIST/TestCert/server.jks";
		 String trustStoreLocation = "C:/Projects/Dragon/NIST/TestCert/serverTrsutStore";
		
		try
		{
			
			System.setProperty("javax.net.debug", "all");
			System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
			KeyStore serverKeystore = KeyStore.getInstance("JKS");
			serverKeystore.load(new FileInputStream(keyStoreLocation),"changeit".toCharArray());
            KeyManagerFactory serverKeyManager = KeyManagerFactory.getInstance("SunX509");
            serverKeyManager.init(serverKeystore,"changeit".toCharArray());


            KeyStore serverTrustStore = KeyStore.getInstance("JKS");
            serverTrustStore.load(new FileInputStream(trustStoreLocation),"changeit".toCharArray());
            TrustManagerFactory trustManager = TrustManagerFactory.getInstance("SunX509");
            trustManager.init(serverTrustStore);

            //use keys to create SSLSoket
            SSLContext ssl = SSLContext.getInstance("TLS");
            ssl.init(serverKeyManager.getKeyManagers(), trustManager.getTrustManagers(), SecureRandom.getInstance("SHA1PRNG"));
            SSLServerSocket serverSock = (SSLServerSocket)ssl.getServerSocketFactory().createServerSocket(8095);
            serverSock.setNeedClientAuth(true);
            SSLSocket socket = (SSLSocket)serverSock.accept();
		}
		catch (IOException ioe){
			System.out.println("Exception occured :" + ioe.getMessage());
			ioe.printStackTrace();
		}
		catch (KeyManagementException kme){
			System.out.println("Exception occured :" + kme.getMessage());
			kme.printStackTrace();
		}
		catch (NoSuchAlgorithmException nse) {
			System.out.println("Exception occured :" + nse.getMessage());
			nse.printStackTrace();
		}
		catch (CertificateException ce) {
			System.out.println("Exception occured :" + ce.getMessage());
			ce.printStackTrace();
		}
		catch (KeyStoreException kse) {
			System.out.println("Exception occured :" + kse.getMessage());
			kse.printStackTrace();
		}
		catch (UnrecoverableKeyException urke) {
			System.out.println("Exception occured :" + urke.getMessage());
			urke.printStackTrace();
		}

	}

}
