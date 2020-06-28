package com.casic.security.springboot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @RequestMapping(value = "/login-success",produces = {"text/plain;charset=UTF-8"})
    public String loginSuccess(){
        //提示具体用户名称登录成功
        return getUsername()+"登录成功";
    }
    @RequestMapping(value = "/r/r1",produces = {"text/plain;charset=UTF-8"})
    @PreAuthorize("hasAnyAuthority('p1')")//拥有p1权限才可以访问
    public String r1(){
        return getUsername()+"访问资源r1";
    }
    @RequestMapping(value = "/r/r2",produces = {"text/plain;charset=UTF-8"})
    @PreAuthorize("hasAnyAuthority('p2')")//拥有p2权限才可以访问
    public String r2(){
        return getUsername()+"访问资源r2";
    }

    //获取当前用户信息
    private String getUsername(){
        String username=null;
        //当前认证通过的用户身份
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        //获取用户的身份
        Object principal = authentication.getPrincipal();
        if(principal==null){
            username="匿名";
        }
        if(principal instanceof UserDetails){
            UserDetails userDetails= (UserDetails) principal;
            username=userDetails.getUsername();
        }else {
            username=principal.toString();
        }
        return username;
    }
}
