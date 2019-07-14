package com.enjoy.thread;

import com.enjoy.entity.User;
import com.enjoy.mapper.UserMapper;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class UserThread implements Runnable {

    @Resource
    private UserMapper userMapper;

    private List<User> userList;
    private CountDownLatch latch;

    public UserThread(List<User> userList, UserMapper userMapper) {
        this.userList = userList;
        this.userMapper = userMapper;
    }

    public UserThread(List<User> userList, CountDownLatch latch) {
        this.userList = userList;
        this.latch = latch;
    }

    public UserThread(List<User> userList, UserMapper userMapper, CountDownLatch latch) {
        this.userList = userList;
        this.userMapper = userMapper;
        this.latch = latch;
    }

    @Override
    public void run() {
        userMapper.insertByList(userList);
        // 发出线程任务完成的信号
        latch.countDown();
    }
}
