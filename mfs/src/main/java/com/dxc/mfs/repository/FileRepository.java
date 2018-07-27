package com.dxc.mfs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dxc.mfs.model.File;

public interface FileRepository extends JpaRepository<File, Integer> {
File findByIdFile(int idfile);
//@Modifying
//@Query("SELECT e FROM file e where e.file_name = :category")
//List<File> findByCategory3(@Param("category") String category) ;
List<File> findAllByEmailUploader(String email);
List<File>  findByFileNameContaining(String search); 
List<File>  findByFileName(String search); 
List<File> findBySize(long size);
List<File>  findByUploaderContaining(String search); 

}
