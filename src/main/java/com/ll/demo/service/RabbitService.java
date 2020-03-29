package com.ll.demo.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class RabbitService {
    //@RabbitListener(queues = {"atguigu.emps","atguigu","atguigu.news","gulixueyuan.news"})
    public void listener(String message){
        System.out.println(message);
    }
}
