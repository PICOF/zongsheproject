package com.example.zongshe.DAO;

public interface PDao {
    String findDrPswbyWid(String wid);
    String findDrPswbyName(String name);
    String findNamebyWid(String wid);
    String findWidbyName(String name);
    int getBedinUse();
    int getInToday();
    int getOutToday();
}
