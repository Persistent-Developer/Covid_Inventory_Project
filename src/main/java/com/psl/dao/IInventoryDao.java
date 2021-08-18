package com.psl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.psl.entity.Inventory;
import com.sun.istack.NotNull;

public interface IInventoryDao extends CrudRepository<Inventory, Integer>{
	
	
	@Query(value="select distinct i.category from inventory i join store s on i.fk5_storeid=s.id where s.id=?1",nativeQuery = true)
	public List<String> findAllbyID(int id);
	
	/*
	 * @Query(value="select * from inventory i where i.product_name=?1",nativeQuery
	 * = true) public List<Inventory> findAllbyStoreID(String name);
	 */
	
	@Query(value="select * from inventory where category=?1",nativeQuery = true)
	List<Inventory> findByCategory(String name);
	
	@Query(value="select * from inventory where product_group=?1",nativeQuery = true)
	List<Inventory> findByGroup(String name);

	@Query(value="select i.* from inventory i join store s on i.fk5_storeid=s.id where i.product_code=?1",nativeQuery = true)
	public Inventory findByProduct_code(String product_code);
	
	@Query(value="select * from inventory i where i.product_code=?1",nativeQuery = true)
	public Inventory findByProduct_code1(String product_code);

	
	
	@Query(value="select * from inventory i join store s on i.fk5_storeid=s.id where i.product_name=?1 and i.category=?2 and i.product_group=?3 and s.id=?4",nativeQuery=true)
	public List<Inventory> findByMultipleValues1(String name,String category,String group,int id);
	
	@Query(value="select * from inventory i join store s on i.fk5_storeid=s.id where i.product_name=?1 and i.product_group=?2 and s.id=?3",nativeQuery=true)
	public List<Inventory> findByMultipleValues2(String name,String group,int id);
	
	@Query(value="select * from inventory i join store s on i.fk5_storeid=s.id where i.product_name=?1 and i.category=?2 and s.id=?3",nativeQuery=true)
	public List<Inventory> findByMultipleValues3(String name,String category,int id);
	
	@Query(value="select * from inventory i join store s on i.fk5_storeid=s.id where i.product_name=?1 and s.id=?2",nativeQuery=true)
	public List<Inventory> findByMultipleValues4(String name,int id);
	
	@Query(value="select * from inventory",nativeQuery = true)
	public List<Inventory> getAllProducts();
	
	@Query(value="select i.product_group from inventory i join store s on i.fk5_storeid=s.id where s.id=?1",nativeQuery = true)
	public List<String> findAllGroups(int id);	

}
