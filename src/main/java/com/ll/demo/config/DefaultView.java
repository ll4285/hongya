package com.ll.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class DefaultView implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //registry.addViewController("/").setViewName("index");
        registry.addViewController("/design").setViewName("design");
        registry.addViewController("/youshi").setViewName("youshi");
        registry.addViewController("/case").setViewName("case");
        registry.addViewController("/team").setViewName("team");
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/news").setViewName("news");
        registry.addViewController("/contact").setViewName("contact");
    }
}
