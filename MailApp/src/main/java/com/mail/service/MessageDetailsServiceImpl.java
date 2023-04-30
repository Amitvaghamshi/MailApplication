package com.mail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mail.exception.MessageDetailsException;
import com.mail.exception.UsersExceptions;
import com.mail.model.MessageDetails;
import com.mail.model.MessageFiles;
import com.mail.model.Users;
import com.mail.repository.MessageDetailsRepository;
@Service
public class MessageDetailsServiceImpl implements MessageDetailsService{

	@Autowired
	private MessageDetailsRepository messageRepo;
	
	@Autowired
	private UsersService userService;
	
	@Override
	public MessageDetails SendMailtoUsers(String from, String to, MessageDetails mail) throws UsersExceptions {
		     Users fromUser=userService.findByuserName(from);
		     Users toUser=userService.findByuserName(to);
		     
		     // Checking that Whether the loggedIn user Is sending mail Or Not so geting the data of Authenticated user
		     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		     String senderEmail = authentication.getName();
		     System.out.println(senderEmail);

		     if(!senderEmail.equals(from)){
		    	 throw new UsersExceptions("User can not send mail from Others Account");
		     }
		    
		     
		     if(from.equals(to)){
		    	 throw new UsersExceptions("User can not send mail to Self");
		     }
		     
		     if(fromUser==null || toUser==null){
		    	 throw new UsersExceptions("User not found with this userId");
		     }
		     
		     mail.setFromUser(fromUser);
		     mail.setToUser(toUser);
		     
		     //  to set user with message
		     List<MessageFiles> files= mail.getFiles();
		     for(MessageFiles m:files) {
		    	  m.setMessage(mail);
		     }
		     
		     return messageRepo.save(mail);
	}
	
	
	@Override
	public MessageDetails starMail(Integer mailId) throws MessageDetailsException {
		
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		  String senderEmail = authentication.getName();
		  System.out.println(senderEmail);
		     
		  MessageDetails details=messageRepo.findById(mailId).orElseThrow(()->new MessageDetailsException("No mail found with this id"));
		     
	     if(!senderEmail.equals(details.getFromUser().getUserName())){
	    	 throw new MessageDetailsException("you can not star others mail");
	     }
	     
	     details.setIsStar("YES");
	     return messageRepo.save(details);
	    	
	}
	
	

}
