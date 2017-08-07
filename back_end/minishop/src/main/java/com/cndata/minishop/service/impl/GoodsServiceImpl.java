package com.cndata.minishop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cndata.minishop.domain.Goods;
import com.cndata.minishop.domain.GoodsExample;
import com.cndata.minishop.mapper.GoodsMapper;
import com.cndata.minishop.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsMapper goodsMapper;
	
	@Override
	public void add(Goods goods) {
		goodsMapper.insert(goods);
	}

	@Override
	public void delete(Integer id) {
		goodsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void update(Goods goods) {
		goodsMapper.updateByPrimaryKeySelective(goods);
	}

	@Override
	public Goods getById(Integer id) {
		return goodsMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Goods> getByList(GoodsExample example) {
		return goodsMapper.selectByExample(example);
	}

	@Override
	public PageInfo<Goods> getByPage(GoodsExample example, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<Goods>(goodsMapper.selectByExample(example));
	}
}
