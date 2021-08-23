
package com.psl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.dao.IOrderDao;
import com.psl.entity.Orders;

@Service("orderService")
public class OrderService {
	
	@Autowired
	public IOrderDao dao;

	public Orders placeOrder(Orders o) throws Exception {
		return dao.save(o);
	}
	
	public List<Orders> getAllOrders() throws Exception {
		return dao.getAllOrders();
	}
}

