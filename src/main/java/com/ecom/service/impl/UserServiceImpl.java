package com.ecom.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.entities.User;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.UserDto;
import com.ecom.repo.UserRepository;
import com.ecom.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDto create(UserDto userDto) {
		
		//dto to entity
		
		User user = this.toEntity(userDto);
		User createdUser = this.userRepository.save(user);
		return this.toDto(createdUser);
	}

	@Override
	public List<UserDto> getAll() {
		List<User> allUser = this.userRepository.findAll();
		List<UserDto> allDtos = allUser.stream().map(user-> this.toDto(user)).collect(Collectors.toList());
		
		return allDtos;
	}

	@Override
	public UserDto getByUserId(int userId) {
		User u = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user not found"+ userId));
		return this.toDto(u);
	}

	@Override
	public UserDto getByEmail(String email) {
	 User u = this.userRepository.findByEmail(email).orElseThrow(()-> new ResourceNotFoundException("user with this email is  found"));
		return this.toDto(u);
	}

	@Override
	public UserDto update(UserDto userDto, int userId) {
	
		User u = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user not found"+ userId));
		
		u.setName(userDto.getName());
		u.setEmail(userDto.getEmail());
		u.setPassword(userDto.getPassword());
		u.setAbout(userDto.getAbout());
		u.setAddress(userDto.getAddress());
		u.setActive(userDto.isActive());
		u.setGender(userDto.getGender());
		u.setCreateAt(userDto.getCreateAt());
		u.setPhone(userDto.getPhone());
		
		User updatedUser = this.userRepository.save(u);
		
		return this.toDto(updatedUser);
	}

	@Override
	public void deleteUser(int userId) {

		User u = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user not found"+ userId));
		this.userRepository.delete(u);
		
	}
	
	
	
	public UserDto toDto(User u) {
		
		UserDto dto = new UserDto();
		
		dto.setUserId(u.getUserId());
		dto.setName(u.getName());
		dto.setEmail(u.getEmail());
		dto.setPassword(u.getPassword());
		dto.setAbout(u.getAbout());
		dto.setAddress(u.getAddress());
		dto.setGender(u.getGender());
		dto.setCreateAt(u.getCreateAt());
		dto.setPhone(u.getPhone());
		dto.setActive(u.isActive());
	
		
		
		return dto;
	}
	
	
	public User toEntity(UserDto t)
	{
		User u =new User();
		
		u.setUserId(t.getUserId());
		u.setName(t.getName());
		u.setEmail(t.getEmail());
		u.setPassword(t.getPassword());
		u.setAbout(t.getAbout());
		u.setAddress(t.getAddress());
		u.setActive(t.isActive());
		u.setGender(t.getGender());
		u.setCreateAt(t.getCreateAt());
		u.setPhone(t.getPhone());
		
		
		
		return u;
	}
	
}
