package com.mail.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mail.exception.MessageDetailsException;
import com.mail.exception.UsersExceptions;
import com.mail.model.MessageDetails;
import com.mail.service.MessageDetailsService;

import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/mail")
public class MessageController {
	
	@Autowired
	private MessageDetailsService messageService;
	
	Logger logger = LoggerFactory.getLogger(MessageController.class);
	
	@PostMapping("/send")
	public ResponseEntity<MessageDetails> sendMessage(@RequestParam("from") String from ,@RequestParam("to") String to,@RequestBody MessageDetails mail) throws UsersExceptions{
		  MessageDetails msg=messageService.SendMailtoUsers(from, to, mail);
		  logger.info(from+" has send mail to "+to);
		  return new ResponseEntity<MessageDetails>(msg,HttpStatus.CREATED);
	}
	
	@PutMapping("/star/{id}")
	public ResponseEntity<MessageDetails> starMailHandler(@NotNull @PathVariable("id") Integer mailId) throws  MessageDetailsException{
		  MessageDetails msg=messageService.starMail(mailId);
		  logger.info(mailId+" has star mail");
		  return new ResponseEntity<MessageDetails>(msg,HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("/unstar/{id}")
	public ResponseEntity<MessageDetails> UnStarMailHandler(@NotNull @PathVariable("id") Integer mailId) throws  MessageDetailsException{
		  MessageDetails msg=messageService.unStarMail(mailId);
		  logger.info(mailId+" has Unstar mail");
		  return new ResponseEntity<MessageDetails>(msg,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/read/{id}")
	public ResponseEntity<MessageDetails> markAsReadHandler(@NotNull @PathVariable("id") Integer mailId) throws  MessageDetailsException{
		  MessageDetails msg=messageService.markAsRead(mailId);
          logger.info(mailId+" has Just read the mail");
		  return new ResponseEntity<MessageDetails>(msg,HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("/unread/{id}")
	public ResponseEntity<MessageDetails> markAsUnReadHandler(@NotNull @PathVariable("id") Integer mailId) throws  MessageDetailsException{
		  MessageDetails msg=messageService.markAsUnread(mailId);
		  logger.info(mailId+" has Just Unread the mail");
		  return new ResponseEntity<MessageDetails>(msg,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/recipient/{id}")
	public ResponseEntity<MessageDetails> DeleteRecipientsMailHandler(@NotNull @PathVariable("id") Integer mailId) throws  MessageDetailsException{
		  MessageDetails msg=messageService.deleteRecipientsMail(mailId);
		  logger.info(mailId+" has Just Delete  the mail from Inbox");
		  return new ResponseEntity<MessageDetails>(msg,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/sender/{id}")
	public ResponseEntity<MessageDetails> DeleteSendersMailHandler(@NotNull @PathVariable("id") Integer mailId) throws  MessageDetailsException{
		  MessageDetails msg=messageService.deleteSendersMail(mailId);
		  logger.info(mailId+" has Just delete the mail from sent box");
		  return new ResponseEntity<MessageDetails>(msg,HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/sended/{fm}")
	public ResponseEntity<List<MessageDetails>> getSendedMailHandler(@NotNull @PathVariable("fm") String from) throws  MessageDetailsException{
		List<MessageDetails> msg=messageService.getSendedMailList(from);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@GetMapping("/received/{fm}")
	public ResponseEntity<List<MessageDetails>> getReceivedMailHandler(@NotNull @PathVariable("fm") String to) throws  MessageDetailsException{
		List<MessageDetails> msg=messageService.getReceivedMailList(to);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	
	@GetMapping("/received/paginated/{fm}")
	public ResponseEntity<List<MessageDetails>> getPagedReceivedMailHandler(@NotNull @PathVariable("fm") String to,@RequestParam("pagesize") Integer pagesize,@RequestParam("pageno") Integer pageNo) throws  MessageDetailsException{
		List<MessageDetails> msg=messageService.getReceivedMailListByPagination(to, pageNo,pagesize);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@GetMapping("/sended/paginated/{fm}")
	public ResponseEntity<List<MessageDetails>> getPagedSendedMailHandler(@NotNull @PathVariable("fm") String to,@RequestParam("pagesize") Integer pagesize,@RequestParam("pageno") Integer pageNo) throws  MessageDetailsException{
		List<MessageDetails> msg=messageService.getSendersMailListByPagination(to, pageNo,pagesize);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@GetMapping("/serach")
	public ResponseEntity<List<MessageDetails>> getSerachedMailHandler(@RequestParam("to") String to,@RequestParam("from") String from) throws  MessageDetailsException{
		List<MessageDetails> msg=messageService.getReceivedMailListBySenderName(to, from);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	
	@GetMapping("/received/count/{to}")
	public ResponseEntity<Integer> getCountOfInboxMailHandler(@PathVariable("to") String to) throws  MessageDetailsException{
		Integer  count=messageService.getCountOfMailInInbox(to);
		logger.info(to+" has "+count+" mail in inbox");
		
		return new ResponseEntity<>(count,HttpStatus.OK);
	}
	
	
	@GetMapping("/message/{id}")
	public ResponseEntity<MessageDetails> getMessageByIdHandler(@PathVariable("id") Integer id) throws  MessageDetailsException{
		MessageDetails md= messageService.getMessageById(id);
		
		return new ResponseEntity<>(md,HttpStatus.OK);
	}
	
	
	
	@GetMapping("/stared/{id}")
	public ResponseEntity<List<MessageDetails>> getStaredMailsHandler(@PathVariable("id") String  to)throws  MessageDetailsException{
		List<MessageDetails> msg= messageService.getStaredMessage(to);
		
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}



	
}
