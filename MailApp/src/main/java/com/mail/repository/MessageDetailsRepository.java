package com.mail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mail.model.MessageDetails;

@Repository
public interface MessageDetailsRepository extends JpaRepository<MessageDetails, Integer>{

}
