package com.zmrx.app.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cheeryday_general_cheeryday")
public class GeneralCheeryDay extends BaseDomain{

    public String name;//customer cheeryday name

	@Column(name = "begin_time")
	public Date beginTime;// cheeryday begin
	@Column(name="end_time")
	public Date endTime;//cheeryday end

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

}