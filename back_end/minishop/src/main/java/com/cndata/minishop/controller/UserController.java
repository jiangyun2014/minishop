package com.cndata.minishop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cndata.minishop.domain.Users;
import com.cndata.minishop.domain.UsersExample;
import com.cndata.minishop.domain.Wallet;
import com.cndata.minishop.service.UserService;
import com.cndata.minishop.service.WalletService;
import com.cndata.minishop.web.cache.Cache;
import com.cndata.minishop.web.cache.CacheManager;
import com.cndata.minishop.web.utils.Md5Utils;
import com.github.pagehelper.PageInfo;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private WalletService walletService;
	
	@ResponseBody
	@RequestMapping(value="/api/user/add", method=RequestMethod.POST)
	public Map<String,Object> add(@RequestBody Users user){
		Map<String,Object> result = new HashMap<String,Object>();
		
		//判断账号是否已经注册
		UsersExample example = new UsersExample();
		UsersExample.Criteria criteria = example.createCriteria();
		criteria.andAccountEqualTo(user.getAccount());
		List<Users> ret = userService.getByList(example);
		if(ret!=null && ret.size()>0){//已存在
			result.put("status_key", 0);
			result.put("msg", "此账号已经注册了！");
		}else{
			//encode password
			user.setPassword(Md5Utils.encode(user.getPassword()));
			userService.add(user);
			
			//创建用户钱包
			List<Users> retList = userService.getByList(example);
			if(retList!=null && retList.size()>0){//已存在
				Users current_user = retList.get(0);
				Wallet wallet = new Wallet();
				wallet.setUserid(current_user.getId());
				wallet.setCurrency(0.0);
				walletService.add(wallet);
			}
			result.put("status_key", 1);
			result.put("msg", "注册成功！");
		}
		return result;
	}
	
	@RequestMapping(value="/api/auth/user/delete/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable Integer id){
		userService.delete(id);
	}
	
	@RequestMapping(value="/api/auth/user/update/{id}", method=RequestMethod.PUT)
	public void update(@PathVariable Integer id, @RequestBody Users user){
		userService.update(user);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/auth/user/{id}", method=RequestMethod.GET)
	public Users getById(@PathVariable Integer id){
		return userService.getById(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/auth/user/list", method=RequestMethod.GET)
	public List<Users> getByList(Users user){
		UsersExample example = new UsersExample();
		return userService.getByList(example);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/auth/user/page/{pageNum}/{pageSize}", method=RequestMethod.GET)
	public PageInfo<Users> getByPage(Users user, @PathVariable Integer pageNum, @PathVariable Integer pageSize){
		UsersExample example = new UsersExample();
		return userService.getByPage(example, pageNum, pageSize);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/auth/user/identity", method=RequestMethod.GET)
	public Users getByIdentity(HttpServletRequest req){
		Users user = null;
		String token = req.getHeader("Authorization");
		if(null!=token && !"".equals(token)){
			Cache cache = CacheManager.getCacheInfo(token);
			if(null!=cache){
				user = (Users) cache.getValue();
			}
		}
		return user;
	}
}
