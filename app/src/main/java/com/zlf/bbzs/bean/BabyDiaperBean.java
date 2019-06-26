package com.zlf.bbzs.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zhu on 2019/6/25.
 */

@Entity
public class BabyDiaperBean {
    Long dataTime;
    Long diaperTime;
    String property;
    String memo;
    @Generated(hash = 493518529)
    public BabyDiaperBean(Long dataTime, Long diaperTime, String property,
            String memo) {
        this.dataTime = dataTime;
        this.diaperTime = diaperTime;
        this.property = property;
        this.memo = memo;
    }
    @Generated(hash = 1033739294)
    public BabyDiaperBean() {
    }
    public Long getDataTime() {
        return this.dataTime;
    }
    public void setDataTime(Long dataTime) {
        this.dataTime = dataTime;
    }
    public Long getDiaperTime() {
        return this.diaperTime;
    }
    public void setDiaperTime(Long diaperTime) {
        this.diaperTime = diaperTime;
    }
    public String getProperty() {
        return this.property;
    }
    public void setProperty(String property) {
        this.property = property;
    }
    public String getMemo() {
        return this.memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
}
