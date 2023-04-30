package com.mail.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mail.model.MessageDetails;

@Repository
public interface MessageDetailsRepository extends JpaRepository<MessageDetails, Integer>{
	
	@Query("from MessageDetails where fromUser.userName =:fm and isDelBySender='NO' ")
	public List<MessageDetails> findSendedMail(@Param("fm") String from);
	
	@Query("from MessageDetails where toUser.userName =:fm and isDelByRecipient='NO' ")
	public List<MessageDetails> findRecivedMail(@Param("fm") String to);
	
	@Query("from MessageDetails where toUser.userName =:fm and isDelByRecipient='NO' and fromUser.userName LIKE :title%  ")
	public List<MessageDetails> findRecivedMailSerachByUserName(@Param("fm") String to,@Param("title") String fromUser);
	
	@Query("select count(*) from MessageDetails  where toUser.userName =:fm and isDelByRecipient='NO' ")
	public Integer findCountOfReceivedMail(@Param("fm") String to);
}
