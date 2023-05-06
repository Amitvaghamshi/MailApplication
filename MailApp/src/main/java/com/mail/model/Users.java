package com.mail.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@Entity
public class Users{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "first_name",nullable = false)
	@NotNull
	private String firstName ;
	
	@NotNull
	@Column(name = "last_name",nullable = false)
	private String lastName;
	
	@NotNull
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dob",nullable = false)
	private LocalDate dob;
	
	@NotNull
	@Column(name = "user_name",unique = true,nullable = false)
	private String userName;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "password",length = 1000,nullable = false)
	private String password;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "fromUser")
	@JsonIgnore
	private List<MessageDetails> message=new ArrayList<>();
}
