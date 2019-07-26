package com.chenyun.service;

import com.chenyun.bean.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BaseCurd extends CrudRepository<UserEntity, Long> {

}
