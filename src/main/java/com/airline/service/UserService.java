package com.airline.service;

import java.util.List;

import com.airline.dto.UserDto;



public interface UserService {
	
	//create user
		UserDto createUser(UserDto userDto);
		
		//update user
		
		UserDto updateUser(UserDto user,int id);
		
		//get user by id
		UserDto getUserById(Integer id);
		
		//get all users
		
		List<UserDto> getAllUsers();
		
		//delete by id
		
		void deleteUser(Integer id);
		

}
