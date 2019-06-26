package com.zlf.bbzs.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zhu on 2019/6/26.
 */

@Entity
public class HangOutBean {
    long dataTime;
    long startTime;
    long endTime;

    String memo;

    @Generated(hash = 2017156865)
    public HangOutBean(long dataTime, long startTime, long endTime, String memo) {
        this.dataTime = dataTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.memo = memo;
    }

    @Generated(hash = 1569231326)
    public HangOutBean() {
    }

    public long getDataTime() {
        return this.dataTime;
    }

    public void setDataTime(long dataTime) {
        this.dataTime = dataTime;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
