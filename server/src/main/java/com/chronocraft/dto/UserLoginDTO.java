package com.chronocraft.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserLoginDTO {
	private String emailId;
	private String password;
	private String role;
}
