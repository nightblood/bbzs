package com.zlf.bbzs.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zhu on 2019/6/24.
 */
@Entity
public class BreastMilkBean {
    Long dataTime; // 记录时间
    Long leftTime; // 左侧时间
    Long rightTime; // 右侧时间
    String memo; //备忘
    @Generated(hash = 874017819)
    public BreastMilkBean(Long dataTime, Long leftTime, Long rightTime,
            String memo) {
        this.dataTime = dataTime;
        this.leftTime = leftTime;
        this.rightTime = rightTime;
        this.memo = memo;
    }
    @Generated(hash = 1853121793)
    public BreastMilkBean() {
    }
    public Long getDataTime() {
        return this.dataTime;
    }
    public void setDataTime(Long dataTime) {
        this.dataTime = dataTime;
    }
    public Long getLeftTime() {
        return this.leftTime;
    }
    public void setLeftTime(Long leftTime) {
        this.leftTime = leftTime;
    }
    public Long getRightTime() {
        return this.rightTime;
    }
    public void setRightTime(Long rightTime) {
        this.rightTime = rightTime;
    }
    public String getMemo() {
        return this.memo;
    }
    public void setMemo(String memo) {
        this.memo = memo;
    }
}
