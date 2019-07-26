package com.chenyun.dao;

import com.chenyun.bean.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<UserEntity,Long>   {


//    @Query("Select UserEntity from UserEntity where userName=?1")
//    public List<UserEntity> getUserList(String username);
}
