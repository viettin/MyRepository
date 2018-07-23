package com.dxc.mfs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.mfs.model.User;
import com.dxc.mfs.services.UserService;

@RestController
public class UserController {
	@Autowired
	UserService userService;
	
//	@RequestMapping(value="users/{username}", method = RequestMethod.GET)
//	public @ResponseBody List<User> findByUsername(@PathVariable String username){
//		return userRepository.findAllByUsername(username);
	
	@RequestMapping(value="login", method = RequestMethod.POST)
	public @ResponseBody MessageStatus login( HttpServletRequest request, HttpSession session){
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User user = userService.checkLogin(email, password);
		MessageStatus m = new MessageStatus();
		if(user != null) {
			 m.setStatus("success");
			 m.setMessage("Login Success");
			 m.setData(user);
			 session.setAttribute("userDetail", user);
		} else {
			m.setStatus("fail");
			m.setMessage("Login unsuccess");
		}
		return m;
	}
	
	@RequestMapping(value="admin",method = RequestMethod.GET)
	public List<User> getAllUserByAdmin (HttpSession session) {
		
		List<User> listUser = null;
		
		User userLoging = (User) session.getAttribute("userDetail");
		 if( userLoging == null ) { // chua dang nhap
			 
		 } else {
			 if (userLoging.isAdmin()) {
				listUser = userService.getAllUser();
			 }
		 }
		 
		 return listUser;
 	}
}
