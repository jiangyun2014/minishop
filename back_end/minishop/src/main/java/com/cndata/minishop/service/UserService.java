package com.cndata.minishop.service;

import java.util.List;

import com.cndata.minishop.domain.Users;
import com.cndata.minishop.domain.UsersExample;
import com.github.pagehelper.PageInfo;

public interface UserService extends BaseService<Users> {

	public List<Users> getByList(UsersExample example);
	
	public PageInfo<Users> getByPage(UsersExample example, Integer pageNum, Integer pageSize);
}
