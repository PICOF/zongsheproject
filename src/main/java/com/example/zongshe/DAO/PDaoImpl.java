package com.example.zongshe.DAO;

import com.example.zongshe.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
    public int getBedinUse() {
        String sql="select count(*) from bed where inuse=true";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public int getInToday() {
        String sql="select count(*) from bed where inuse=true and iodate=date_format(now(),'%Y-%m-%d')";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public int getOutToday() {
        String sql="select count(*) from bed where inuse=false and iodate=date_format(now(),'%Y-%m-%d')";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
