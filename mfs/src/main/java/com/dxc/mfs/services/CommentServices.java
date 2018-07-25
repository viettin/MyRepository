package com.dxc.mfs.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.mfs.model.Comment;
import com.dxc.mfs.model.File;
import com.dxc.mfs.repository.CommentRepository;
import com.dxc.mfs.repository.FileRepository;

@Service
public class CommentServices {
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	FileRepository fileRepository;
	 public boolean addComment (Comment cmt) {
		 commentRepository.save(cmt);
		 File existFile = fileRepository.findByIdFile(cmt.getIdfile());
		 
		 if (existFile != null) {
			 List<Comment> listComment  = existFile.getCommentList();
			
			 
			 if (listComment != null) {
				 listComment.add(cmt);
				 return true;
			 } else {
				List<Comment> listComment2 = null ;
				
				listComment2.add(cmt);
				 existFile.setCommentList(listComment2);
				 return true;
			 }
		 }
		 if(existFile == null) {
			 return false;
		 }
		 fileRepository.save(existFile);
		 return true;
	 }
	}

