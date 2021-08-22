package com.psl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.psl.entity.Inventory;
import com.psl.entity.Orders;

public interface IOrderDao extends CrudRepository<Orders, Integer> {
	
	@Query(value="select * from orders",nativeQuery = true)
	public List<Orders> getAllOrders();
}
