package com.ecom.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.entities.Role;
import com.ecom.entities.User;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.payload.UserDto;
import com.ecom.repo.RoleRepository;
import com.ecom.repo.UserRepository;
import com.ecom.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private RoleRepository roleRepository;
	
	
	@Override
	public UserDto create(UserDto userDto) {
		
		//dto to entity
		
		User user = this.toEntity(userDto);
		
		
		Role role = this.roleRepository.findById(7412).get();
		user.getRoles().add(role);
		
		User createdUser = this.userRepository.save(user);
		
		//Entity to dto
		
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
	
		User u = this.userRepository.findById(userId).orElseThrow(
				()-> new ResourceNotFoundException("user not found"+ userId));
		
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
	
	@Autowired
	private ModelMapper mopper1;
	
	
	public UserDto toDto(User user) {
		
		return this.mopper1.map(user, UserDto.class);
	}
	
	
	public User toEntity(UserDto dto)
	{
		return this.mopper1.map(dto, User.class);
	}
	
}
