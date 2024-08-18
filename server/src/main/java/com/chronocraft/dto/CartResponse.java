package com.chronocraft.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartResponse {
	private String totalCartPrice;
	
	private List<CartDataResponse> cartData;

}
