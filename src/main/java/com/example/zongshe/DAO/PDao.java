package com.example.zongshe.DAO;

import com.example.zongshe.entity.Bed;
import com.example.zongshe.entity.PatientPre;

import java.util.List;

public interface PDao {
    String findDrPswbyWid(String wid);
    String findDrPswbyName(String name);
    String findNamebyWid(String wid);
    String findWidbyName(String name);
    List<Bed> getAllBed();
    PatientPre getPre(int bid);
    String getArr(int bid,String date);
    void setArr(int bid,String date,String data);
}
