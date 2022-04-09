package com.example.zongshe.DAO;

import com.example.zongshe.entity.Bed;
import com.example.zongshe.entity.Doctor;
import com.example.zongshe.entity.PatientPre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PDaoImpl implements PDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public String findDrPswbyWid(String wid) {
        try{
            String sql = "select password from drlist where wid=?";
            String psw = jdbcTemplate.queryForObject(sql, String.class, wid);
            return psw;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public String findDrPswbyName(String name) {
        try{
            String sql = "select password from drlist where name=?";
            String psw = jdbcTemplate.queryForObject(sql, String.class, name);
            return psw;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public String findNamebyWid(String wid) {
        try {
            String sql = "select name from drlist where wid=?";
            String name = jdbcTemplate.queryForObject(sql, String.class, wid);
            return name;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String findWidbyName(String name) {
        try {
            String sql = "select wid from drlist where name=?";
            String wid = jdbcTemplate.queryForObject(sql, String.class, name);
            return wid;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Bed> getAllBed() {
        String sql="select * from bed where inuse=true or iodate=date_format(now(),'%Y-%m-%d')";
        List<Bed> ls=jdbcTemplate.query(sql, new BeanPropertyRowMapper<Bed>(Bed.class));
        return ls;
    }

    @Override
    public PatientPre getPre(int bid) {
        String sql="select * from prelist where pbid=?";
        PatientPre ls=jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<PatientPre>(PatientPre.class),bid);
        return ls;
    }

    @Override
    public String getArr(int bid, String date) {
        String sql = "select "+date+" from arrange where bid = ?";
        String arr = jdbcTemplate.queryForObject(sql, String.class,bid);
        return arr;
    }

    @Override
    public void setArr(int bid, String date, String data) {
        String sql ="update arrange set "+date+"=? where bid=?";
        Object[] args={data,bid};
        int insert=jdbcTemplate.update(sql,args);
        System.out.println("修改了 "+bid+" 号床病人 "+insert+"条结果："+data);
    }
}
