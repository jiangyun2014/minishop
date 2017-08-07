package com.cndata.minishop.service;

import java.util.List;

import com.cndata.minishop.domain.Cart;
import com.cndata.minishop.domain.CartExample;
import com.github.pagehelper.PageInfo;

public interface CartService extends BaseService<Cart> {

	public List<Cart> getByList(CartExample example);
	
	public PageInfo<Cart> getByPage(CartExample example, Integer pageNum, Integer pageSize);
}
