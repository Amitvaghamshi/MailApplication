package com.mail.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mail.model.Users;
import com.mail.repository.UsersRepository;

@Service
public class UsersUserDetailsService implements UserDetailsService{

	@Autowired
	private UsersRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Users> opt=userRepo.findByUserName(username);
		
         if(opt.isPresent()) {
			
			Users user= opt.get();
			List<GrantedAuthority> authorities = new ArrayList<>();
		
		//	System.out.println("granted authorities "+authorities);
			
			return new User(user.getUserName(), user.getPassword(), authorities);
		}else
			throw new BadCredentialsException("User Details not found with this username: "+username);
		
	}
}
