package com.zlf.bbzs.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zhu on 2019/6/26.
 */

@Entity
public class BottleBean {
    long dataTime;
    int capacity;
    String kind;
    String memo;
    @Generated(hash = 608543113)
    public BottleBean(long dataTime, int capacity, String kind, String memo) {
        this.dataTime = dataTime;
        this.capacity = capacity;
        this.kind = kind;
        this.memo = memo;
    }
    @Generated(hash = 94252842)
    public BottleBean() {
    }
    public long getDataTime() {
        return this.dataTime;
    }
    public void setDataTime(long dataTime) {
        this.dataTime = dataTime;
    }
    public int getCapacity() {
        return this.capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
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
