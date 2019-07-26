package com.chenyun.service.impl;

import com.chenyun.bean.UserEntity;
import com.chenyun.dao.UserDao;
import com.chenyun.service.BaseCurd;
import com.chenyun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;



@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Resource
    private BaseCurd baseCurd;


    // 获取用户，取参数id缓存用户
    // @Cacheable 表示先从缓存中通过定义的键查询，如果可以查询到数据，则返回，
    //             否则执行该方法，返回数据，并将返回结果保存到缓存中。
    @Override
    @Cacheable(value="redisCache", key="'redis_user_'+#id")
    public UserEntity getUser(Long id) {
        return userDao.getOne(id);
    }


//    @Override
//    public List<UserEntity> findUsers(String userName, String note) {
//
//        return  userDao.getUserList(userName);
//    }


    //@CachePut 表示将返回的方法结果，存放到缓存中。
    @Override
    @Transactional
    @CachePut(value="redisCache", key = "'redis_user_'+#result.id")
    public UserEntity insertUser(UserEntity user) {
        UserEntity user1 = user;
        userDao.save(user);
        return user;
    }

    // 更新数据后，更新缓存，如果 condition 配置项使结果返回null，不缓存
    @Override
    @Transactional
    @CachePut(value="redisCache", condition="#result !='null'", key = "'redis_user_'+#id")
    public UserEntity updateUser(Long id, String userName) {

        // 此处调用 getUser 方法，该方法缓存注解失效，
        // 所以这里还是会执行sql,将查询到数据库的最新数据
        UserEntity user = this.getUser(id);

        if (user == null ) return null;
        user.setUserName( userName);
        userDao.save(user);
        return user;
    }

    //@CacheEvict 通过定义的键值移除缓存，它有一个boolean类型的配置：beforeInvacation,
    //             表示在方法执行之前或之后移除缓存，默认值为false：在方法执行之后移除缓存。
    // 移除缓存
    @Override
    @Transactional
    @CacheEvict(value="redisCache", key="'redis_user_'+#id", beforeInvocation = false)
    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }
}
