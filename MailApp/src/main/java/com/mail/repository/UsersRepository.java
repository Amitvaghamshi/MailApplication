package com.mail.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mail.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>{
	public Optional<Users> findByUserName(String userName);
}
