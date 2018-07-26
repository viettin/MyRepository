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
	private long limitUp;
	private long totalLimitUp;
	private long limitDown;
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

	public long getTotalLimitUp() {
		return totalLimitUp;
	}

	public void setTotalLimitUp(long totalLimitUp) {
		this.totalLimitUp = totalLimitUp;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public long getLimitUp() {
		return limitUp;
	}

	public void setLimitUp(long limitUp) {
		this.limitUp = limitUp;
	}

	public long getLimitDown() {
		return limitDown;
	}

	public void setLimitDown(long limitDown) {
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
