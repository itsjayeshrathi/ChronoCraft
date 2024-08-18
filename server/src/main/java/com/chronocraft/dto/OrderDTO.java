package com.chronocraft.dto;

import java.util.List;

import com.chronocraft.entities.Address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
    private String orderId;
	
    private int watchId;
    
    private int userId;
    
    private String userName;  // first name + last name
    
    private Address address;
    
    private String userPhone;
	
	private String watchName;
	
	private String watchDescription;
	
	private String watchImage;
	
	private int quantity;
	
	private String totalPrice;
	
	private String orderDate;
}
