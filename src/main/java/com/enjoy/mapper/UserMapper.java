package com.enjoy.mapper;

import com.enjoy.entity.User;

import java.util.List;

public interface UserMapper {
    public int insertByList(List<User> userList);

    public List<User> selectByName(String name);
}
