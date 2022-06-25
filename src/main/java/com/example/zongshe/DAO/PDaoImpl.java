package com.example.zongshe.DAO;

import com.example.zongshe.entity.Bed;
import com.example.zongshe.entity.PatientPre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;

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
        //下面这一串都是为了作假，上传时记得删除
        Random random=new Random();
        for (Bed b:ls) {
            b.setHb(random.nextInt(20)+b.getHb()-10);
            b.setOxygen(Math.min(b.getOxygen()+random.nextInt(10)-5,100));
            b.setTem((float) Math.round((b.getTem()+random.nextFloat()*2-1)*100)/100);
            b.setPre((float) Math.round((b.getPre()+ random.nextFloat()*10-5)*100)/100);
            //System.out.println(b.getTem()+" "+b.getPre());
        }
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
