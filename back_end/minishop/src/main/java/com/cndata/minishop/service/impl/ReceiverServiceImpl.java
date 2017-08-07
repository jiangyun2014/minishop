package com.cndata.minishop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cndata.minishop.domain.Receiver;
import com.cndata.minishop.domain.ReceiverExample;
import com.cndata.minishop.mapper.ReceiverMapper;
import com.cndata.minishop.service.ReceiverService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("receiverService")
public class ReceiverServiceImpl implements ReceiverService {

	@Autowired
	private ReceiverMapper receiverMapper;
	
	@Override
	public void add(Receiver receiver) {
		receiverMapper.insert(receiver);
	}

	@Override
	public void delete(Integer id) {
		receiverMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void update(Receiver receiver) {
		receiverMapper.updateByPrimaryKeySelective(receiver);
	}

	@Override
	public Receiver getById(Integer id) {
		return receiverMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Receiver> getByList(ReceiverExample example) {
		return receiverMapper.selectByExample(example);
	}

	@Override
	public PageInfo<Receiver> getByPage(ReceiverExample example, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<Receiver>(receiverMapper.selectByExample(example));
	}
}
