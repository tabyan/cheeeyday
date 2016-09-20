package com.zmrx.app.domain;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class BaseDomain{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer objectid;

	@Column(name = "add_time",updatable = false,nullable = false)
	private Date addTime;

	@Column(name = "modify_time",nullable = false)
	private Date modifyTime = new Date();

	public void setObjectid(Integer objectid){
		this.objectid = objectid;
	}

	public Integer getObjectid(){
		return objectid;
	}

	public void setAddTime(Date addTime){
		this.addTime = addTime;
	}

	public Date getAddTime(){
		return addTime;
	}

	public void setModifyTime(Date modifyTime){
		this.modifyTime = new Date();
	}

	public Date getModifyTime(){
		return modifyTime;
	}
}