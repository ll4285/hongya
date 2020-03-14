package com.ll.demo.controller;

import com.ll.demo.entity.Case;
import com.ll.demo.entity.Picture;
import com.ll.demo.service.CaseService;
import com.ll.demo.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private PictureService pictureService ;
    @Autowired
    private CaseService caseService ;
    @RequestMapping("/")
    public String homePage(Model model){
        List<Picture> homeCarousel = pictureService.findHomeCarousel(1);//首页轮播图模块
        List<Case> homeCase = caseService.findFirst10() ;//首页案例模块
        model.addAttribute("homeCarousel",homeCarousel) ;
        model.addAttribute("homeCase",homeCase) ;

        return "index" ;
    }
}
