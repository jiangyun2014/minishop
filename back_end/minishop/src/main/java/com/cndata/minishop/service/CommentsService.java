package com.cndata.minishop.service;

import java.util.List;

import com.cndata.minishop.domain.Comments;
import com.cndata.minishop.domain.CommentsExample;
import com.github.pagehelper.PageInfo;

public interface CommentsService extends BaseService<Comments> {

	public List<Comments> getByList(CommentsExample example);
	
	public PageInfo<Comments> getByPage(CommentsExample example, Integer pageNum, Integer pageSize);
}
