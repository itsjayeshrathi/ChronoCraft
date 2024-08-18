package com.chronocraft.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chronocraft.custom_exceptions.ResourceNotFoundException;
import com.chronocraft.custom_exceptions.UsernameNotFoundException;
import com.chronocraft.dto.UserDTO;
import com.chronocraft.dto.UserLoginDTO;
import com.chronocraft.entities.Address;
import com.chronocraft.entities.User;
import com.chronocraft.repositories.AddressRepository;
import com.chronocraft.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AddressRepository addressRepository;

    //registration
    @Override
    public User createUser(UserDTO userDTO) {
        // Convert UserDTO to User entity
    	
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmailId(userDTO.getEmailId());
        user.setPassword(userDTO.getPassword());
        user.setPhoneNo(userDTO.getPhoneNo());
        user.setRole(userDTO.getRole());
        
        Address address = new Address();
		address.setCity(userDTO.getCity());
		address.setZipcode(userDTO.getZipcode());
		address.setStreet(userDTO.getStreet());
		
        
        user.setAddress(address);
        
        // Save the User entity
        User savedUser = userRepository.save(user);

        // Convert the saved User entity back to UserDTO
//        UserDTO savedUserDTO = new UserDTO();
//        savedUserDTO.setFirstName(savedUser.getFirstName());
//        savedUserDTO.setLastName(savedUser.getLastName());
//        savedUserDTO.setEmailId(savedUser.getEmailId());
//        savedUserDTO.setPassword(savedUser.getPassword());
//        savedUserDTO.setPhoneNo(savedUser.getPhoneNo());
//        savedUserDTO.setStreet(savedUser.getAddress().getStreet());
//        savedUserDTO.setCity(savedUser.getAddress().getCity());
//        savedUserDTO.setZipcode(savedUser.getAddress().getZipcode());
//        savedUserDTO.setRole(savedUser.getRole());

        return savedUser;
    }

    //login
    @Override
    public User findByEmailIdAndPassword(UserLoginDTO user) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmailIdAndPassword(user.getEmailId(),user.getPassword());
        
        if (userOptional.isPresent()) {
            User userResult = userOptional.get();
            return userResult;
        } else {
            // Handle the case where the user is not found
           throw new UsernameNotFoundException("User not found with email: " + user.getEmailId());
        
        }
    }
    
//    @Override
//    public UserDTO updateUser(Long id, UserDTO userDTO) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
//        user.setName(userDTO.getFirstName());
//        user.setEmail(userDTO.getEmail());
//        user.setPassword(userDTO.getPassword());
//        userRepository.save(user);
//        return userDTO;
//    }

    @Override
    public void deleteUser(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepository.delete(user);
    }

	@Override
	public UserDTO updateUser(int id, UserDTO userDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO getUserById(int id) {
		User user=userRepository.findUserById(id);
		Address address=user.getAddress();
		
		UserDTO userdto=new UserDTO();
		userdto.setCity(address.getCity());
		userdto.setEmailId(user.getEmailId());
		userdto.setFirstName(user.getFirstName());
		userdto.setLastName(user.getLastName());
		userdto.setPassword(user.getPassword());
		userdto.setPhoneNo(user.getPhoneNo());
		userdto.setRole(user.getRole());
		userdto.setStreet(address.getStreet());
		userdto.setZipcode(address.getZipcode());
		return userdto;
	}

	@Override
	public List<UserDTO> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

//    @Override
//    public UserDTO getUserById(Long id) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
//        UserDTO userDTO = new UserDTO();
//        userDTO.setId(user.getId());
//        userDTO.setName(user.getName());
//        userDTO.setEmail(user.getEmail());
//        return userDTO;
//    }

//    @Override
//    public List<UserDTO> getAllUsers() {
//        return userRepository.findAll().stream().map(user -> {
//            UserDTO userDTO = new UserDTO();
//            userDTO.setId(user.getId());
//            userDTO.setName(user.getName());
//            userDTO.setEmail(user.getEmail());
//            return userDTO;
//        }).collect(Collectors.toList());
//    }
}
