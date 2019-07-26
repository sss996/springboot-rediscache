package com.chenyun.service;

import com.chenyun.bean.UserEntity;

import java.util.List;

public interface UserService {

    public UserEntity getUser(Long id);
//    public List<UserEntity> findUsers(String userName, String note);
    public UserEntity insertUser(UserEntity user);
    public UserEntity updateUser(Long id, String userName);
    public void deleteUser(Long id);
}
