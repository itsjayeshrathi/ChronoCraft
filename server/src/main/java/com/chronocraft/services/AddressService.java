package com.chronocraft.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chronocraft.dto.AddressDTO;
@Service
public interface AddressService {
    AddressDTO createAddress(AddressDTO addressDTO);
    AddressDTO updateAddress(int id, AddressDTO addressDTO);
    void deleteAddress(int id);
    AddressDTO getAddressById(int id);
    List<AddressDTO> getAllAddresses();
}
