package com.ll.demo.service;

import com.ll.demo.entity.Picture;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PictureService {
    List<Picture> findHomeCarousel(Integer flag) ;
}
