package com.chronocraft.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chronocraft.custom_exceptions.UsernameNotFoundException;
import com.chronocraft.dto.UserDTO;
import com.chronocraft.dto.UserLoginDTO;
import com.chronocraft.entities.User;
import com.chronocraft.repositories.UserRepository;
import com.chronocraft.services.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepo;
    
  

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
    	System.out.println("recieved request for REGISTER USER");
        User createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("login")
	public ResponseEntity<?> loginUser(@RequestBody UserLoginDTO loginRequest) {
		System.out.println("recieved request for LOGIN USER");
		System.out.println(loginRequest);
		
		User user = new User();
		try {
			user=userService.findByEmailIdAndPassword(loginRequest);
		} catch (UsernameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println(user);
		System.out.println("login successfull!!!");
		return ResponseEntity.ok(user);
	}
    
//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody UserLoginDTO loginRequest) {
//        System.out.println("Received request for LOGIN USER");
//        System.out.println(loginRequest);
//
//        try {
//            User user = userService.findByEmailIdAndPassword(loginRequest);
//
//            // Generate JWT token
//            String token = jwtUtil.generateToken(user.getEmailId());
//
//            // Return token in response
//            return ResponseEntity.ok(new AuthResponse(token, user));
//        } catch (UsernameNotFoundException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(401).body("Invalid email or password");
//        }
//    }
    
    
    @GetMapping("admin/all")
	public ResponseEntity<?> getAllAdmin(){
		System.out.println("recieved request for getting all admin");

		List<User> admins = this.userRepo.findByRole("Admin");

		System.out.println("response sent");
		return ResponseEntity.ok(admins);
	}

	@GetMapping("customer/all")
	public ResponseEntity<?> getAllCustomer(){
		System.out.println("received for getting all customers");

		List<User> customers = this.userRepo.findByRole("Customer");
		System.out.println("repsonse sent");
		
		return ResponseEntity.ok(customers);
	}

	
	
	
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getUserById(@PathVariable int id) {
//        UserDTO userDTO = userService.getUserById(id);
//        return ResponseEntity.ok(userDTO);
//    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        UserDTO userDTO = userService.getUserById(id);
        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
 


//    @GetMapping
//    public ResponseEntity<List<UserDTO>> getAllUsers() {
//        List<UserDTO> users = userService.getAllUsers();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
 
}
