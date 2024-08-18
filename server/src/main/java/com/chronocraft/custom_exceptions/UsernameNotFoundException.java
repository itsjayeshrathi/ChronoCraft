package com.chronocraft.custom_exceptions;

public class UsernameNotFoundException extends Exception {
	public UsernameNotFoundException(String mesg) {
		super(mesg);
	}
}
