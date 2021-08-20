package com.psl.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.psl.dao.IStoreDao;
import com.psl.entity.Store;

@Service
public class StoreService {
	
	@Autowired
	private IStoreDao dao;
	
	public Store addStore(Store store) throws Exception
	{
		return dao.save(store);
	}
	
	public Store findById(int id) throws Exception
	{
		return dao.findById(id).get();
	}
	
	public List<Store> getAllStore() throws Exception
	{
		List<Store> storelist=new ArrayList<>();
		Iterable<Store> iterablelist = dao.findAll();
		for(Store s: iterablelist)
		{
			storelist.add(s);
		}
		return storelist;
	}
}
