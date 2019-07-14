package com.enjoy.service;

import com.enjoy.Application;
import com.enjoy.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class userTest {

    @Resource
    private UserService userService;

    @Test
    public void test() {
        long starttime = System.currentTimeMillis();
        List<User> userList = new ArrayList<>();
        for (int i =0; i< 200000; i++) {

            User user = new User();
            user.setAddress("山东临沂");
            user.setMail("469754506@qq.com");
            user.setPassword("123456");
            user.setName("张建平" + i);
            user.setReamark(user.getName() + "是个帅小伙");
            userList.add(user);
        }

        try {
            userService.insert(userList);
        }catch (Exception e) {
            e.printStackTrace();
        }

        long entime = System.currentTimeMillis();

        System.out.println(entime - starttime);
    }
}
