package com.mail.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mail.exception.UsersExceptions;
import com.mail.model.Users;
import com.mail.service.UsersService;

@RestController
@RequestMapping("/mail")
public class UsersController {
	
	@Autowired
	private UsersService userService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	Logger logger = LoggerFactory.getLogger(UsersController.class);

	 
	
	   @PostMapping("/users")
	   public ResponseEntity<Users> saveUserHandler(@RequestBody Users user){
		       user.setPassword(encoder.encode(user.getPassword()));
		       user.setUserName(user.getUserName()+"@amail.com");
		       Users us=userService.saveUser(user);
		       logger.info(user.getUserName()+" has ragisterd to Account");
		       return new ResponseEntity<>(us,HttpStatus.CREATED);
	   }
	   
	   
	   @GetMapping("/signIn")
		public ResponseEntity<Users> getLoggedInUserDetailsHandler(Authentication auth) throws UsersExceptions{
		     Users user=userService.findByuserName(auth.getName());
		     logger.info(auth.getName()+" has signIn");
			 return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
			 
		}
	   
}
