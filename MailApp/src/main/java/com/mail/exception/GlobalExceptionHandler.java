package com.mail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UsersExceptions.class)
	public ResponseEntity<ErrorDetails> UsersExceptionHandler(UsersExceptions ex ,WebRequest wr){
	
		ErrorDetails err=new ErrorDetails();
		err.setMessage(ex.getMessage());
		err.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(MessageDetailsException.class)
	public ResponseEntity<ErrorDetails> MessageDetailsExceptionHandler(MessageDetailsException ex ,WebRequest wr){
	
		ErrorDetails err=new ErrorDetails();
		err.setMessage(ex.getMessage());
		err.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetails> NohandlerExceptionHandler(NoHandlerFoundException ex ,WebRequest wr){
	
		ErrorDetails err=new ErrorDetails();
		err.setMessage(ex.getMessage());
		err.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> NohandlerExceptionHandler(MethodArgumentNotValidException ex ,WebRequest wr){
	
		ErrorDetails err=new ErrorDetails();
		err.setMessage(ex.getBindingResult().getFieldError().getDefaultMessage());
		err.setDetails(wr.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);
	}
	
	
	
}
