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
	 public boolean addUser (User user) {
		 User checkUser= userRepository.findByEmail(user.getEmail());
 		if(checkUser == null) {
 			userRepository.save(user);
 			 levelRepository.findByIdLevel(1).getUserList().add(user);
 			 user.setIdLevel(levelRepository.findByIdLevel(1));
 			 return true;
 		}
 		else {
 			return false;
 		}
	 }
	 public boolean deleteUser (String email){
		 User user = userRepository.findByEmail(email);
		 if(user!=null) {
			userRepository.delete(user);
			return true;
		 }
		 return false;
		 
	 }
	 public User updateUser (User user){
		 User userUpdate = userRepository.findByEmail(user.getEmail());
		 if(userUpdate != null) {
			 userRepository.save(userUpdate);
			 return userUpdate;
		 }
		 return user;
		 
	 }
	 public User updateUser (String email, String name,String pass){
		 User userUpdate = userRepository.findByEmail(email);
		 if(userUpdate != null) {
			 userUpdate.setfullname(name);
			 userUpdate.setPassword(pass);
			 userRepository.save(userUpdate);
			 return userUpdate;
		 }
		 return userUpdate;
		 
	 }
}
