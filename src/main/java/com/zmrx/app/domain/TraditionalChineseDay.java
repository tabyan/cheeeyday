package com.zmrx.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by tabyan on 16-9-19.
 */
@Entity
@Table(name = "cheeryday_traditional_chineseday")
public class TraditionalChineseDay extends BaseDomain{

    public String name;
    @Column(name = "chineseday_time")
    public Date chinesedayTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getChinesedayTime() {
        return chinesedayTime;
    }

    public void setChinesedayTime(Date chinesedayTime) {
        this.chinesedayTime = chinesedayTime;
    }
}
