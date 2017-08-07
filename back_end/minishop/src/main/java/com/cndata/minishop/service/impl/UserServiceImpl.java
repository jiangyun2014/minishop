package com.cndata.minishop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cndata.minishop.domain.Users;
import com.cndata.minishop.domain.UsersExample;
import com.cndata.minishop.mapper.UsersMapper;
import com.cndata.minishop.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersMapper usersMapper;
	
	@Override
	public void add(Users users) {
		usersMapper.insert(users);
	}

	@Override
	public void delete(Integer id) {
		usersMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void update(Users users) {
		usersMapper.updateByPrimaryKeySelective(users);
	}

	@Override
	public Users getById(Integer id) {
		return usersMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Users> getByList(UsersExample example) {
		return usersMapper.selectByExample(example);
	}

	@Override
	public PageInfo<Users> getByPage(UsersExample example, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<Users>(usersMapper.selectByExample(example));
	}
}
