package com.airline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airline.dto.ApiResponse;
import com.airline.dto.UserDto;
import com.airline.service.UserService;

import jakarta.validation.Valid;
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	//create user
	
	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto createUserDto=this.userService.createUser(userDto);
		return new ResponseEntity<UserDto>(createUserDto,HttpStatus.CREATED);
	}
	
	
	//update user
	@PutMapping("/update/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer id )
	{
		
		UserDto updateUser=this.userService.updateUser(userDto, id);
		return new ResponseEntity<>(updateUser,HttpStatus.OK);
		
	}
	//delete User
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id)
	{
		this.userService.deleteUser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully",true), HttpStatus.OK);
	}
	
	//get all users
	@GetMapping("/allusers")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
	    return  ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	//get single user
	@GetMapping("/user/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable Integer id)
	{
		return ResponseEntity.ok(this.userService.getUserById(id));
	}
		


}
