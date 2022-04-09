package com.example.zongshe.entity;

public class Bed {
    private int bid;
    private String name;
    private String iodate;
    private float tem;
    private int oxygen;
    private int hb;
    private float pre;
    private boolean inuse;
    private String status;
    private int arr;
    private int surgery;
    private String drid;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIodate() {
        return iodate;
    }

    public void setIodate(String iodate) {
        this.iodate = iodate;
    }

    public float getTem() {
        return tem;
    }

    public void setTem(float tem) {
        this.tem = tem;
    }

    public int getOxygen() {
        return oxygen;
    }

    public void setOxygen(int oxygen) {
        this.oxygen = oxygen;
    }

    public int getHb() {
        return hb;
    }

    public void setHb(int hb) {
        this.hb = hb;
    }

    public float getPre() {
        return pre;
    }

    public void setPre(float pre) {
        this.pre = pre;
    }

    public String getDrid() {
        return drid;
    }

    public void setDrid(String drid) {
        this.drid = drid;
    }

    public boolean isInuse() {
        return inuse;
    }

    public void setInuse(boolean inuse) {
        this.inuse = inuse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSurgery() {
        return surgery;
    }

    public void setSurgery(int surgery) {
        this.surgery = surgery;
    }

    public int getArr() {
        return arr;
    }

    public void setArr(int arr) {
        this.arr = arr;
    }
}
