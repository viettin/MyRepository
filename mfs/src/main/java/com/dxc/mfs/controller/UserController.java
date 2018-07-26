package com.dxc.mfs.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dxc.mfs.model.Comment;
import com.dxc.mfs.model.File;
import com.dxc.mfs.model.User;
import com.dxc.mfs.services.CommentServices;
import com.dxc.mfs.services.FileServices;
import com.dxc.mfs.services.UserService;

@RestController
public class UserController {
	private static final String String = null;
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	UserService userService;
	@Autowired
	CommentServices commentServices;
	@Autowired
	FileServices fileServices;

	@RequestMapping(value = "/login", method = RequestMethod.POST) // Login
	public @ResponseBody MessageStatus login(HttpServletRequest request, HttpSession session) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println(email + " : " + password);
		User user = userService.checkLogin(email, password);
		MessageStatus m = new MessageStatus();
		if (user != null) {
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

	@RequestMapping(value = "/logout", method = RequestMethod.GET) // get tat ca user cho trang admin
	public @ResponseBody MessageStatus Logout(HttpSession session) {
		MessageStatus m = new MessageStatus();
		User userLoging = (User) session.getAttribute("userDetail");
		if (userLoging != null) {
			session.removeAttribute("userDetail");
			m.setStatus("success");
			m.setMessage("Logout Success");
			return m;
		} else {
			m.setStatus("Fail");
			m.setMessage("Da~ dang nhap deoo dau");
			return m;
		}

	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET) // get tat ca user cho trang admin
	public @ResponseBody List<User> getAllUserByAdmin(HttpSession session) {

		List<User> listUser = null;

		User userLoging = (User) session.getAttribute("userDetail");
		if (userLoging == null) { // chua dang nhap

		} else {
			if (userLoging.isAdmin()) {
				listUser = userService.getAllUser();
			}
		}

		return listUser;
	}

//	@RequestMapping(value = "/register", method = RequestMethod.POST) //register
//	public @ResponseBody MessageStatus addUser(HttpServletRequest request) {
//
//		Date date = new Date();
//		return null;}

	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	public @ResponseBody MessageStatus adduser(HttpServletRequest request) {
		Date date = new Date();
		User user = new User();
		user.setfullname(request.getParameter("fullname"));
		user.setPassword(request.getParameter("password"));
		user.setEmail(request.getParameter("email"));
		user.setAdmin(false);
		user.setCreateDate(date);

		MessageStatus m = new MessageStatus();
		if (userService.addUser(user)) {
			m.setStatus("success");
			m.setMessage("Register Success");
			m.setData(user);
		} else {
			m.setStatus("fail");
			m.setMessage("User is ready exist");
		}
		return m;
	}

	@RequestMapping(value = "delete/{email}", method = RequestMethod.DELETE) // delete user
	public @ResponseBody MessageStatus deleteByEmail(@PathVariable String email,HttpSession session) {
		User userLoging = (User) session.getAttribute("userDetail");
		MessageStatus m = new MessageStatus();
		if(userLoging != null) {
			if(userLoging.isAdmin()) {
				
				if (userService.deleteUser(email)) {
					List <User> userList= userService.getAllUser();
					m.setStatus("success");
					m.setMessage("delete Success");
					m.setData(userList);
					return m;
			}
				m.setStatus("fail");
				m.setMessage("You are not Admin");
				
				return m;
			}
			
		}else {
			m.setStatus("fail");
			m.setMessage("delete unsuccess");
		}
		return m;
	}

	@RequestMapping(value = "update/{email}", method = RequestMethod.PATCH) // cap nhat thong tin user
	public @ResponseBody MessageStatus updateByEmail(@PathVariable String email, HttpServletRequest request) {
		String fullname = request.getParameter("fullname");
		String password = request.getParameter("password");
		User user = userService.updateUser(email, fullname, password);
		MessageStatus m = new MessageStatus();
		if (user != null) {
			m.setStatus("success");
			m.setMessage("update Success");
			m.setData(user);
		} else {
			m.setStatus("fail");
			m.setMessage("update unsuccess");
		}
		return m;
	}

	@RequestMapping(value = "comment/{idfile}", method = RequestMethod.POST) // comment theo file
	public @ResponseBody MessageStatus commentFile(@PathVariable int idfile, HttpServletRequest request,
			HttpSession session) {
		User userLoging = (User) session.getAttribute("userDetail");
		MessageStatus m = new MessageStatus();
		Comment cmt = new Comment();
		if (userLoging != null) {
			cmt.setContent(request.getParameter("content"));
			cmt.setUserComment(userLoging.getFullname());
			cmt.setIdfile(idfile);
			commentServices.addComment(cmt);
			if (commentServices.addComment(cmt)) {
				m.setStatus("success");
				m.setMessage("Comment Success");
				m.setData(cmt);
				return m;
			}
		} else if (userLoging == null) {
			cmt.setContent(request.getParameter("content"));
			cmt.setUserComment("Guest");
			cmt.setIdfile(idfile);
			commentServices.addComment(cmt);
			if (commentServices.addComment(cmt)) {
				m.setStatus("success");
				m.setMessage("Comment Success");
				m.setData(cmt);
				return m;
			}
		}
		return m;
	}

	@RequestMapping(value = "deletefile/{idfile}", method = RequestMethod.DELETE) // delete file
	public @ResponseBody MessageStatus deleteByEmail(@PathVariable int idfile) {

		MessageStatus m = new MessageStatus();
		if (fileServices.deleteFile(idfile)) {
			m.setStatus("success");
			m.setMessage("delete Success");
			List<File> filelist = fileServices.getAllFile();
			m.setData(filelist);

		} else {
			m.setStatus("fail");
			m.setMessage("delete unsuccess");
		}
		return m;
	}

	@RequestMapping(value = "updatefile/{idfile}", method = RequestMethod.PATCH) // cap nhap thong tin file
	public @ResponseBody MessageStatus updateByEmail(@PathVariable int idFile, HttpServletRequest request) {
		File file = fileServices.getByIdFile(idFile);
		MessageStatus m = new MessageStatus();
		if (file != null) {
			file.setFileName(request.getParameter("filename"));
			file.setDescription("description");
			fileServices.updateFile(file);
			m.setStatus("success");
			m.setMessage("update Success");
			m.setData(file);
		} else {
			m.setStatus("fail");
			m.setMessage("update unsuccess");
		}
		return m;
	}

	@RequestMapping(value = "/file/{idfile}", method = RequestMethod.GET) // get thong tin 1 file theo id
	public @ResponseBody MessageStatus getFile(@PathVariable int idFile, HttpServletRequest request,
			HttpSession session) {
		MessageStatus m = new MessageStatus();
		User userLoging = (User) session.getAttribute("userDetail");
		if (userLoging == null) { // chua dang nhap
			m.setStatus("fail ");
			m.setMessage("fail ");
			return m;

		} else {
			
				File file = fileServices.getByIdFile(idFile);
				m.setStatus("success");
				m.setMessage("Load Success");
				m.setData(file);
			
			return m;
		}
	}

	@RequestMapping(value = "/file/all", method = RequestMethod.GET) // get file theo user da dang nhap.
	public @ResponseBody MessageStatus getAllFileUser(HttpSession session) {
		MessageStatus m = new MessageStatus();
		User userLoging = (User) session.getAttribute("userDetail");
		if (userLoging == null) { // chua dang nhap
			m.setStatus("fail cmnr");
			m.setMessage("fail cmnr");
			return m;
		} else {
			if (!userLoging.isAdmin()) {
				String emailUser = userLoging.getEmail();
				List<File> listFile = fileServices.getAllFileByEmail(emailUser);
				m.setStatus("success");
				m.setMessage("Load Success");
				m.setData(listFile);
			} else {
				m.setStatus("fail cmnr");
				m.setMessage("fail cmnr");
				return m;
			}
			return m;
		}
	}

	@RequestMapping(value = "/file", method = RequestMethod.GET) // Get File cho trang chu
	public @ResponseBody MessageStatus getAllFile() {
		MessageStatus m = new MessageStatus();
		List<File> listFile = fileServices.getAllFile();
		m.setStatus("success");
		m.setMessage("Load Success");
		m.setData(listFile);
		return m;
	}

	@PostMapping("/uploadFile")
	public MessageStatus uploadFile(@RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
		User userLoging = (User) session.getAttribute("userDetail");
		MessageStatus m = new MessageStatus();
		Date date = new Date();
		File newFile = new File();
		if (userLoging != null) {
			String name = userLoging.getFullname();
			String email = userLoging.getEmail();
			File dbFile = fileServices.storeFile(file, name, email);
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
					.path(String.valueOf(dbFile.getIdFile())).toUriString();
			m.setStatus("success");
			m.setMessage(fileDownloadUri);
			return m;
		}
		
		return m;

	}

	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<byte[]> getFile(@PathVariable int fileId) {
		File file = fileServices.getByIdFile(fileId);
		
		if(file !=null) {
			
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
					.body(file.getData());	
		}
		
		return ResponseEntity.status(404).body(null);
	}
	@RequestMapping(value = "/search/{condition}", method = RequestMethod.POST) // Get File cho trang chu
	public @ResponseBody MessageStatus getAllFile(@PathVariable String condition,HttpServletRequest request) {
		String search = request.getParameter("search");
		MessageStatus m = new MessageStatus();
		List<File> listFile = fileServices.searchFile(condition,search);
		m.setStatus("success");
		m.setMessage("Load Success");
		m.setData(listFile);
		return m;
	}


}
