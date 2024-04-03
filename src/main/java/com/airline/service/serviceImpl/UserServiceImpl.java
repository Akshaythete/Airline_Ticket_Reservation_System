package com.airline.service.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airline.dto.UserDto;
import com.airline.exception.ResourceNotFoundException;
import com.airline.model.User;
import com.airline.repo.UserRepo;
import com.airline.service.UserService;

@Service

public class UserServiceImpl implements UserService 
{
	
	@Autowired
	private UserRepo userRepo;
	
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	//update user
	@Override
	public UserDto updateUser(UserDto userDto, int id) 
	{
		
		User user=this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","id",id));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setLocation(userDto.getLocation());
		
		User updateUser=this.userRepo.save(user);
		
		UserDto userDto1=this.userToDto(updateUser);
		return userDto1 ;
	}

	//get user by id
	@Override
	public UserDto getUserById(Integer id)
	{
		
		User user=this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "Id", id));
		return this.userToDto(user);
	}

	//get all users
	@Override
	public List<UserDto> getAllUsers() 
	{
		
		List<User> users=this.userRepo.findAll();
		
		List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	//delete user
	@Override
	public void deleteUser(Integer id)
	{
		
		User user=this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "Id", id));
		this.userRepo.delete(user);
		
	}
	
	
  //create users
	@Override
	public UserDto createUser(UserDto userDto)
	{
		User user=this.dtoToUser(userDto);
		User saveduser=this.userRepo.save(user);
		return this.userToDto(saveduser);
	
	}
	private User dtoToUser(UserDto userDto)
	{
		
	  User user=this.modelMapper.map(userDto, User.class);
	  return user;
	}
	
	private UserDto userToDto(User user)
	{
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
		
		return userDto;
	}

}
