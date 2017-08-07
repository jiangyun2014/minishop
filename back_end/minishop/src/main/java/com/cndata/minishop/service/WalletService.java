package com.cndata.minishop.service;

import java.util.List;

import com.cndata.minishop.domain.Wallet;
import com.cndata.minishop.domain.WalletExample;
import com.github.pagehelper.PageInfo;

public interface WalletService extends BaseService<Wallet> {

	public List<Wallet> getByList(WalletExample example);
	
	public PageInfo<Wallet> getByPage(WalletExample example, Integer pageNum, Integer pageSize);
}
