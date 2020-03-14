package com.ll.demo.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ll.demo.entity.Case;
import com.ll.demo.entity.Picture;
import com.ll.demo.service.CaseService;
import com.ll.demo.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeRestController {

    @Autowired
    private PictureService pictureService ;

    @Autowired
    private CaseService caseService ;

    @GetMapping("/home/picture/carousel/{flag}")
    public List<Picture> findHomeCarousel(@PathVariable Integer flag){
        return pictureService.findHomeCarousel(flag);
    }

    @GetMapping("/home/picture/case")
    public List<Case> findHomecase(){
        return caseService.findFirst10() ;
    }

}

