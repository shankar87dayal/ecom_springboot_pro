package com.ecom;

import java.util.*;

import com.ecom.entities.User;
import com.ecom.repo.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ecom.entities.Role;
import com.ecom.repo.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class EcomSpringbootProApplication implements CommandLineRunner{

	@Autowired
	private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
	
	
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

            List<Role> roles = new ArrayList<>();
            roles.add(role1);
            roles.add(role2);
            roles.add(role3);
            roleRepository.saveAll(roles);

            User user = new User();
            user.setName("Raushan_ku_ray");
            user.setEmail("raushanku90@gmail.com");
            user.setPassword(this.passwordEncoder.encode("abcd"));
            user.setAddress("Bihar");
            user.setAbout("I am coder");
            user.setPhone("1232432");
            user.setCreateAt(new Date());
            user.setGender("Male");
            user.setRoles(new HashSet<>(roles));

            //create user with admin role and insert them
            try {

                User user1 = this.userRepository.findByEmail("raushanku90@gmail.com").get();


            } catch (NoSuchElementException e) {

                System.out.println("saving admin user");
                this.userRepository.save(user);

            }


        } catch (Exception e) {
            System.out.println("User already there !!");
            e.printStackTrace();
        }

    }
}