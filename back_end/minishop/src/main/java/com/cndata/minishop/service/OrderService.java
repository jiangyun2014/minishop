package com.cndata.minishop.service;

import java.util.List;

import com.cndata.minishop.domain.Orders;
import com.cndata.minishop.domain.OrdersExample;
import com.github.pagehelper.PageInfo;

public interface OrderService extends BaseService<Orders> {

	public List<Orders> getByList(OrdersExample example);
	
	public PageInfo<Orders> getByPage(OrdersExample example, Integer pageNum, Integer pageSize);
}
