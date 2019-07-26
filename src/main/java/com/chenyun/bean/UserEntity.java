package com.chenyun.bean;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name = "t_user")
@Entity(name="user")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private int age;

    private String note;
}
