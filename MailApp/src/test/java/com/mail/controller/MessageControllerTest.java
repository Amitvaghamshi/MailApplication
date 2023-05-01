package com.mail.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.mail.exception.UsersExceptions;
import com.mail.model.MessageDetails;
import com.mail.model.Users;
import com.mail.repository.UsersRepository;


@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerTest {

	
	@Autowired
	private MessageController messagecontroller;
	
	@Autowired
	private UsersController usercontroller;
	
	@Autowired
	private static UsersRepository userRepo;
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private WebApplicationContext context;
	
	
	private static Users sender;
	private static Users reciver;
	
	@BeforeAll
	public static void init() {
		 sender=new Users();
		 sender.setFirstName("abc");
		 sender.setLastName("def");
		 sender.setDob(LocalDate.of(2000, 9, 12));
		 sender.setUserName("abc09");
		 sender.setPassword("Abc@123");
		 
		 
		 reciver=new Users();
		 reciver.setFirstName("xyz");
		 reciver.setLastName("pqr");
		 reciver.setDob(LocalDate.of(2004, 3, 3));
		 reciver.setUserName("xyz09");
		 reciver.setPassword("Xyz@123");
		  
	}
	
	  @BeforeEach
	    public void initmethod(){
	        this.mvc= MockMvcBuilders
	                .webAppContextSetup(context).apply(springSecurity())
	                .build();
	  }
	
	@Test
	public void testSaveUserHandler() {
		
		  ResponseEntity<Users> u= usercontroller.saveUserHandler(sender);
		  boolean sd= u.getStatusCode().is2xxSuccessful();
		  assertTrue(sd);
		  
		  ResponseEntity<Users> s= usercontroller.saveUserHandler(reciver);
		  boolean rc= s.getStatusCode().is2xxSuccessful();
		  assertTrue(rc);
		  
	}
	
	@Test
	public void testGetLoggedInUserDetailsHandler() throws Exception {
		     MvcResult  res= mvc.perform(get("/mail/signIn").with(httpBasic("abc09@amail.com","Abc@123"))).andExpect(status().isAccepted()).andReturn();
		     assertEquals(202,res.getResponse().getStatus());
	}
	
	@Test
	public void testsendMessage() {
		MessageDetails details=new MessageDetails();
		details.setMessage("Hello");
		details.setSubject("Test");
		
		try {
			ResponseEntity<MessageDetails> res=messagecontroller.sendMessage(sender.getUserName(),reciver.getUserName(), details);
			boolean rc= res.getStatusCode().is2xxSuccessful();
			assertTrue(rc);
		} catch (UsersExceptions e) {
			e.printStackTrace();
		}
		
	}
	
	@AfterAll
	public static void destroy() {
	//	userRepo.delete(sender);
		System.out.println("Testing ended");
	}
	
	
}
