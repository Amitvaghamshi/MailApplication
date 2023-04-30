package com.mail.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class MessageFiles{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer fileId;
	
	@Column(length = 1000)
	private String urlPath;
	
	@ManyToOne
	@JoinColumn(name = "message_id")
	@JsonIgnore
	private MessageDetails message;
	
}
