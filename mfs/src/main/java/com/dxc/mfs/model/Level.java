package com.dxc.mfs.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "level")
public class Level {
	@Id
	@GeneratedValue
	private int idLevel;
	private String levelName;
	private int limitUp;
	private int limitDown;
	private String img;
	@OneToMany(mappedBy = "idLevel", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<User> user;

	public int getIdLevel() {
		return idLevel;
	}

	public void setIdLevel(int idLevel) {
		this.idLevel = idLevel;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public int getLimitUp() {
		return limitUp;
	}

	public void setLimitUp(int limitUp) {
		this.limitUp = limitUp;
	}

	public int getLimitDown() {
		return limitDown;
	}

	public void setLimitDown(int limitDown) {
		this.limitDown = limitDown;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}

}
