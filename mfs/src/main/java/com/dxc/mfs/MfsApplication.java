package com.dxc.mfs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dxc.mfs.model.File;
import com.dxc.mfs.model.User;
import com.dxc.mfs.repository.FileRepository;
import com.dxc.mfs.repository.LevelRepository;
import com.dxc.mfs.repository.UserRepository;

@SpringBootApplication
public class MfsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MfsApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(UserRepository userRepository, LevelRepository levelRepository,FileRepository fileRepository ) {
		return (args) -> {
			userRepository.deleteAll();
			
			User user1 = new User();
			user1.setfullname("tin");
			user1.setEmail("tin@gmail.com");
			user1.setPassword("1234567");
			user1.setAdmin(true);
			user1 = userRepository.save(user1);
			User user2 = new User();
			user2.setfullname("tin2");
			user2.setEmail("tin2@gmail.com");
			user2.setPassword("1232");
			user2.setAdmin(false);
			user2 = userRepository.save(user2);
			File file = new File();
			file.setFileName("test");
			file.setDescription("da~ bao la test ma");
			file.setType("txt");
			fileRepository.save(file);
//			Level lv = new Level();
//			lv.setIdLevel(1);
//			lv.setImg("bronze");
//			lv.setLevelName("Bronze");
//			lv.setLimitUp(5*1024);
//			lv.setLimitDown(50*1024);
//			lv.setTotalLimitUp(20*1024);
//			lv.setUserList(null);
//			levelRepository.save(lv);
//			Level lv2 = new Level();
//			lv2.setIdLevel(2);
//			lv2.setImg("silver");
//			lv2.setLevelName("Silver");
//			lv2.setLimitUp(10*1024);
//			lv2.setLimitDown(50*1024);
//			lv2.setTotalLimitUp(70*1024);
//			lv2.setUserList(null);
//			levelRepository.save(lv2);
//			Level lv3 = new Level();
//			lv3.setIdLevel(3);
//			lv3.setImg("gold");
//			lv3.setLevelName("Gold");
//			lv3.setLimitUp(20*1024);
//			lv3.setLimitDown(100*1024);
//			lv3.setTotalLimitUp(200*1024);
//			lv3.setUserList(null);
//			levelRepository.save(lv3);
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
