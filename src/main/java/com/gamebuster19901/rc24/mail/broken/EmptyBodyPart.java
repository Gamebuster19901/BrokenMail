package com.gamebuster19901.rc24.mail.broken;

import java.io.InputStream;

import javax.mail.internet.MimeBodyPart;

import org.apache.commons.io.input.NullInputStream;

public class EmptyBodyPart extends MimeBodyPart {

	@Override
	public InputStream getContentStream() {
		return new NullInputStream();
	}
	
}
