package com.dxc.mfs.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.dxc.mfs.model.Comment;
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
	private String uploader;
	private String emailUploader;
	private Date uploadDate;
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

	@OneToMany(mappedBy = "idFile", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Comment> commentList;
	@OneToMany(mappedBy = "idFile", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Download> download;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idType")
	private Type idType;

	public int getIdFile() {
		return idFile;
	}

	public void setIdFile(int idFile) {
		this.idFile = idFile;
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

	public Type getIdType() {
		return idType;
	}

	public void setIdType(Type idType) {
		this.idType = idType;
	}

}
