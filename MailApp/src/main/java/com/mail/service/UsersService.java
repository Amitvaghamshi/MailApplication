package com.mail.service;

import com.mail.exception.UsersExceptions;
import com.mail.model.Users;

public interface UsersService {
	public Users saveUser(Users user);
	public Users findByuserName(String  userName) throws UsersExceptions;
}
