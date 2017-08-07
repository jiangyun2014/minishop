package com.cndata.minishop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cndata.minishop.domain.Users;
import com.cndata.minishop.domain.Wallet;
import com.cndata.minishop.domain.WalletExample;
import com.cndata.minishop.service.WalletService;
import com.cndata.minishop.web.cache.Cache;
import com.cndata.minishop.web.cache.CacheManager;
import com.github.pagehelper.PageInfo;

@RestController
public class WalletController {

	@Autowired
	private WalletService walletService;
	
	@RequestMapping(value="/api/auth/wallet/add", method=RequestMethod.POST)
	public void add(@RequestBody Wallet wallet){
		walletService.add(wallet);
	}
	
	@RequestMapping(value="/api/auth/wallet/delete/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable Integer id){
		walletService.delete(id);
	}
	
	@RequestMapping(value="/api/auth/wallet/update/{id}", method=RequestMethod.PUT)
	public void update(@PathVariable Integer id, @RequestBody Wallet wallet){
		walletService.update(wallet);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/auth/wallet/{id}", method=RequestMethod.GET)
	public Wallet getById(@PathVariable Integer id){
		return walletService.getById(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/auth/wallet/list", method=RequestMethod.GET)
	public List<Wallet> getByList(Wallet wallet){
		WalletExample example = new WalletExample();
		return walletService.getByList(example);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/auth/wallet/page/{pageNum}/{pageSize}", method=RequestMethod.GET)
	public PageInfo<Wallet> getByPage(Wallet wallet, @PathVariable Integer pageNum, @PathVariable Integer pageSize){
		WalletExample example = new WalletExample();
		return walletService.getByPage(example, pageNum, pageSize);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/auth/wallet/identity", method=RequestMethod.GET)
	public Wallet getByIdentity(HttpServletRequest req){
		Wallet wallet = null;
		String token = req.getHeader("Authorization");
		if(null!=token && !"".equals(token)){
			Cache cache = CacheManager.getCacheInfo(token);
			if(null!=cache){
				Users current_user = (Users) cache.getValue();
				WalletExample example = new WalletExample();
				WalletExample.Criteria criteria = example.createCriteria();
				criteria.andUseridEqualTo(current_user.getId());
				List<Wallet> ret = walletService.getByList(example);
				if(ret!=null && ret.size()>0){
					wallet = ret.get(0);
				}
			}
		}
		return wallet;
	}
}
