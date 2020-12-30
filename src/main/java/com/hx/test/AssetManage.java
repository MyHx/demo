package com.hx.test;

import java.util.Date;

public class AssetManage {
    private Date creationTime;

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public String toString() {
        return "AssetManage{" +
                "creationTime=" + creationTime +
                '}';
    }
}
