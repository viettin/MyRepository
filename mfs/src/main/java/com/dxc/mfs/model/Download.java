package com.dxc.mfs.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.dxc.mfs.model.File;
import com.dxc.mfs.model.User;

@Entity
@Table(name = "download")
public class Download implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Basic(optional = false)
	@NotNull
	@Column(name = "datedownload", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date datedownload;
	@JoinColumn(name = "id_user", referencedColumnName = "idUser")
	@ManyToOne
	private User idUser;
	@JoinColumn(name = "id_file", referencedColumnName = "idFile")
	@ManyToOne
	private File idFile;

	public Download() {
	}

	public Download(Integer id) {
		this.id = id;
	}

	public Download(Integer id, Date datedownload) {
		this.id = id;
		this.datedownload = datedownload;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDatedownload() {
		return datedownload;
	}

	public void setDatedownload(Date datedownload) {
		this.datedownload = datedownload;
	}

	public User getIdUser() {
		return idUser;
	}

	public void setIdUser(User idUser) {
		this.idUser = idUser;
	}

	public File getIdFile() {
		return idFile;
	}

	public void setIdFile(File idFile) {
		this.idFile = idFile;
	}
}
