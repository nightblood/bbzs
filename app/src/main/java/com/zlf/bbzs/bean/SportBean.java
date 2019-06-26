package com.zlf.bbzs.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zhu on 2019/6/26.
 */
@Entity
public class SportBean {
    long dataTime;
    long startTime;
    long endTime;

    String kind;
    String memo;
    @Generated(hash = 1078664590)
    public SportBean(long dataTime, long startTime, long endTime, String kind,
            String memo) {
        this.dataTime = dataTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.kind = kind;
        this.memo = memo;
    }
    @Generated(hash = 84405280)
    public SportBean() {
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
    public String getKind() {
        return this.kind;
    }
    public void setKind(String kind) {
        this.kind = kind;
    }
    public String getMemo() {
        return this.memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
}
