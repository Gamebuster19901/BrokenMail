package com.gamebuster19901.rc24.mail.broken;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.ContentType;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.PreencodedMimeBodyPart;

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
		for(int i = 65; i < 66; i++) {
			CloseableHttpClient client = HttpClients.createDefault();
			File secretFile = new File("./mail.secret");
			String wiiID;
			String password;
			HttpPost request;
			HttpPost dupe;
			BufferedReader fileReader = null;
			InputStreamReader mailReader = null;
			try {
				fileReader = new BufferedReader(new FileReader(secretFile));
				wiiID = fileReader.readLine();
				password = fileReader.readLine();
				request = new HttpPost("https://mtw." + SERVER + "/cgi-bin/send.cgi?mlid=" + wiiID + "&passwd=" + password + "&maxsize=11534336");
				dupe = new HttpPost("https://mtw." + SERVER + "/cgi-bin/send.cgi?mlid=" + wiiID + "&passwd=" + password + "&maxsize=11534336");
				
				WiiMessage message = new WiiMessage(Session.getDefaultInstance(System.getProperties()));
				
				message.setEnvelopeFrom(wiiID + "@rc24.xyz");
				
				MimeMultipart multiPart = new MimeMultipart();
				ContentType contentType = new ContentType("multipart", "mixed", null);
				contentType.setParameter("boundary", genBoundary());
				Field field = Multipart.class.getDeclaredField("contentType");
				field.setAccessible(true);
				field.set(multiPart, contentType.toString());
				
				EmptyBodyPart authPart = new EmptyBodyPart();
				authPart.setDisposition("form-data; name=\"mlid\"");
				authPart.addHeader("mlid", wiiID);
				authPart.addHeader("passwd", password);
				//authPart.setText(null);
				password = null;
				
				MimeBodyPart messagePart = new PreencodedMimeBodyPart("base64");
				messagePart.setDisposition("form-data; name=\"m1\"");
				messagePart.addHeader("MAIL FROM", wiiID + "@rc24.xyz");
				messagePart.addHeader("RCPT TO", wiiID + "@rc24.xyz");
				messagePart.addHeader("From", wiiID + "@rc24.xyz");
				messagePart.addHeader("To", wiiID + "@rc24.xyz");
				messagePart.addHeader("Subject", "AEIAYQB6AGkAbgBnAGE=");
				messagePart.addHeader("Message-Id", "<"+ i +"excitebot@rc24.xyz>");
				messagePart.addHeader("X-Wii-AppId", "2-48414541-0001");
				messagePart.addHeader("X-Wii-Cmd", "00048001");
				messagePart.setText("AEIAYQB6AGkAbgBnAGE=");
				messagePart.addHeader("Content-Type", "text/plain; charset=utf-16be");
				messagePart.addHeader("Content-Transfer-Encoding", "base64");
	
				
				multiPart.addBodyPart(authPart);
				multiPart.addBodyPart(messagePart);
	
				message.setContent(multiPart);
				
				
				
				StringBuilder b = new StringBuilder();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				message.writeTo(outputStream);
				String o = outputStream.toString();
				String boundary = o.substring(o.trim().lastIndexOf('\n') + 3);
				boundary = boundary.substring(0, boundary.length() - 4);
				request.setHeader("User-Agent", "WiiConnect24/2.2.255.0");
				request.setHeader("Content-Type", "multipart/form-data; boundary=\"" + boundary + "\"");
				String output = o;
				System.out.println(boundary);
				System.out.println(output.contains("\r\n\r\n--" + boundary));
				output = output.replace("\r\n\r\n--" + boundary, "\r\n--" + boundary);
	
				request.setEntity(new StringEntity(output));
				dupe.setEntity(new StringEntity(output));
				
				HttpEntity responseEntity = dupe.getEntity();
				if(responseEntity != null) {
					ByteArrayOutputStream logStream = new ByteArrayOutputStream();
					dupe.getEntity().writeTo(logStream);
					LOGGER.log(Level.INFO, new String(logStream.toByteArray()));
					logStream.close();
				}
				
				HttpResponse response = client.execute(request);
				if(response != null) {
					ByteArrayOutputStream logStream = new ByteArrayOutputStream();
					response.getEntity().writeTo(logStream);
					LOGGER.log(Level.INFO, new String(logStream.toByteArray()));
					logStream.close();
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
					LOGGER.log(Level.SEVERE, "An exception occurred when closing POST readers", t);
				}
			}
		}
		System.err.println("Waiting 15 seconds to retrieve mail...");
		Thread.sleep(15000);
		receive();
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
