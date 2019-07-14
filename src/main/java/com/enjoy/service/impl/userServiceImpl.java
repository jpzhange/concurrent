package com.enjoy.service.impl;

import com.enjoy.entity.User;
import com.enjoy.mapper.UserMapper;
import com.enjoy.service.UserService;
import com.enjoy.thread.UserThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class userServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserMapper userMapper;

    @Override
    public void insert(List<User> userList) throws Exception {
        int size = userList.size() % 2000 == 0 ? userList.size() / 2000 : userList.size() / 2000 + 1;

        ExecutorService executor = Executors.newFixedThreadPool(5);

        try {
            CountDownLatch latch = new CountDownLatch(size);
            List<User> userListCopy = new ArrayList<>(2000);

            int fromIndex, toIndex;
            for (int i = 0; i < size; i++) {

                fromIndex = i * 2000;
                toIndex = Math.min(userList.size(), fromIndex + 2000);
                userListCopy = userList.subList(fromIndex, toIndex);
//                userMapper.insertByList(userListCopy);
                executor.execute(new UserThread(userListCopy, userMapper, latch));
            }
            latch.await();
        }finally {
            executor.shutdown();
        }
    }
}
