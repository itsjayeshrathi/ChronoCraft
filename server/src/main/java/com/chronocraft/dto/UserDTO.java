package com.chronocraft.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {
	private String firstName;

	private String lastName;

	private String emailId;

	private String password;

	private String phoneNo;

	private String street;

	private String city;

	private String zipcode;
	
	private String role;
}
