package com.dxc.mfs.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.mfs.model.Comment;
import com.dxc.mfs.model.User;
import com.dxc.mfs.repository.FileRepository;
import com.dxc.mfs.services.CommentServices;
import com.dxc.mfs.services.UserService;

@RestController
public class UserController {
	private static final String String = null;
	@Autowired
	UserService userService;
	@Autowired
	CommentServices commentServices;
	
	

	
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
	public  @ResponseBody List<User> getAllUserByAdmin (HttpSession session) {
		
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

	@RequestMapping(value="adduser",method = RequestMethod.POST)
	public  @ResponseBody MessageStatus addUser (HttpServletRequest request) {
		
		Date  date = new Date();
		User user = new User();
		user.setfullname(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		user.setEmail(request.getParameter("email"));
		user.setAdmin(false);
		user.setCreateDate(date);
		userService.addUser(user);
		MessageStatus m = new MessageStatus();
		if(user != null) {
			 m.setStatus("success");
			 m.setMessage("Register Success");
			 m.setData(user);
		} else {
			m.setStatus("fail");
			m.setMessage("Register unsuccess");
		}
		return m;
	}

	@RequestMapping(value="delete/{email}", method = RequestMethod.DELETE)
	public   @ResponseBody MessageStatus deleteByEmail(@PathVariable String email){
		 
		 MessageStatus m = new MessageStatus();
			if(userService.deleteUser(email)) {
				 m.setStatus("success");
				 m.setMessage("delete Success");
				 
			} else {
				m.setStatus("fail");
				m.setMessage("delete unsuccess");
			}
			return m;
}
	@RequestMapping(value="update/{email}", method = RequestMethod.PATCH)
	public   @ResponseBody MessageStatus updateByEmail(@PathVariable String email,HttpServletRequest request){
		String fullname = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userService.updateUser(email, fullname, password);
		MessageStatus m = new MessageStatus();
		if(user != null) {
			 m.setStatus("success");
			 m.setMessage("update Success");
			 m.setData(user);
		} else {
			m.setStatus("fail");
			m.setMessage("update unsuccess");
		}
		return m;
	}
	@RequestMapping(value="comment/{idfile}",method = RequestMethod.POST)
	public  @ResponseBody MessageStatus commentFile (@PathVariable int idfile, HttpServletRequest request, HttpSession session) {
		User userLoging = (User) session.getAttribute("userDetail");
		MessageStatus m = new MessageStatus();
		Comment cmt = new Comment();
		if (userLoging != null) {
			cmt.setContent(request.getParameter("content"));
			cmt.setUserComment(userLoging.getFullname());
			cmt.setIdfile(idfile);
			commentServices.addComment(cmt);
			if(commentServices.addComment(cmt)) {
				 m.setStatus("success");
				 m.setMessage("Comment Success");
				 m.setData(cmt);
				 return m;
				
			}
			
		}
		
		
		else if (userLoging == null) {
			cmt.setContent(request.getParameter("content"));
			cmt.setUserComment("Guest");
			cmt.setIdfile(idfile);
			commentServices.addComment(cmt);
			if(commentServices.addComment(cmt)) {
				 m.setStatus("success");
				 m.setMessage("Comment Success");
				 m.setData(cmt);
				 return m;
				
			}
		}
		 return m;
	}
	}
	
