package com.dxc.mfs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dxc.mfs.model.User;
import com.dxc.mfs.repository.UserRepository;

@SpringBootApplication
public class MfsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MfsApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(UserRepository userRepository) {
		return (args) -> {
			userRepository.deleteAll();
			
			User user1 = new User();
			user1.setUsername("tin");
			user1.setPassword("123");
			
			User user2 = new User();
			user2.setUsername("bao");
			user2.setPassword("456");
			
			System.out.println(user1);
			user1 = userRepository.save(user1);
			System.out.println(user1);
			System.out.println(userRepository.count());
			
			System.out.println(user2);
			user1 = userRepository.save(user2);
			System.out.println(user2);
			System.out.println(userRepository.count());
			
			System.out.println(userRepository.findAll());
			
			System.out.println(userRepository.findAllByUsername("tin"));
		};
	}
}
