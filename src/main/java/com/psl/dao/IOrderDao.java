package com.psl.dao;

import org.springframework.data.repository.CrudRepository;

import com.psl.entity.Orders;

public interface IOrderDao extends CrudRepository<Orders, Integer> {
	
	
}
