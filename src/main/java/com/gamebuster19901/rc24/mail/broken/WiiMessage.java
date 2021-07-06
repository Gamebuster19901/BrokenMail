package com.gamebuster19901.rc24.mail.broken;

import javax.mail.MessagingException;
import javax.mail.Session;
import com.sun.mail.smtp.SMTPMessage;

public class WiiMessage extends SMTPMessage {

	public WiiMessage(Session session) throws MessagingException {
		super(session);
		this.updateMessageID();
	}
	
	@Override
	protected void updateMessageID() throws MessagingException {
		if(this.getHeader("Message-Id") == null) {
			//updateMessageID();
		}
	}


}
