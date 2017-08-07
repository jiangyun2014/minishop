package com.cndata.minishop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cndata.minishop.domain.Orders;
import com.cndata.minishop.domain.OrdersExample;
import com.cndata.minishop.mapper.OrdersMapper;
import com.cndata.minishop.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrdersMapper orderMapper;
	
	@Override
	public void add(Orders order) {
		orderMapper.insert(order);
	}

	@Override
	public void delete(Integer id) {
		orderMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void update(Orders order) {
		orderMapper.updateByPrimaryKeySelective(order);
	}

	@Override
	public Orders getById(Integer id) {
		return orderMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Orders> getByList(OrdersExample example) {
		return orderMapper.selectByExample(example);
	}

	@Override
	public PageInfo<Orders> getByPage(OrdersExample example, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<Orders>(orderMapper.selectByExample(example));
	}
}
