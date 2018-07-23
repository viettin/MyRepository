package com.dxc.mfs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.mfs.model.User;
import com.dxc.mfs.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
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
}
