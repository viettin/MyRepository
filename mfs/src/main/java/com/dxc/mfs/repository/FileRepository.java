package com.dxc.mfs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dxc.mfs.model.File;

public interface FileRepository extends JpaRepository<File, Integer> {
File findByIdFile(int idfile);

List<File> findAllByEmailUploader(String email);
 
}
