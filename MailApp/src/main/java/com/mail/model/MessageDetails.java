package com.mail.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class MessageDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer messageId;
	
	@NotNull
	@Column(length = 1000,nullable = false)
	private String subject;
	
	@NotNull
	@Column(length = 10000,nullable = false)
	private String message;
	
	@JsonProperty(access = Access.READ_ONLY)
	@Column(length = 3)
	private String isViewed="No";
	
	@JsonProperty(access = Access.READ_ONLY)
	@Column(length = 3)
	private String isStar="No";
	
	@JsonProperty(access = Access.READ_ONLY)
	private final LocalDateTime timeStamp=LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name = "from_User",nullable = false)
	private Users fromUser;
	
	@ManyToOne
	@JoinColumn(name = "to_user",nullable = false)
	private Users toUser;
	
	@OneToMany(mappedBy = "message",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<MessageFiles> files=new ArrayList<>();
	
}
