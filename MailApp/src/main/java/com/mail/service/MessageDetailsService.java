package com.mail.service;

import com.mail.exception.MessageDetailsException;
import com.mail.exception.UsersExceptions;
import com.mail.model.MessageDetails;

public interface MessageDetailsService {
	 public MessageDetails SendMailtoUsers(String from ,String to,MessageDetails mail) throws UsersExceptions;
	 public MessageDetails starMail(Integer mailId) throws MessageDetailsException;
}
