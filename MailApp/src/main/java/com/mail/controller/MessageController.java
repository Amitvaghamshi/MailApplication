package com.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mail.exception.UsersExceptions;
import com.mail.model.MessageDetails;
import com.mail.service.MessageDetailsService;

@RestController
@RequestMapping("/mail")
public class MessageController {
	
	@Autowired
	private MessageDetailsService messageService;

	
	@PostMapping("/sendmail")
	public ResponseEntity<MessageDetails> sendMessage(@RequestParam("from") String from ,@RequestParam("to") String to,@RequestBody MessageDetails mail) throws UsersExceptions{
		  MessageDetails msg=messageService.SendMailtoUsers(from, to, mail);
		  return new ResponseEntity<MessageDetails>(msg,HttpStatus.CREATED);
	}
}
