package com.casic.security.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TestBCrypt {

    @Test
    public void test(){
        //对密码进行加密
        String hashpw= BCrypt.hashpw("123",BCrypt.gensalt());
        System.out.println("==========");
        System.out.println(hashpw);
        //校验密码
        boolean checkpw = BCrypt.checkpw("123", "$2a$10$ZB6frYhcBBAuDzVPZMCe3.0pDBzDYAfW391GOpPKYAfDRiOy24eh.");
        System.out.println(checkpw);
    }
}
