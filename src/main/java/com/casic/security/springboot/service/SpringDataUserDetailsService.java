package com.casic.security.springboot.service;

import com.casic.security.springboot.dao.UserDao;
import com.casic.security.springboot.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SpringDataUserDetailsService implements UserDetailsService {
    @Autowired
    UserDao userDao;
    //根据账号查询用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        //将来连接数据库根据账号查询用户信息
        UserDto userDto = userDao.getUserByUserName(username);
        if(userDto==null){
            //如果用户查不到，就返回null,由provider来抛异常
            return null;
        }
        //暂时采用模拟方式
        //登录账号
        System.out.println(userDto);
        //暂时采用模拟方式
//        UserDetails userDetails = User.withUsername("zhangsan").password("$2a$10$psjg7Sox1JLsrOZ5aF1qaeQJ7KrSaZ0/11J9XWfYwZyp2s.TeSKn2").authorities("p1").build();
        UserDetails userDetails = User.withUsername(userDto.getUsername()).password(userDto.getPassword()).authorities("p1").build();

        return userDetails;
    }
}
