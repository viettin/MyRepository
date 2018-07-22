package com.dxc.mfs.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.mfs.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findAllByUsername(String username);
	List<User> findByUsernameAndPassword(String username, String password);
}
