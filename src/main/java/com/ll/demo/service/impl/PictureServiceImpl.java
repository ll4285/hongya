package com.ll.demo.service.impl;

import com.ll.demo.entity.Picture;
import com.ll.demo.repository.PictureRepository;
import com.ll.demo.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureRepository repository ;

    @Override
    public List<Picture> findHomeCarousel(Integer flag) {
        return repository.findByFlagIsOrderByCreateTime(flag);
    }
}
