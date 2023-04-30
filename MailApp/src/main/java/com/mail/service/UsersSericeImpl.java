package com.mail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mail.exception.UsersExceptions;
import com.mail.model.Users;
import com.mail.repository.UsersRepository;

@Service
public class UsersSericeImpl implements UsersService{
	
	@Autowired
	private UsersRepository userRepo;

	@Override
	public Users saveUser(Users user) {
		  return userRepo.save(user);
	}

	@Override
	public Users findByuserName(String userName) throws UsersExceptions {
		  return userRepo.findByUserName(userName).orElseThrow(()->new UsersExceptions("User not found with this userName "+userName));
	}

}
