package com.ll.demo.service.impl;

import com.ll.demo.entity.Case;
import com.ll.demo.repository.CaseRepository;
import com.ll.demo.service.CaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig
@Slf4j
public class CaseServiceImpl implements CaseService {
    private final String CASE = "CASE";
    @Autowired
    private CaseRepository caseRepository ;

    @Override
    public List<Case> findFirst10() {
        return caseRepository.findAll() ;
    }

    @Override
    @Cacheable(value = CASE,key = "#id")
    public Case findById(Integer id){
        log.info("从数据库查询！");
        return caseRepository.findById(id).orElseGet(Case::new);
    }
}
