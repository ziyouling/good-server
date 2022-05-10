package com.ziyouling.good.goodserver.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.ziyouling.good.goodserver.vo.entity.BusinessTag;

public interface BusinessTagRepository extends CrudRepository<BusinessTag, Long> {
	Page<BusinessTag> findAll(Pageable pageable);
	
	Page<BusinessTag> findAllByNameStartsWith(String name, Pageable pageable);
	
	BusinessTag findByName(String name);
	
}
