package com.ecom.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecom.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

		// custom finder methods
		//important method
		//email == username for our project
	
	public Optional<User> findByEmail(String email);
	
	public List<User> findByName(String name);
	
	public User findByEmailAndPassword(String email, String password);
	
	public List<User> findByActive(boolean active);
	
	public List<User> findByAboutIsNull();
	
	public List<User> findByNameStartingWith(String prefix);
	
	
	public List<User> findByNameContaining(String infix);
	
	public List<User> findByNameLike(String likePatteren);
	
	public List<User> findByNameOrderByNameDesc(String name);
	
    public List<User> findTo4By();
    
    
    //creating query method
    
    @Query("select u from User u")
    public List<User> getAllUser();
    
    @Query("select u from User u where u.userId = :userId and u.email = :email ")
	public User getUserByEmail(@Param("userId") int abcId,String email);
}









