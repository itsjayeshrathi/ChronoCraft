package com.chronocraft.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDataResponseDTO {
	private List<OrderDTO> orderResponse;
	
	private String totalCartPrice;
}
