package com.casic.security.springboot.dao;

import com.casic.security.springboot.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
//根据账号查询用户信息
    public UserDto getUserByUserName(String username){
        String sql="select id,username,password,fullname,mobile from t_user where username= ?";
        List<UserDto> list=jdbcTemplate.query(sql,new Object[]{username},new BeanPropertyRowMapper<>(UserDto.class));
        List<Map> listMap=jdbcTemplate.query(sql,new Object[]{username},new BeanPropertyRowMapper<>(Map.class));
        System.out.println(listMap);
        if (list == null && list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }
}
