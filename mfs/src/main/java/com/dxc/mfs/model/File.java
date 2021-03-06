package com.dxc.mfs.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "File")
public class File {
	@Id
	@GeneratedValue
	private int idFile;
	private String fileName;
	private String description;
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "idUser")
//	private User idUser;
	private long size;
	

	private String uploader;
	private String emailUploader;
	private Date uploadDate;
	 @Lob
	 private byte[] data;
	 public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public String getEmailUploader() {
		return emailUploader;
	}

	public void setEmailUploader(String emailUploader) {
		this.emailUploader = emailUploader;
	}

	@OneToMany
	@JsonIgnore
	private List<Comment> commentList;
	@OneToMany
	@JsonIgnore
	private List<Download> download;
	
	private String Type;

	public int getIdFile() {
		return idFile;
	}

	public void setIdFile(int idFile) {
		this.idFile = idFile;
	}
	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public List<Download> getDownload() {
		return download;
	}

	public void setDownload(List<Download> download) {
		this.download = download;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	

}
