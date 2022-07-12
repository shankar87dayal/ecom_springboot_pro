package com.ecom.service;

import java.util.List;

import com.ecom.payload.UserDto;

public interface UserService {

	//ceate
	
	UserDto create(UserDto userDto);
	
	//get all users
	
	List<UserDto> getAll();
	
	//get user by useraid
	
	UserDto getByUserId(int userId);
	
	//get by email
	
	UserDto getByEmail(String email);
	
	//update
	
	UserDto update(UserDto userDto , int userId);

	//delete
	
	void deleteUser(int userId);	
	
	
	
	
}
