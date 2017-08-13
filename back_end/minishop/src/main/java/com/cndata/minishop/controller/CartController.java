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

import com.cndata.minishop.domain.Cart;
import com.cndata.minishop.domain.CartExample;
import com.cndata.minishop.domain.Goods;
import com.cndata.minishop.domain.Users;
import com.cndata.minishop.service.CartService;
import com.cndata.minishop.service.GoodsService;
import com.cndata.minishop.web.cache.Cache;
import com.cndata.minishop.web.cache.CacheManager;
import com.github.pagehelper.PageInfo;

@RestController
public class CartController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private GoodsService goodsService;
	
	@ResponseBody
	@RequestMapping(value="/api/auth/cart/add", method=RequestMethod.POST)
	public Map<String,Object> add(HttpServletRequest req, @RequestBody Cart cart){
		Map<String,Object> result = new HashMap<String,Object>();
		
		String token = req.getHeader("Authorization");
		if(null!=token && !"".equals(token)){
			Cache cache = CacheManager.getCacheInfo(token);
			if(null!=cache){
				Users user = (Users) cache.getValue();
				
				CartExample example = new CartExample();
				CartExample.Criteria criteria = example.createCriteria();
				criteria.andGoodsidEqualTo(cart.getGoodsid());
				criteria.andUseridEqualTo(user.getId());
				List<Cart> cartList = cartService.getByList(example);
				if(cartList!=null && cartList.size()>0){//若购物车中已有该商品，则数量加1
					cart = cartList.get(0);
					cart.setQuantity(cart.getQuantity()+1);
					cartService.update(cart);
				}else{
					Goods goods = goodsService.getById(cart.getGoodsid());
					cart.setPrice(goods.getPrice());
					cart.setQuantity(1);
					cart.setUserid(user.getId());
					cartService.add(cart);
				}
			}
		}
		return result;
	}
	
	@RequestMapping(value="/api/auth/cart/delete/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable Integer id){
		cartService.delete(id);
	}
	
	@RequestMapping(value="/api/auth/cart/update/{id}", method=RequestMethod.PUT)
	public void update(@PathVariable Integer id, @RequestBody Cart cart){
		cartService.update(cart);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/auth/cart/{id}", method=RequestMethod.GET)
	public Cart getById(@PathVariable Integer id){
		return cartService.getById(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/auth/cart/list", method=RequestMethod.GET)
	public List<Cart> getByList(HttpServletRequest req, Cart cart){
		CartExample example = new CartExample();
		CartExample.Criteria criteria = example.createCriteria();
		String token = req.getHeader("Authorization");
		if(null!=token && !"".equals(token)){
			Cache cache = CacheManager.getCacheInfo(token);
			if(null!=cache){
				Users user = (Users) cache.getValue();
				criteria.andUseridEqualTo(user.getId());
				return cartService.getByList(example);
			}
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/api/auth/cart/page/{pageNum}/{pageSize}", method=RequestMethod.GET)
	public PageInfo<Cart> getByPage(HttpServletRequest req, Cart cart, @PathVariable Integer pageNum, @PathVariable Integer pageSize){
		CartExample example = new CartExample();
		CartExample.Criteria criteria = example.createCriteria();
		String token = req.getHeader("Authorization");
		if(null!=token && !"".equals(token)){
			Cache cache = CacheManager.getCacheInfo(token);
			if(null!=cache){
				Users user = (Users) cache.getValue();
				criteria.andUseridEqualTo(user.getId());
				return cartService.getByPage(example, pageNum, pageSize);
			}
		}
		return null;
	}
}
