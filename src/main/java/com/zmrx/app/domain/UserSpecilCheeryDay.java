package com.zmrx.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "cheeryday_user_specil_cheeryday")
public class UserSpecilCheeryDay extends BaseDomain{

	public String name;//customer cheeryday name

	@Column(name = "begin_time")
	public Date beginTime;// cheeryday begin

	@Column(name="end_time")
	public Date endTime;//cheeryday end

	private String userid;//user

	public void setName(String name){
		this.name =name;
	}

	public String getName(){
		return name;
	}

	public void setBeginTime(Date beginTime){
		this.beginTime = beginTime;
	}

	public Date getBeginTime(){
		return beginTime;
	}

	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}

	public Date getEndTime(){
		return endTime;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
}