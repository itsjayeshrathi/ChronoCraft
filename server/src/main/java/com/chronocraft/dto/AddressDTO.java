package com.chronocraft.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {
    private int id;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private Long userId;

    // Getters and Setters
}
