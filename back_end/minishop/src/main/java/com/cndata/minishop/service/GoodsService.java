package com.cndata.minishop.service;

import java.util.List;

import com.cndata.minishop.domain.Goods;
import com.cndata.minishop.domain.GoodsExample;
import com.github.pagehelper.PageInfo;

public interface GoodsService extends BaseService<Goods> {

	public List<Goods> getByList(GoodsExample example);
	
	public PageInfo<Goods> getByPage(GoodsExample example, Integer pageNum, Integer pageSize);
}
