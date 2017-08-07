package com.cndata.minishop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cndata.minishop.domain.Wallet;
import com.cndata.minishop.domain.WalletExample;
import com.cndata.minishop.mapper.WalletMapper;
import com.cndata.minishop.service.WalletService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service("walletService")
public class WalletServiceImpl implements WalletService {

	@Autowired
	private WalletMapper walletMapper;
	
	@Override
	public void add(Wallet wallet) {
		walletMapper.insert(wallet);
	}

	@Override
	public void delete(Integer id) {
		walletMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void update(Wallet wallet) {
		walletMapper.updateByPrimaryKeySelective(wallet);
	}

	@Override
	public Wallet getById(Integer id) {
		return walletMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Wallet> getByList(WalletExample example) {
		return walletMapper.selectByExample(example);
	}

	@Override
	public PageInfo<Wallet> getByPage(WalletExample example, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		return new PageInfo<Wallet>(walletMapper.selectByExample(example));
	}
}
