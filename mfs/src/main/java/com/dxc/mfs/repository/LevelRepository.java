package com.dxc.mfs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.mfs.model.Level;

public interface LevelRepository extends JpaRepository<Level,Integer> {
Level  findByIdLevel(int idlevel); 
}
