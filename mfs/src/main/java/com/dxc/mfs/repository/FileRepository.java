package com.dxc.mfs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.mfs.model.User;

public interface FileRepository extends JpaRepository<User, Integer> {

}
