package com.chronocraft.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddToCartDTO {
	private int watchId;
	
	private int quantity;
	
	private int userId;
}
