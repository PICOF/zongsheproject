package com.example.zongshe.DAO;

import com.example.zongshe.entity.Bed;

import java.util.List;

public interface PDao {
    String findDrPswbyWid(String wid);
    String findDrPswbyName(String name);
    String findNamebyWid(String wid);
    String findWidbyName(String name);
    List<Bed> getAllBed();
    int getBedinUse();
    int getInToday();
    int getOutToday();
}
