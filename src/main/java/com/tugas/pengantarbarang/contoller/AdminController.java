package com.tugas.pengantarbarang.contoller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tugas.pengantarbarang.entity.UserEntity;
import com.tugas.pengantarbarang.service.FirebaseService;


//@Controller
@RestController
//@RequestMapping("/users")
public class AdminController {
	
	
	@Autowired
	FirebaseService firebaseService;
	
	@GetMapping("/login")
	public String viewLogin() {
		return "login.html";
	}
	
	@PostMapping("/addUser")
	public String addUser(@RequestBody UserEntity userEntity) throws ExecutionException, InterruptedException {
		return firebaseService.saveUserDetails(userEntity);
	}
	
	@GetMapping("/getAll")
    public List<UserEntity> getAlluser() throws ExecutionException, InterruptedException {

        return firebaseService.getAllUser();
    }
	
	@GetMapping("/user/{name}")
    public UserEntity getName(@PathVariable String name) throws ExecutionException, InterruptedException {

        return firebaseService.getUserByName(name);
    }
	
	@PutMapping("/update")
	public String updateUsers(@RequestBody UserEntity users)throws ExecutionException, InterruptedException{
		return firebaseService.udateUsers(users);
	}
	
	@DeleteMapping("/user/{name}")
	public String deletUser(@PathVariable String name) throws ExecutionException, InterruptedException{
		return firebaseService.deleteUsers(name);
	}

}
