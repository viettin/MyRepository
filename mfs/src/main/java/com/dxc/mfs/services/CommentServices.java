package com.dxc.mfs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.mfs.model.Comment;
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
//		 fileRepository.findByIdFile(cmt.getIdfile()).getCommentList().add(cmt);

		 return true;
	 }
	}

