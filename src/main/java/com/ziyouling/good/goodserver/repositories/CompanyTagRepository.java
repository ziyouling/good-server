package com.ziyouling.good.goodserver.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ziyouling.good.goodserver.vo.entity.Company;
import com.ziyouling.good.goodserver.vo.entity.CompanyTag;

public interface CompanyTagRepository extends CrudRepository<CompanyTag, Long> {
	long  deleteByCompany(Company company);
}
