package com.dxc.mfs.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.mfs.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findAllByFullname(String fullname);
	List<User> findByEmailAndPassword(String email, String password);
//	List<User> findAllUser();
	User findByEmail(String email);
}
