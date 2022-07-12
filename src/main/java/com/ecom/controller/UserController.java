package com.ecom.controller;

import java.util.Date;
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

import com.ecom.payload.ApiResonse;
import com.ecom.payload.UserDto;
import com.ecom.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	//create user data
	
	@PostMapping("/")
	public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
	
		userDto.setCreateAt(new Date());
		userDto.setActive(true);
		
		UserDto createduser = this.userService.create(userDto);
		
		return new ResponseEntity<>(createduser, HttpStatus.CREATED);
	}
	
	//update user
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody  UserDto userDto, @PathVariable int userId)
	{
		UserDto updateduser = this.userService.update(userDto, userId);
		 return new ResponseEntity<UserDto>(updateduser, HttpStatus.OK);
	}
	
	//get all user
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAll()
	{
		List<UserDto> all = this.userService.getAll();
		return new ResponseEntity<List<UserDto>>(all, HttpStatus.OK);
	}
	
	
	//get single user by id
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getById(@PathVariable int userId)
	{
		UserDto userDto = this.userService.getByUserId(userId);
		
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}
	
	//get single user by email
	@GetMapping("/email/{userEmail}")
	
	public ResponseEntity<UserDto> getByEmail(@PathVariable String  userEmail)
	{
		UserDto userDto = this.userService.getByEmail(userEmail);
		
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}
	
	//delete user
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResonse> deleteUser(@PathVariable int userId)
	{
		this.userService.deleteUser(userId);
		return new ResponseEntity<ApiResonse>(new ApiResonse("User is deleted successfully !!", true), HttpStatus.OK);
	}
}














