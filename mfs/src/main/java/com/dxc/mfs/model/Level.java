package com.dxc.mfs.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Lob;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "level")
public class Level {
	@Id
	private int idLevel;
	private String levelName;
	private byte limitUp;
	private byte totalLimitUp;
	private byte limitDown;
	private String img;
	@OneToMany
	@JsonIgnore
	private List<User> userList;

	public int getIdLevel() {
		return idLevel;
	}

	public void setIdLevel(int idLevel) {
		this.idLevel = idLevel;
	}

	public String getLevelName() {
		return levelName;
	}

	public byte getTotalLimitUp() {
		return totalLimitUp;
	}

	public void setTotalLimitUp(byte totalLimitUp) {
		this.totalLimitUp = totalLimitUp;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public byte getLimitUp() {
		return limitUp;
	}

	public void setLimitUp(byte limitUp) {
		this.limitUp = limitUp;
	}

	public byte getLimitDown() {
		return limitDown;
	}

	public void setLimitDown(byte limitDown) {
		this.limitDown = limitDown;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

}
