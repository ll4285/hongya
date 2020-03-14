package com.ll.demo.repository;

import com.ll.demo.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture,Integer> {
    public List<Picture> findByFlagIsOrderByCreateTime(Integer flag) ;
}
