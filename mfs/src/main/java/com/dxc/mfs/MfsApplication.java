package com.dxc.mfs;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dxc.mfs.model.File;
import com.dxc.mfs.model.Level;
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
			
			Level lv3 = new Level();
			lv3.setIdLevel(3);
			lv3.setImg("gold");
			lv3.setLevelName("Gold");
			lv3.setLimitUp(20480);
			lv3.setLimitDown(120400);
			lv3.setTotalLimitUp(204800);
			lv3.setUserList(null);
			levelRepository.save(lv3);
			Date date = new Date();
			User user1 = new User();
			user1.setfullname("tin");
			user1.setEmail("tin@gmail.com");
			user1.setPassword("1234567");
			user1.setAdmin(true);
			user1.setCurrentUp((byte) 0);
			user1.setCreateDate(date);
		
			user1 = userRepository.save(user1);
//			lv3.getUserList().add(user1);
//			user1.setIdLevel(levelRepository.findByIdLevel(3));
			Level lv = new Level();
			lv.setIdLevel(1);
			lv.setImg("bronze");
			lv.setLevelName("Bronze");
			lv.setLimitUp(5620);
			lv.setLimitDown(51200);
			lv.setTotalLimitUp(20480);
			lv.setUserList(null);
			levelRepository.save(lv);
			Level lv2 = new Level();
			lv2.setIdLevel(2);
			lv2.setImg("silver");
			lv2.setLevelName("Silver");
			lv2.setLimitUp(10240);
			lv2.setLimitDown(51200);
			lv2.setTotalLimitUp( 71680);
			lv2.setUserList(null);
			levelRepository.save(lv2);
			
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
