package com.cndata.minishop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cndata.minishop.domain.Goods;
import com.cndata.minishop.domain.GoodsExample;
import com.cndata.minishop.service.GoodsService;
import com.github.pagehelper.PageInfo;

@RestController
public class GoodsController {

	@Autowired
	private GoodsService goodsService;
	
	@RequestMapping(value="/api/auth/goods/add", method=RequestMethod.POST)
	public void add(@RequestBody Goods goods){
		goodsService.add(goods);
	}
	
	@RequestMapping(value="/api/auth/goods/delete/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable Integer id){
		goodsService.delete(id);
	}
	
	@RequestMapping(value="/api/auth/goods/update/{id}", method=RequestMethod.PUT)
	public void update(@PathVariable Integer id, @RequestBody Goods goods){
		goodsService.update(goods);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/goods/{id}", method=RequestMethod.GET)
	public Goods getById(@PathVariable Integer id){
		return goodsService.getById(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/goods/list", method=RequestMethod.GET)
	public List<Goods> getByList(Goods goods, String searchKey){
		GoodsExample example = new GoodsExample();
		if(searchKey!=null && !"".equals(searchKey)){
			GoodsExample.Criteria criteria = example.createCriteria();
			criteria.andNameLike("%"+searchKey+"%");
		}
		return goodsService.getByList(example);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/goods/page/{pageNum}/{pageSize}", method=RequestMethod.GET)
	public PageInfo<Goods> getByPage(Goods goods, @PathVariable Integer pageNum, @PathVariable Integer pageSize, String searchKey){
		GoodsExample example = new GoodsExample();
		if(searchKey!=null && !"".equals(searchKey)){
			GoodsExample.Criteria criteria = example.createCriteria();
			criteria.andNameLike("%"+searchKey+"%");
		}
		return goodsService.getByPage(example, pageNum, pageSize);
	}
}
