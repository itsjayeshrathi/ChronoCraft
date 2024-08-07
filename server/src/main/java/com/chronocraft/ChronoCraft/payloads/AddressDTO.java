package com.chronocraft.ChronoCraft.payloads;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    @NotBlank
    private Long id;
    private String street;
    private String city;
    private String state;
    private String buildingName;
    private String pincode;
    private String country;
}
