package com.ll.demo.repository;

import com.ll.demo.entity.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface CaseRepository extends JpaRepository<Case,Integer> {
    List<Case> findAll() ;
}
