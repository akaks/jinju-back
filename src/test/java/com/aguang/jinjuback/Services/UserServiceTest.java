package com.aguang.jinjuback.Services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void createUser() throws Exception{
        userService.createUser("13011111111","000000");
        userService.createUser("13022222222","111111");
        userService.createUser("13033333333","222222");
        userService.createUser("13044444444","333333");
        userService.createUser("13055555555","444444");
    }

    @Test
    public void getUser() throws Exception{

    }

    @Test
    public void updateUser() throws Exception{

    }
}
