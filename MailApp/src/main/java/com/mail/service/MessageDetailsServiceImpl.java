package com.mail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.mail.exception.MessageDetailsException;
import com.mail.exception.UsersExceptions;
import com.mail.model.MessageDetails;
import com.mail.model.MessageFiles;
import com.mail.model.Users;
import com.mail.repository.MessageDetailsPagingAndSortingRepository;
import com.mail.repository.MessageDetailsRepository;
@Service
public class MessageDetailsServiceImpl implements MessageDetailsService{

	@Autowired
	private MessageDetailsRepository messageRepo;
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private MessageDetailsPagingAndSortingRepository paginatonRepo;
	
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
		     
	     if(!senderEmail.equals(details.getToUser().getUserName())){
	    	 throw new MessageDetailsException("you can not star others mail");
	     }
	     
	     details.setIsStar("YES");
	     return messageRepo.save(details);
	    	
	}
	
	
	@Override
	public MessageDetails unStarMail(Integer mailId) throws MessageDetailsException {
		
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		  String senderEmail = authentication.getName();
		  System.out.println(senderEmail);
		     
		  MessageDetails details=messageRepo.findById(mailId).orElseThrow(()->new MessageDetailsException("No mail found with this id"));
		     
	     if(!senderEmail.equals(details.getToUser().getUserName())){
	    	 throw new MessageDetailsException("you can not star others mail");
	     }
	     
	     details.setIsStar("NO");
	     return messageRepo.save(details);
	    	
	}
	
	@Override
	public MessageDetails markAsRead(Integer mailId) throws MessageDetailsException {
		
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		  String senderEmail = authentication.getName();
		  System.out.println(senderEmail);
		     
		  MessageDetails details=messageRepo.findById(mailId).orElseThrow(()->new MessageDetailsException("No mail found with this id"));
		     
	     if(!senderEmail.equals(details.getToUser().getUserName())){
	    	 throw new MessageDetailsException("you can not read others mail");
	     }
	     
	     details.setIsViewed("YES");
	     return messageRepo.save(details);
	    	
	} 
	
	
	@Override
	public MessageDetails markAsUnread(Integer mailId) throws MessageDetailsException {
		
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		  String senderEmail = authentication.getName();
		  System.out.println(senderEmail);
		     
		  MessageDetails details=messageRepo.findById(mailId).orElseThrow(()->new MessageDetailsException("No mail found with this id"));
		     
	     if(!senderEmail.equals(details.getToUser().getUserName())){
	    	 throw new MessageDetailsException("you can not unread others mail");
	     }
	     
	     details.setIsViewed("NO");
	     return messageRepo.save(details);
	    	
	} 
	
	
	@Override
	public MessageDetails deleteRecipientsMail(Integer mailId) throws MessageDetailsException{
		
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		  String senderEmail = authentication.getName();
		  System.out.println(senderEmail);
		     
		  MessageDetails details=messageRepo.findById(mailId).orElseThrow(()->new MessageDetailsException("No mail found with this id"));
		  
	     if(!senderEmail.equals(details.getToUser().getUserName())){
	    	 throw new MessageDetailsException("you can not delete others mail");
	     }
	     
	     details.setIsDelByRecipient("YES");
	     // if mail is deleted from sender and recipient then delete from data base else simple update
	     if(details.getIsDelBySender().equals("YES") && details.getIsDelByRecipient().equals("YES")) {
	    	 messageRepo.delete(details);
	    	 System.out.println("It shoulbe deleted");
	     }else {
	    	 messageRepo.save(details);
	     }
	     
	    return 	details;
	} 
	
	@Override
	public MessageDetails deleteSendersMail(Integer mailId) throws MessageDetailsException{
		
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		  String senderEmail = authentication.getName();
		  System.out.println(senderEmail);
		     
		  MessageDetails details=messageRepo.findById(mailId).orElseThrow(()->new MessageDetailsException("No mail found with this id"));
		  
	     if(!senderEmail.equals(details.getFromUser().getUserName())){
	    	 throw new MessageDetailsException("you can not delete others mail");
	     }
	     
	     details.setIsDelBySender("YES");
	     // if mail is deleted from sender and recipient then delete from data base else simple update
	     if(details.getIsDelBySender().equals("YES") && details.getIsDelByRecipient().equals("YES")) {
	    	 messageRepo.delete(details);
	     }else {
	    	 messageRepo.save(details);
	     }
	     
	    return 	details;
	}    
	
	@Override
	 public List<MessageDetails> getSendedMailList(String from) throws MessageDetailsException{
		
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		  String senderEmail = authentication.getName();
		  System.out.println(senderEmail);
		     		  
	     if(!senderEmail.equals(from)){
	    	 throw new MessageDetailsException("you can get data others mail");
	     }
		
		return messageRepo.findSendedMail(from);
		
	}
	
	 @Override
	 public List<MessageDetails> getReceivedMailList(String to) throws MessageDetailsException{
		
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		  String senderEmail = authentication.getName();
		  System.out.println(senderEmail);
		     		  
	     if(!senderEmail.equals(to)){
	    	 throw new MessageDetailsException("you can get data others mail");
	     }
		
		return messageRepo.findRecivedMail(to);
		
	}
	 
	 @Override
	 public List<MessageDetails> getReceivedMailListByPagination(String to,Integer pageNo ,Integer noOfRecord ) throws MessageDetailsException{
		
		 if(pageNo<=0 || noOfRecord<=0) {
	    	 throw new MessageDetailsException("pageNo Or noOfRecord can not be less than 0");
		 }
		 
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		  String senderEmail = authentication.getName();
		  System.out.println(senderEmail);
		  
		  
		  
	     if(!senderEmail.equals(to)){
	    	 throw new MessageDetailsException("you can get data others mail");
	     }
		 
	     Pageable pg=PageRequest.of(pageNo-1, noOfRecord,Sort.by("timeStamp").descending());
	     
	     List<MessageDetails> pagecontent= paginatonRepo.findRecivedMail(to,pg);
	     
	     if(!pagecontent.isEmpty()) {
	    	 return pagecontent;
	     }else {
	    	 throw new MessageDetailsException("No record found at page "+pageNo);
	     }
		
	}
	 
	 
	 @Override
	 public List<MessageDetails> getSendersMailListByPagination(String from,Integer pageNo ,Integer noOfRecord ) throws MessageDetailsException{
		
		 if(pageNo<=0 || noOfRecord<=0) {
	    	 throw new MessageDetailsException("pageNo Or noOfRecord can not be less than 0");
		 }
		 
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		  String senderEmail = authentication.getName();
		  System.out.println(senderEmail);
		     		  
	     if(!senderEmail.equals(from)){
	    	 throw new MessageDetailsException("you can get data others mail");
	     }
		 
	     Pageable pg=PageRequest.of(pageNo-1, noOfRecord,Sort.by("timeStamp").descending());
	     
	     List<MessageDetails> pagecontent= paginatonRepo.findSendedMail(from,pg);
	     
	     if(!pagecontent.isEmpty()) {
	    	 return pagecontent;
	     }else {
	    	 throw new MessageDetailsException("No record found at page "+pageNo);
	     }
		
	}
	 
	 @Override
	 public List<MessageDetails> getReceivedMailListBySenderName(String to,String SenderName) throws MessageDetailsException{
		
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		  String senderEmail = authentication.getName();
		  System.out.println(senderEmail);
		     		  
	     if(!senderEmail.equals(to)){
	    	 throw new MessageDetailsException("you can get data others mail");
	     }
		
	     List<MessageDetails> result= messageRepo.findRecivedMailSerachByUserName(to, SenderName);
		
		return result;
	}
	 
	 @Override
	 public Integer getCountOfMailInInbox(String to) throws MessageDetailsException{
		
		  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		  String senderEmail = authentication.getName();
		  System.out.println(senderEmail);
		     		  
	     if(!senderEmail.equals(to)){
	    	 throw new MessageDetailsException("you can get data others mail");
	     }
		
		return messageRepo.findCountOfReceivedMail(to);
	}
	 
	 
	

}
