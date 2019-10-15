package com.zzj.solrapi.entity;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {

    @Override
    public String toString(){
        String end = "";
        end = JSON.toJSONString(this);
        return end;
    }
}
