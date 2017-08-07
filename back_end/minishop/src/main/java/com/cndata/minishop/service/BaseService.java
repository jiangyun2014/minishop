package com.cndata.minishop.service;

public interface BaseService<T> {

	public void add(T t);
	
	public void delete(Integer id);
	
	public void update(T t);
	
	public T getById(Integer id);
}
