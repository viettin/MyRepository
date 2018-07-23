package com.dxc.mfs;

import java.util.List;

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
			user1.setfullname("tin");
			user1.setEmail("tin@gmail.com");
			user1.setPassword("123");
			user1.setAdmin(true);
			user1 = userRepository.save(user1);
			User user2 = new User();
			user2.setfullname("tin2");
			user2.setEmail("tin2@gmail.com");
			user2.setPassword("1232");
			user2.setAdmin(false);
			user2 = userRepository.save(user2);
//			List<User> list = userRepository.findByUsernameAndPassword("tin", "123");
//			System.out.println(list.size());
//			System.out.println(list.get(0));
//			User user2 = new User();
//			user2.setUsername("bao");
//			user2.setPassword("456");
//			
//			System.out.println(user1);
//			user1 = userRepository.save(user1);
//			System.out.println(user1);
//			System.out.println(userRepository.count());
//			
//			System.out.println(user2);
//			user1 = userRepository.save(user2);
//			System.out.println(user2);
//			System.out.println(userRepository.count());
//			
//			System.out.println(userRepository.findAll());
//			
//			System.out.println(userRepository.findAllByUsername("tin"));
		};
	}
}
