package com.example.zongshe.service;

import com.example.zongshe.DAO.PDaoImpl;
import com.example.zongshe.entity.Bed;
import com.example.zongshe.entity.PatientPre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {
    @Autowired
    private PDaoImpl P;
    public List<Bed> getBed(){
        return P.getAllBed();
    }
    public PatientPre getPatientPre(int bid){
        return P.getPre(bid);
    }
    public String getArrList(int bid,String date){
        return P.getArr(bid,date);
    }
    public void setArrList(int bid, String date, String data){
        P.setArr(bid, date, data);
    }
}
