package com.cndata.minishop.controller;

import java.util.Date;
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
import com.cndata.minishop.domain.Orders;
import com.cndata.minishop.domain.OrdersExample;
import com.cndata.minishop.domain.Users;
import com.cndata.minishop.service.CartService;
import com.cndata.minishop.service.OrderService;
import com.cndata.minishop.web.cache.Cache;
import com.cndata.minishop.web.cache.CacheManager;
import com.github.pagehelper.PageInfo;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CartService cartService;
	
	@ResponseBody
	@RequestMapping(value="/api/auth/order/add", method=RequestMethod.POST)
	public Map<String,Object> add(@RequestBody Orders order){
		Map<String,Object> result = new HashMap<String,Object>();
		order.setStatus(1);
		order.setOrdertime(new Date());
		orderService.add(order);
		
		//从购物车中移除该商品
		CartExample example = new CartExample();
		CartExample.Criteria criteria = example.createCriteria();
		criteria.andGoodsidEqualTo(order.getGoodsid());
		criteria.andUseridEqualTo(order.getUserid());
		List<Cart> cartList = cartService.getByList(example);
		if(cartList!=null && cartList.size()>0){
			for(Cart tmp : cartList){
				cartService.delete(tmp.getId());
			}
		}
		result.put("status_key", 1);
		result.put("msg", "订单提交成功！");
		return result;
	}
	
	@RequestMapping(value="/api/auth/order/delete/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable Integer id){
		orderService.delete(id);
	}
	
	@RequestMapping(value="/api/auth/order/update/{id}", method=RequestMethod.PUT)
	public void update(@PathVariable Integer id, @RequestBody Orders order){
		orderService.update(order);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/auth/order/{id}", method=RequestMethod.GET)
	public Orders getById(@PathVariable Integer id){
		return orderService.getById(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/auth/order/list", method=RequestMethod.GET)
	public List<Orders> getByList(HttpServletRequest req, Orders order){
		OrdersExample example = new OrdersExample();
		example.setOrderByClause("ordertime desc");
		String token = req.getHeader("Authorization");
		if(null!=token && !"".equals(token)){
			Cache cache = CacheManager.getCacheInfo(token);
			if(null!=cache){
				Users user = (Users) cache.getValue();
				
				OrdersExample.Criteria criteria = example.createCriteria();
				if(order.getStatus()>0){
					criteria.andStatusEqualTo(order.getStatus());
				}
				criteria.andUseridEqualTo(user.getId());
				return orderService.getByList(example);
			}
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="/api/auth/order/page/{pageNum}/{pageSize}", method=RequestMethod.GET)
	public PageInfo<Orders> getByPage(HttpServletRequest req, Orders order, @PathVariable Integer pageNum, @PathVariable Integer pageSize){
		OrdersExample example = new OrdersExample();
		example.setOrderByClause("ordertime desc");
		String token = req.getHeader("Authorization");
		if(null!=token && !"".equals(token)){
			Cache cache = CacheManager.getCacheInfo(token);
			if(null!=cache){
				Users user = (Users) cache.getValue();
				
				OrdersExample.Criteria criteria = example.createCriteria();
				if(order.getStatus()>0){
					criteria.andStatusEqualTo(order.getStatus());
				}
				criteria.andUseridEqualTo(user.getId());
				return orderService.getByPage(example, pageNum, pageSize);
			}
		}
		return null;
	}
}
