package com.mail.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.mail.model.MessageDetails;

public interface MessageDetailsPagingAndSortingRepository extends PagingAndSortingRepository<MessageDetails, Integer>{

	@Query("from MessageDetails where toUser.userName =:fm and isDelByRecipient='NO' ")
	public List<MessageDetails> findRecivedMail(@Param("fm") String to,Pageable pageble);
	
	@Query("from MessageDetails where fromUser.userName =:fm and isDelBySender='NO' ")
	public List<MessageDetails> findSendedMail(@Param("fm") String from,Pageable pageble);
}
