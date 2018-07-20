package com.dxc.mfs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dxc.mfs.model.User;
import com.dxc.mfs.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value="users/{username}", method = RequestMethod.GET)
	public @ResponseBody List<User> findByUsername(@PathVariable String username){
		return userRepository.findAllByUsername(username);
	}
}
