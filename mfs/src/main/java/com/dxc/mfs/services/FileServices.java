package com.dxc.mfs.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.dxc.mfs.exception.FileStorageException;
import com.dxc.mfs.exception.MyFileNotFoundException;
import com.dxc.mfs.model.File;
import com.dxc.mfs.repository.FileRepository;

@Service
public class FileServices {
	@Autowired
	FileRepository fileRepository;

	public boolean deleteFile(int idfile) {
		File file = fileRepository.findByIdFile(idfile);
		if (file != null) {
			fileRepository.delete(file);
			return true;
		} else {

			return false;
		}
	}

	public List<File> getAllFile() {

		List<File> listFile = fileRepository.findAll();
		return listFile;
	}

	public List<File> getAllFileByEmail(String email) {

		List<File> listFile = fileRepository.findAllByEmailUploader(email);
		return listFile;
	}

	public File getByIdFile(int idFile) {
		File file = fileRepository.findByIdFile(idFile);
		return file;
	}

	public boolean updateFile(File file) {
		fileRepository.save(file);
		return true;

	}

	public File storeFile(MultipartFile file, String name, String email) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			Date date = new Date();
			File newFile = new File();
			newFile.setFileName(file.getOriginalFilename());
			System.out.println(newFile.getFileName());
			newFile.setEmailUploader(email);
			newFile.setType(file.getContentType());
			newFile.setUploader(name);
			newFile.setData(file.getBytes());
			newFile.setUploadDate(date);
			;
			newFile.setSize(file.getSize());
			return fileRepository.save(newFile);
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public List<File> searchFile(String condition, String search) {
		if (condition.equals("name")) {
			List<File> listfile = fileRepository.findByFileNameContaining(search);
			return listfile;
		} else if (condition.equals("category")) {
			String catename = "."+search;
			List<File> listfile = fileRepository.findByFileNameContaining(catename);
			return listfile;
		} else if (condition.equals("uploader")) {
			List<File> listfile = fileRepository.findByUploaderContaining(search);
			return listfile;
		} else if (condition.equals("size")) {
			try {
				Long size = Long.parseLong(search);
				List<File> listfile = fileRepository.findBySize(size);
				return listfile;
			}
			catch (Exception e) {
			return null;
			}
		}
		return null;
	}

}
