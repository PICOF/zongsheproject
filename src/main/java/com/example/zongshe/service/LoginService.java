package com.example.zongshe.service;

import com.example.zongshe.DAO.PDaoImpl;
import com.example.zongshe.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService {
    @Autowired
    private PDaoImpl P;
    @Autowired
    private Doctor doctor;
    private final Integer bednum=36;
    @Test
    public boolean loginbyWid(String wid,String psw){
        String s=P.findDrPswbyWid(wid);
        if(s==null){
            return false;
        }
        if (psw.equals(s)) {
            doctor.setAll(wid,P.findNamebyWid(wid));
            return true;
        }else {
            return false;
        }
    }
    public boolean loginbyName(String name,String psw){
        String s=P.findDrPswbyName(name);
        if(s==null){
            return false;
        }
        if (psw.equals(s)) {
            doctor.setAll(P.findWidbyName(name),name);
            return true;
        }else {
            return false;
        }
    }
    public List<Integer> getBedInfo(){
        List<Integer> li=new ArrayList<>();
        Integer i=P.getBedinUse();
        li.add(i);
        li.add(P.getInToday());
        li.add(P.getOutToday());
        li.add(bednum-i);
        return li;
    }
}