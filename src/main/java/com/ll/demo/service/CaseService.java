package com.ll.demo.service;

import com.ll.demo.entity.Case;

import java.util.List;

public interface CaseService {
    List<Case> findFirst10();
}
