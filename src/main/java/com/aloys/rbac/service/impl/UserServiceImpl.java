package com.aloys.rbac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aloys.rbac.bean.UserInfo;
import com.aloys.rbac.dao.UserDao;
import com.aloys.rbac.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserInfo getUserInfoByName(String name) {
		
		return userDao.findByUserName(name);
	}

}
