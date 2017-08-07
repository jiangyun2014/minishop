package com.cndata.minishop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cndata.minishop.domain.Users;
import com.cndata.minishop.domain.UsersExample;
import com.cndata.minishop.service.UserService;
import com.cndata.minishop.web.cache.Cache;
import com.cndata.minishop.web.cache.CacheManager;
import com.cndata.minishop.web.utils.JWTUtils;
import com.cndata.minishop.web.utils.Md5Utils;

@RestController
public class LoginController {

	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/api/user/login")
	public Map<String,Object> login(@RequestBody Users user){
		Map<String,Object> result = new HashMap<String,Object>();
		//验证用户身份
		UsersExample example = new UsersExample();
		UsersExample.Criteria criteria = example.createCriteria();
		criteria.andAccountEqualTo(user.getAccount());
		List<Users> ret = userService.getByList(example);
		if(ret!=null && ret.size()>0){//已存在
			Users current_user = ret.get(0);
			boolean flag = current_user.getPassword().equals(Md5Utils.encode(user.getPassword()));
			if(flag){
				//生成Token
				String jwt = JWTUtils.createJWT(
						current_user.getId()+"", 
						current_user.getUsername(), 
						current_user.getAccount(), 
						3600, 
						current_user.getPassword());
				
				//存储Token
				Cache cache = new Cache();
				cache.setKey(jwt);
				cache.setValue(current_user);
				CacheManager.putCache(jwt, cache);
				
				result.put("token", jwt);
				result.put("status_key", 1);
				result.put("msg", "登录成功！");
			}else{
				result.put("status_key", 0);
				result.put("msg", "密码错误！");
			}
		}else{
			result.put("status_key", -1);
			result.put("msg", "此账号不存在！");
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/api/user/logout")
	public Map<String,Object> logout(Users user){
		Map<String,Object> result = new HashMap<String,Object>();
		
		result.put("status_key", -1);
		result.put("msg", "此账号不存在！");
		return result;
	}
}
