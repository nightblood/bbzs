package com.zlf.bbzs.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zhu on 2019/6/26.
 */

@Entity
public class PooBean {
    long dataTime;
    String kind;
    String memo;
    @Generated(hash = 2069205366)
    public PooBean(long dataTime, String kind, String memo) {
        this.dataTime = dataTime;
        this.kind = kind;
        this.memo = memo;
    }
    @Generated(hash = 1369853901)
    public PooBean() {
    }
    public long getDataTime() {
        return this.dataTime;
    }
    public void setDataTime(long dataTime) {
        this.dataTime = dataTime;
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
