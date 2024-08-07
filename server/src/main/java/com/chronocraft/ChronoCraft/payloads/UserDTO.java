package com.chronocraft.ChronoCraft.payloads;
import java.util.List;
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password; // For registration only
    private String firstName;
    private String lastName;
    private List<AddressDTO> addresses;
}
