package com.ll.demo.service.impl;

import com.ll.demo.entity.Case;
import com.ll.demo.repository.CaseRepository;
import com.ll.demo.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CaseServiceImpl implements CaseService {

    @Autowired
    private CaseRepository caseRepository ;

    @Override
    public List<Case> findFirst10() {
        return caseRepository.findAll() ;
    }
}
