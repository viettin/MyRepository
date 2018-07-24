package com.dxc.mfs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.mfs.model.User;
import com.dxc.mfs.repository.LevelRepository;
import com.dxc.mfs.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	LevelRepository levelRepository;
	
	 public User checkLogin(String email, String password) {
		List<User> list =  userRepository.findByEmailAndPassword(email, password);
		 if(list != null && list.size()==1) {
			 return list.get(0);
			 
		 }
		 return null;
	 }
	 public List<User> getAllUser(){
		 List<User> listUser = userRepository.findAll();
		 return listUser;
	 }
	 public User addUser (User user) {
		 userRepository.save(user);
		 levelRepository.findByIdLevel(1).getUserList().add(user);
		 user.setIdLevel(levelRepository.findByIdLevel(1));
		 return user;
	 }
	 public boolean deleteUser (String email){
		 User user = userRepository.findByEmail(email);
		 if(user!=null) {
			userRepository.delete(user);
			return true;
		 }
		 return false;
		 
	 }
	 public User updateUser (String email,String fullname, String password){
		 User user = userRepository.findByEmail(email);
		 if(user!=null) {
			user.setfullname(fullname);
			user.setPassword(password);
			userRepository.save(user);
			return user;
		 }
		 return null;
		 
	 }
}
