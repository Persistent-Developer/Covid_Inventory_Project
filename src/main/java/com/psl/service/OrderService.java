package com.psl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.dao.IOrderDao;
import com.psl.entity.Orders;

@Service("orderService")
public class OrderService {
	
	@Autowired
	public IOrderDao dao;

	public void placeOrder(Orders o) {
		dao.save(o);
	}
}
