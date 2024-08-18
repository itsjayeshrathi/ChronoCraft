package com.chronocraft.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartDataResponse {
	private int cartId;
	
	private int watchId;
	
	private String watchName;
	
	private String watchDescription;
	
	private String watchImage;
	
	private int quantity;
}
