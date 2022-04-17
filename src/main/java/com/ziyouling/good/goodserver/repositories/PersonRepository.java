package com.ziyouling.good.goodserver.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ziyouling.good.goodserver.vo.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
	Person findByName(String name);
}
