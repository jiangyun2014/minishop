package com.cndata.minishop.service;

import java.util.List;

import com.cndata.minishop.domain.Receiver;
import com.cndata.minishop.domain.ReceiverExample;
import com.github.pagehelper.PageInfo;

public interface ReceiverService extends BaseService<Receiver> {

	public List<Receiver> getByList(ReceiverExample example);
	
	public PageInfo<Receiver> getByPage(ReceiverExample example, Integer pageNum, Integer pageSize);
}
