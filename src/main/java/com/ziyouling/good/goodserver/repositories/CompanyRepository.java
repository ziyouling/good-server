package com.ziyouling.good.goodserver.repositories;

import org.springframework.data.repository.CrudRepository;

import com.ziyouling.good.goodserver.vo.entity.Company;

public interface CompanyRepository extends CrudRepository<Company, Long> {
	Company findByNickname(String nickName);
}
