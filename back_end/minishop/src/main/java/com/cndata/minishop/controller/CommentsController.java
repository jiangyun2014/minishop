package com.cndata.minishop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cndata.minishop.domain.Comments;
import com.cndata.minishop.domain.CommentsExample;
import com.cndata.minishop.service.CommentsService;
import com.github.pagehelper.PageInfo;

@RestController
public class CommentsController {

	@Autowired
	private CommentsService commentsService;
	
	@RequestMapping(value="/api/auth/comments/add", method=RequestMethod.POST)
	public void add(@RequestBody Comments comments){
		commentsService.add(comments);
	}
	
	@RequestMapping(value="/api/auth/comments/delete/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable Integer id){
		commentsService.delete(id);
	}
	
	@RequestMapping(value="/api/auth/comments/update/{id}", method=RequestMethod.PUT)
	public void update(@PathVariable Integer id, @RequestBody Comments comments){
		commentsService.update(comments);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/comments/{id}", method=RequestMethod.GET)
	public Comments getById(@PathVariable Integer id){
		return commentsService.getById(id);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/comments/list", method=RequestMethod.GET)
	public List<Comments> getByList(Comments comments){
		CommentsExample example = new CommentsExample();
		example.setOrderByClause("pubtime desc");
		
		Integer goodsId = comments.getGoodsid();
		if(goodsId!=null){
			CommentsExample.Criteria criteria = example.createCriteria();
			criteria.andGoodsidEqualTo(goodsId);
		}
		return commentsService.getByList(example);
	}
	
	@ResponseBody
	@RequestMapping(value="/api/comments/page/{pageNum}/{pageSize}", method=RequestMethod.GET)
	public PageInfo<Comments> getByPage(Comments comments, @PathVariable Integer pageNum, @PathVariable Integer pageSize){
		CommentsExample example = new CommentsExample();
		example.setOrderByClause("pubtime desc");
		
		Integer goodsId = comments.getGoodsid();
		if(goodsId!=null){
			CommentsExample.Criteria criteria = example.createCriteria();
			criteria.andGoodsidEqualTo(goodsId);
		}
		return commentsService.getByPage(example, pageNum, pageSize);
	}
}
