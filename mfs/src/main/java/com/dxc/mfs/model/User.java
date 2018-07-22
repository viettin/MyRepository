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
import javax.xml.bind.annotation.XmlTransient;

import com.dxc.mfs.model.Download;
import com.dxc.mfs.model.File;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue
	private int idUser;
	private String username;
	private String password;
	private String email;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idLevel")
	private Level idLevel;
	private int currentUp;
	private int currentDown;
	private Date createDate;
	private boolean isAdmin;

	@OneToMany(mappedBy = "idUser")
	@JsonIgnore
	private List<File> filesList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser")
	@JsonIgnore
	private List<Download> downloadList;

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Level getIdLevel() {
		return idLevel;
	}

	public void setIdLevel(Level idLevel) {
		this.idLevel = idLevel;
	}

	public int getCurrentUp() {
		return currentUp;
	}

	public void setCurrentUp(int currentUp) {
		this.currentUp = currentUp;
	}

	public int getCurrentDown() {
		return currentDown;
	}

	public void setCurrentDown(int currentDown) {
		this.currentDown = currentDown;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public List<File> getFilesList() {
		return filesList;
	}

	public void setFilesList(List<File> filesList) {
		this.filesList = filesList;
	}

	public List<Download> getDownloadList() {
		return downloadList;
	}

	public void setDownloadList(List<Download> downloadList) {
		this.downloadList = downloadList;
	}

	@Override
	public String toString() {
		return "User [id=" + idUser + ", username=" + username + ", password=" + password + "]";
	}

}
