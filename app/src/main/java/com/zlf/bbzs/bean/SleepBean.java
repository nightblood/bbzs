package com.zlf.bbzs.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zhu on 2019/6/25.
 */

@Entity
public class SleepBean {
    Long dataTime;
    Long sleepStartTime;
    Long sleepEndTime;
    String memo;
    @Generated(hash = 1764579456)
    public SleepBean(Long dataTime, Long sleepStartTime, Long sleepEndTime,
            String memo) {
        this.dataTime = dataTime;
        this.sleepStartTime = sleepStartTime;
        this.sleepEndTime = sleepEndTime;
        this.memo = memo;
    }
    @Generated(hash = 266794267)
    public SleepBean() {
    }
    public Long getDataTime() {
        return this.dataTime;
    }
    public void setDataTime(Long dataTime) {
        this.dataTime = dataTime;
    }
    public Long getSleepStartTime() {
        return this.sleepStartTime;
    }
    public void setSleepStartTime(Long sleepStartTime) {
        this.sleepStartTime = sleepStartTime;
    }
    public Long getSleepEndTime() {
        return this.sleepEndTime;
    }
    public void setSleepEndTime(Long sleepEndTime) {
        this.sleepEndTime = sleepEndTime;
    }
    public String getMemo() {
        return this.memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
    

}
