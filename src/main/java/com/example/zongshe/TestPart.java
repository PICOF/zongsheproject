package com.example.zongshe;


import com.example.zongshe.DAO.PDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testng.annotations.Test;

import java.util.List;

public class TestPart {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Test
    public void test1(){
        String sql="select tpre,ypre from prelist where pbid=?";
        List<String> ls=jdbcTemplate.query(sql, new BeanPropertyRowMapper<String>(String.class),1);
        System.out.println((String) ls.toArray()[0]+(String) ls.toArray()[1]);
    }
}
