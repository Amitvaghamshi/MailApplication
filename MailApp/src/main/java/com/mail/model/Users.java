package com.mail.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Users {

	@Id
	private Integer userId;
	private String firstName ;
	private String lastName;
	private LocalDate dob;
	private String userName;
	private String password;
}
