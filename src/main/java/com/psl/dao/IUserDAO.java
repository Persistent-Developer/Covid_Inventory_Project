package com.psl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.psl.entity.User;

public interface IUserDAO extends CrudRepository<User, Integer> {

	@Query(value="select * from user u where u.ph_number=?1",nativeQuery = true)
	public User findByPhNumber(String ph_number);
}
