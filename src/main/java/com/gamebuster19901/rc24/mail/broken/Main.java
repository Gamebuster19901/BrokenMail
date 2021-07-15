package com.gamebuster19901.rc24.mail.broken;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMultipart;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class Main {

	public static final Logger LOGGER = Logger.getLogger("mail");
	public static final String SERVER = "rc24.xyz";
	
	public static void main(String[] args) throws IOException, MessagingException, InterruptedException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		send2();
		receive();
	}
	
	public static void send() {
		for(int i = 65; i < 66; i++) {
			CloseableHttpClient client = HttpClients.createDefault();
			File secretFile = new File("./mail.secret");
			String wiiID;
			String password;
			HttpPost request;
			HttpPost dupe;
			BufferedReader fileReader = null;
			try {
				fileReader = new BufferedReader(new FileReader(secretFile));
				wiiID = fileReader.readLine();
				password = fileReader.readLine();
				request = new HttpPost("https://mtw." + SERVER + "/cgi-bin/send.cgi?mlid=" + wiiID + "&passwd=" + password + "&maxsize=11534336");
				
				String from = wiiID + "@rc24.xyz";
				String to = "w0633303276188884@rc24.xyz";
				
				request.addHeader("Content-Type", "multipart/form-data; boundary=t9Sf4yfjf1RtvDu3AA");
				
				String s = 					
						"Content-Disposition: form-data; name=\"mlid\"\r\n"
						+ "mlid="+wiiID+"\r\n"
						+ "passwd="+password+"\r\n"
						+ "--t9Sf4yfjf1RtvDu3AA\r\n"
						+ "Content-Disposition: form-data; name=\"m1\"\r\n"
						+ "\r\n"
						+ "MAIL_FROM: " + from + "\r\n"
						+ "RCPT TO: " + to + "\r\n"
						+ "From: " + from + "\r\n"
						+ "To: " + to + "\r\n"
						//+ "Subject: =?UTF-16BE?B?AFkAbwB1AHIAIABnAGEAbQBlAHMAIABhAHIAZQAgAHIAZQBhAGQAeQAh?=\r\n"
						+ "X-Wii-AppId: 2-48414541-0001\r\n"
						+ "X-Wii-Cmd: 00044001\r\n"
						+ "Content-Type: text/plain; charset=utf-16be\r\n"
						+ "Content-Transfer-Encoding: base64\r\n"
						+ "\r\n"
						+ "AHQAZQBzAHQ=\r\n"
						+ "\r\n"
						+ "--t9Sf4yfjf1RtvDu3AA\r\n"
						+ "Content-Disposition: form-data; name=\"m2\"\r\n"
						+ "\r\n"
						+ "MAIL_FROM: " + from + "\r\n"
						+ "RCPT TO: Gamebuster1990@gmail.com\r\n"
						+ "From: " + from + "\r\n"
						+ "To: Gamebuster1990@gmail.com\r\n"
						//+ "Subject: =?UTF-16BE?B?AFkAbwB1AHIAIABnAGEAbQBlAHMAIABhAHIAZQAgAHIAZQBhAGQAeQAh?=\r\n"
						+ "X-Wii-AppId: 2-48414541-0001\r\n"
						+ "X-Wii-Cmd: 00044001\r\n"
						+ "Content-Type: text/plain; charset=utf-16be\r\n"
						+ "Content-Transfer-Encoding: base64\r\n"
						+ "\r\n"
						+ "AHQAZQBzAHQ=\r\n"
						+ "\r\n"
						+ "--t9Sf4yfjf1RtvDu3AA--";
				
				StringEntity e = new StringEntity(s);
				
				MimeMultipart mp = new MimeMultipart(e.toString());
				
				request.setEntity(e);
				
				Header[] headers = request.getAllHeaders();
				for(Header header : headers) {
					System.err.println(header.getName() + ": " + header.getValue());
				}
				System.err.println(s);
				
				HttpResponse response = client.execute(request);
				if(response != null) {
					ByteArrayOutputStream logStream = new ByteArrayOutputStream();
					response.getEntity().writeTo(logStream);
					LOGGER.log(Level.INFO, logStream.toString());
					logStream.close();
				}
				
			}
			catch(Throwable t) {
				t.printStackTrace();
			}
		}
	}
	
	public static void send2() {
		for(int i = 65; i < 66; i++) {
			CloseableHttpClient client = HttpClients.createDefault();
			File secretFile = new File("./mail.secret");
			String wiiID;
			String password;
			HttpPost request;
			HttpPost dupe;
			BufferedReader fileReader = null;
			try {
				fileReader = new BufferedReader(new FileReader(secretFile));
				wiiID = fileReader.readLine();
				password = fileReader.readLine();
				request = new HttpPost("https://mtw." + SERVER + "/cgi-bin/send.cgi?mlid=" + wiiID + "&passwd=" + password + "&maxsize=11534336");
				
				String from = wiiID + "@rc24.xyz";
				String to = "w0633303276188884@rc24.xyz";
				
				request.addHeader("Content-Type", "multipart/form-data; boundary=t9Sf4yfjf1RtvDu3AA");
				
				String s = 					
						"Content-Disposition: form-data; name=\"mlid\"\r\n"
						+ "mlid="+wiiID+"\r\n"
						+ "passwd="+password+"\r\n"
						+ "--t9Sf4yfjf1RtvDu3AA\r\n"
						+ "Content-Disposition: form-data; name=\"m1\"\r\n"
						+ "\r\n"
						+ "MAIL_FROM: " + from + "\r\n"
						+ "RCPT TO: " + to + "\r\n"
						+ "From: " + from + "\r\n"
						+ "To: " + to + "\r\n"
						//+ "Subject: =?UTF-16BE?B?AFkAbwB1AHIAIABnAGEAbQBlAHMAIABhAHIAZQAgAHIAZQBhAGQAeQAh?=\r\n"
						+ "X-Wii-AppId: 2-48414541-0001\r\n"
						+ "X-Wii-Cmd: 00044001\r\n"
						+ "Content-Type: text/plain; charset=utf-16be\r\n"
						+ "Content-Transfer-Encoding: base64\r\n"
						+ "\r\n"
						+ "AHQAZQBzAHQ=\r\n"
						+ "\r\n"
						+ "--t9Sf4yfjf1RtvDu3AA\r\n"
						+ "Content-Disposition: form-data; name=\"m2\"\r\n"
						+ "\r\n"
						+ "MAIL_FROM: " + from + "\r\n"
						+ "RCPT TO: Gamebuster1990@gmail.com\r\n"
						+ "From: " + from + "\r\n"
						+ "To: "+to+"\r\n"
						+ "Subject: WC24 Cmd Message\r\n"
						+ "X-Wii-AppId: 0-00000001-0001\r\n"
						+ "X-Wii-Cmd: 80010001\r\n"
						+ "Content-Type: multipart/mixed; boundary=Boundary-NWC24-03BEF87900066\r\n"
						+ "\r\n"
						+ "--Boundary-NWC24-03BEF87900066\r\n"
						+ "Content-Type: text/plain; charset=us-ascii\r\n"
						+ "Content-Transfer-Encoding: 7bit\r\n"
						+ "\r\n"
						+ "WC24 Cmd Message\r\n"
						+ "--Boundary-NWC24-03BEF87900066\r\n"
						+ "Content-Type: application/octet-stream;\r\n"
						+ " name=a0000102.dat\r\n"
						+ "Content-Transfer-Encoding: base64\r\n"
						+ "Content-Disposition: attachment;\r\n"
						+ " filename=a0000102.dat\r\n"
						+ "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\r\n"
						+ "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\r\n"
						+ "AAAAAAAAAAAAAAAAAAA=\r\n"
						+ "\r\n"
						+ "\r\n"
						+ "--Boundary-NWC24-03BEF87900066-- \r\n"
						+ "--t9Sf4yfjf1RtvDu3AA--";
				
				StringEntity e = new StringEntity(s);
				
				MimeMultipart mp = new MimeMultipart(e.toString());
				
				request.setEntity(e);
				
				Header[] headers = request.getAllHeaders();
				for(Header header : headers) {
					System.err.println(header.getName() + ": " + header.getValue());
				}
				System.err.println(s);
				
				HttpResponse response = client.execute(request);
				if(response != null) {
					ByteArrayOutputStream logStream = new ByteArrayOutputStream();
					response.getEntity().writeTo(logStream);
					LOGGER.log(Level.INFO, logStream.toString());
					logStream.close();
				}
				
			}
			catch(Throwable t) {
				t.printStackTrace();
			}
		}
	}
	
	public static void receive() throws IOException {
		File secretFile = new File("./mail.secret");
		String wiiID;
		String password;
		HttpGet request;
		BufferedReader fileReader = null;
		InputStreamReader mailReader = null;
		CloseableHttpClient client = null;
		try {
			fileReader = new BufferedReader(new FileReader(secretFile));
			wiiID = fileReader.readLine();
			password = fileReader.readLine();
			client = HttpClients.createDefault();
			request = new HttpGet("https://mtw." + SERVER + "/cgi-bin/receive.cgi?mlid=" + wiiID + "&passwd=" + password + "&maxsize=11534336");
			
			CloseableHttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			if(entity != null) {
				LOGGER.log(Level.INFO, "Sent mail fetch request");
				InputStream contentStream = entity.getContent();
				mailReader = new InputStreamReader(contentStream);
				char[] data = new char[1];
				StringBuilder content = new StringBuilder();
				while(mailReader.read(data) != -1) {
					content.append(data);
				}
				LOGGER.log(Level.INFO, content.toString());
			}
			else {
				LOGGER.log(Level.INFO, "Response was null");
			}
		}
		finally {
			wiiID = null;
			password = null;
			request = null;
			try {
				if(fileReader != null) {
					try {
						fileReader.close();
					}
					finally {
						if(mailReader != null) {
							mailReader.close();
						}
					}
				}
			}
			catch(Throwable t) {
				LOGGER.log(Level.SEVERE, "An exception occurred when closing readers", t);
			}
			finally {
				if(client != null) {
					try {
						client.close();
					}
					catch(Throwable t) {
						LOGGER.log(Level.SEVERE, "An exception occurred when closing the HTTP connection to mail server", t);
					}
				}
			}
		}
	}
	
	public static String genBoundary() {
		StringBuilder builder = new StringBuilder("Boundary-NWC24-");
		return builder.append("03BEF88600067").toString();
	}
	
}
