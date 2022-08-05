package com.ecom;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ecom.entities.Role;
import com.ecom.repo.RoleRepository;


@SpringBootApplication
public class EcomSpringbootProApplication implements CommandLineRunner{

	@Autowired
	private RoleRepository roleRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(EcomSpringbootProApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		

		try {
            Role role1 = new Role();
            role1.setId(5245);
            role1.setName("ROLE_ADMIN");

            Role role2 = new Role();
            role2.setId(7412);
            role2.setName("ROLE_NORMAL");

            Role role3 = new Role();
            role3.setId(9632);
            role3.setName("ROLE_STAFF");

            //java 8 features
//            List<Role> roles = new ArrayList<>();
//            roles.add(role1);
//            roles.add(role2);
//            roles.add(role3);
            roleRepository.saveAll(List.of(role1, role2, role3));

        } catch (Exception e) {
            System.out.println("User already there !!");
            e.printStackTrace();
	}

 }
}
