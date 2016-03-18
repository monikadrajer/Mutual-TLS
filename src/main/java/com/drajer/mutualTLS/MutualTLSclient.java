package com.drajer.mutualTLS;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManagerFactory;

public class MutualTLSclient {

	public static void main(String[] args) {
		
		 String keyStoreLocation = "C:/Projects/Dragon/NIST/TestCert/client.jks";
		 String trustStoreLocation = "C:/Projects/Dragon/NIST/TestCert/clientTrustStore";
		
		try
		{
		
			System.setProperty("javax.net.debug", "all");
			System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
			//load client private key
		    KeyStore clientKeyStore = KeyStore.getInstance("JKS");
		    clientKeyStore.load(new FileInputStream(keyStoreLocation),"changeit".toCharArray());
		    KeyManagerFactory clientKeyManager = KeyManagerFactory.getInstance("SunX509");
		    clientKeyManager.init(clientKeyStore,"changeit".toCharArray());
		    //load server public key
		    KeyStore clientTrustStore = KeyStore.getInstance("JKS");
		    clientTrustStore.load(new FileInputStream(trustStoreLocation),"changeit".toCharArray());
		    TrustManagerFactory trustManager = TrustManagerFactory.getInstance("SunX509");
		    trustManager.init(clientTrustStore);
		    
		    //use keys to create SSLSoket
		    SSLContext ssl = SSLContext.getInstance("TLS");
		    ssl.init(clientKeyManager.getKeyManagers(), trustManager.getTrustManagers(), SecureRandom.getInstance("SHA1PRNG"));
		    SSLSocket socket = (SSLSocket)ssl.getSocketFactory().createSocket("localhost", 8095);
		    socket.startHandshake();
		    socket.addHandshakeCompletedListener(getHandShakeListner());
		}
		catch (ConnectException ce) {
			System.out.println("Exception occured :" + ce.getMessage());
			ce.printStackTrace();
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
	
	private static HandshakeCompletedListener getHandShakeListner()
	{
		return new HandshakeCompletedListener() {
			
			public void handshakeCompleted(
					HandshakeCompletedEvent paramHandshakeCompletedEvent) {
				// TODO Auto-generated method stub
				System.out.println("Handshake completed");
				
			}
		};
	}

}
