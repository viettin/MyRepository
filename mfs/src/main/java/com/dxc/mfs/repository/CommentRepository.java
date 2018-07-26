package com.dxc.mfs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.mfs.model.Comment;



public interface CommentRepository extends JpaRepository<Comment,Integer> {
//	public List<Comment> findCommentByIdfile();
}

