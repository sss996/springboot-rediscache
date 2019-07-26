package com.chenyun.web;

import com.chenyun.bean.UserEntity;
import com.chenyun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UseController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    public void getUser(Long id){
        System.out.println(userService.getUser(id));
    }

    @RequestMapping("/insertUser")
    public void insertUser(){
        UserEntity user = new UserEntity();
        user.setUserName("chenyun");
        user.setNote("add demo!");
        userService.insertUser(user);
    }

    @RequestMapping("/updateUser")
    public void updateUser(Long id ,String userName){
        UserEntity user = userService.updateUser(id,userName);
        System.out.println(user);
    }

    @RequestMapping("/deleteUser")
    public void deleteUser(Long id){
        userService.deleteUser(id);
    }


}
