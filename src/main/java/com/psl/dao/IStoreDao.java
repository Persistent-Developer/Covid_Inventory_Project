package com.psl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.psl.entity.Store;

public interface IStoreDao extends CrudRepository<Store, Integer>{
	
	@Query(value="select name,is_active,no_of_timeslots,slot_duration from store where id=?1",nativeQuery = true)
	List<Object[]> getStore(int id);

	@Query(value="select s.slot_duration from store s where s.id=?1",nativeQuery = true)
	public int getStoreSlotDuration(int id);
	
	@Query(value="select s.no_of_timeslots from store s where s.id=?1",nativeQuery = true)
	public int getDeliveryPerSlot(int id);
	
}
