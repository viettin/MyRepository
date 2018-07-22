package com.dxc.mfs.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {
	@Id
	@GeneratedValue
	private int cmtId;
	private String content;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idUser")
	private User user;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idFile")
	private File idFile;

	public int getCmtId() {
		return cmtId;
	}

	public void setCmtId(int cmtId) {
		this.cmtId = cmtId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public File getIdFile() {
		return idFile;
	}

	public void setIdFile(File idFile) {
		this.idFile = idFile;
	}

}
