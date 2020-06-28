package com.casic.security.springboot.dao;

import com.casic.security.springboot.model.PermissionDto;
import com.casic.security.springboot.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
//根据账号查询用户信息
    public UserDto getUserByUserName(String username){
        String sql="select id,username,password,fullname,mobile from t_user where username= ?";
        List<UserDto> list=jdbcTemplate.query(sql,new Object[]{username},new BeanPropertyRowMapper<>(UserDto.class));
//        List<Map> listMap=jdbcTemplate.query(sql,new Object[]{username},new BeanPropertyRowMapper<>(Map.class));
//        System.out.println(listMap);
        if (list == null && list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }

    //根据用户id查询用户权限
    public List<String>findPermissionsByUserId(String userId){
        String sql="SELECT * from t_permission where id in(\n" +
                "\tselect permission_id from t_role_permission where role_id in(\n" +
                "\t    select role_id from t_user_role where user_id=? \n" +
                "\t)\n" +
                ")";
        List<PermissionDto> list = jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(PermissionDto.class));
        List<String> perssions=new ArrayList<>();
        list.forEach(c -> perssions.add(c.getCode()));
        return perssions;
    }
}
