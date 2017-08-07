package com.cndata.minishop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cndata.minishop.domain.Cart;
import com.cndata.minishop.domain.CartExample;
import com.cndata.minishop.mapper.CartMapper;
import com.cndata.minishop.service.CartService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("cartService")
public class CartServiceImpl implements CartService {

	@Autowired
	private CartMapper cartMapper;
	
	@Override
	public void add(Cart cart) {
		cartMapper.insert(cart);
	}

	@Override
	public void delete(Integer id) {
		cartMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void update(Cart cart) {
		cartMapper.updateByPrimaryKeySelective(cart);
	}

	@Override
	public Cart getById(Integer id) {
		return cartMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Cart> getByList(CartExample example) {
		return cartMapper.selectByExample(example);
	}

	@Override
	public PageInfo<Cart> getByPage(CartExample example, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<Cart>(cartMapper.selectByExample(example));
	}
}
