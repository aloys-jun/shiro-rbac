package com.aloys.rbac.dao;

import org.springframework.data.repository.Repository;

import com.aloys.rbac.bean.UserInfo;

public interface UserDao extends Repository<UserInfo, Integer>{

	UserInfo findByUserName(String userName);
}
