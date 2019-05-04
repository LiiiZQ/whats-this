package com.yue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.yue.dao.IUser;
import com.yue.model.User;
@Service
public class UserService {
	@Autowired
	private IUser iuser;
    public List<User> findAllUser(){
    	return iuser.findAllUser();
    }
    public int insertUser(User user){
    	return iuser.insertUser(user);
    }
    public int deleteUser(int id){
    	return iuser.deleteUser(id);
    }
	public int updateUser(User user) {
		return iuser.updateUser(user);
	}
	public void mergeUser(User user) {
		 iuser.mergeUser(user);
	}
	public List<User> selectUser(String username_cx) {
		return iuser.selectUser(username_cx);
	}
}
