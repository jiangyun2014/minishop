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

import com.cndata.minishop.domain.Receiver;
import com.cndata.minishop.domain.ReceiverExample;
import com.cndata.minishop.domain.Users;
import com.cndata.minishop.service.ReceiverService;
import com.cndata.minishop.web.cache.Cache;
import com.cndata.minishop.web.cache.CacheManager;
import com.github.pagehelper.PageInfo;

@RestController
public class ReceiverController {

	@Autowired
	private ReceiverService receiverService;
	
	@ResponseBody
	@RequestMapping(value="/api/auth/receiver/add", method=RequestMethod.POST)
	public Map<String,Object> add(HttpServletRequest req, @RequestBody Receiver receiver){
		Map<String,Object> map = new HashMap<String,Object>();
		String token = req.getHeader("Authorization");
		if(null!=token && !"".equals(token)){
			Cache cache = CacheManager.getCacheInfo(token);
			if(null!=cache){
				Users current_user = (Users) cache.getValue();
				receiver.setUserid(current_user.getId());
				receiverService.add(receiver);
				map.put("status_key", 1);
				map.put("msg", "添加成功！");
			}else{
				map.put("status_key", -1);
				map.put("msg", "添加失败！");
			}
		}else{
			map.put("status_key", -1);
			map.put("msg", "添加失败！");
		}
		return map;
	}
	
	@RequestMapping(value="/api/auth/receiver/delete/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable Integer id){
		receiverService.delete(id);
	}
	
	@RequestMapping(value="/api/auth/receiver/update/{id}", method=RequestMethod.PUT)
	public void update(@PathVariable Integer id, @RequestBody Receiver receiver){
		receiverService.update(receiver);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/auth/receiver/{id}", method=RequestMethod.GET)
	public Receiver getById(@PathVariable Integer id){
		return receiverService.getById(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/auth/receiver/list", method=RequestMethod.GET)
	public List<Receiver> getByList(Receiver receiver){
		ReceiverExample example = new ReceiverExample();
		return receiverService.getByList(example);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/auth/receiver/page/{pageNum}/{pageSize}", method=RequestMethod.GET)
	public PageInfo<Receiver> getByPage(Receiver receiver, @PathVariable Integer pageNum, @PathVariable Integer pageSize){
		ReceiverExample example = new ReceiverExample();
		return receiverService.getByPage(example, pageNum, pageSize);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/auth/receiver/list/identity", method=RequestMethod.GET)
	public List<Receiver> getByIdentity(HttpServletRequest req){
		List<Receiver> receivers = null;
		String token = req.getHeader("Authorization");
		if(null!=token && !"".equals(token)){
			Cache cache = CacheManager.getCacheInfo(token);
			if(null!=cache){
				Users current_user = (Users) cache.getValue();
				ReceiverExample example = new ReceiverExample();
				ReceiverExample.Criteria criteria = example.createCriteria();
				criteria.andUseridEqualTo(current_user.getId());
				receivers = receiverService.getByList(example);
			}
		}
		return receivers;
	}
}
