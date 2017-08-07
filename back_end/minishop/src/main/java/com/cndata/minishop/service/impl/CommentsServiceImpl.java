package com.cndata.minishop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cndata.minishop.domain.Comments;
import com.cndata.minishop.domain.CommentsExample;
import com.cndata.minishop.mapper.CommentsMapper;
import com.cndata.minishop.service.CommentsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("commentsService")
public class CommentsServiceImpl implements CommentsService {

	@Autowired
	private CommentsMapper commentsMapper;
	
	@Override
	public void add(Comments comments) {
		commentsMapper.insert(comments);
	}

	@Override
	public void delete(Integer id) {
		commentsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void update(Comments comments) {
		commentsMapper.updateByPrimaryKeySelective(comments);
	}

	@Override
	public Comments getById(Integer id) {
		return commentsMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Comments> getByList(CommentsExample example) {
		return commentsMapper.selectByExample(example);
	}

	@Override
	public PageInfo<Comments> getByPage(CommentsExample example, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<Comments>(commentsMapper.selectByExample(example));
	}
}
