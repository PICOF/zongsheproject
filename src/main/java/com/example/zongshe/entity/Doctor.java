package com.example.zongshe.entity;

import org.springframework.stereotype.Component;

@Component
public class Doctor {
    private String wid;
    private String name;

    public void setWid(String wid) {
        this.wid = wid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAll(String wid,String name){
        this.wid = wid;
        this.name = name;
    }

    public String getWid() {
        return wid;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "wid='" + wid + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
