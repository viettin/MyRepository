package com.dxc.mfs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.mfs.model.Comment;
import com.dxc.mfs.model.Level;



public interface CommentRepository extends JpaRepository<Comment,Integer> {
	
}

