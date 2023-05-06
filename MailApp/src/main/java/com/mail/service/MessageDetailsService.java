package com.mail.service;

import java.util.List;

import com.mail.exception.MessageDetailsException;
import com.mail.exception.UsersExceptions;
import com.mail.model.MessageDetails;

public interface MessageDetailsService {
	 public MessageDetails SendMailtoUsers(String from ,String to,MessageDetails mail) throws UsersExceptions;
	 public MessageDetails starMail(Integer mailId) throws MessageDetailsException;
	 public MessageDetails unStarMail(Integer mailId) throws MessageDetailsException;
	 public MessageDetails markAsRead(Integer mailId) throws MessageDetailsException;
	 public MessageDetails markAsUnread(Integer mailId) throws MessageDetailsException;
	 public MessageDetails deleteRecipientsMail(Integer mailId) throws MessageDetailsException;
	 public MessageDetails deleteSendersMail(Integer mailId) throws MessageDetailsException;
	 public List<MessageDetails> getSendedMailList(String from) throws MessageDetailsException;
	 public List<MessageDetails> getReceivedMailList(String from) throws MessageDetailsException;
	 public List<MessageDetails> getReceivedMailListByPagination(String to,Integer pageNo ,Integer noOfRecord)
			throws MessageDetailsException;
	 public List<MessageDetails> getSendersMailListByPagination(String from, Integer pageNo, Integer noOfRecord)
			throws MessageDetailsException;
	 public List<MessageDetails> getReceivedMailListBySenderName(String to, String SenderName) throws MessageDetailsException;
	 public Integer getCountOfMailInInbox(String to) throws MessageDetailsException;
	MessageDetails getMessageById(Integer id) throws MessageDetailsException;
	List<MessageDetails> getStaredMessage(String reciver) throws MessageDetailsException;
}
