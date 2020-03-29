package com.ll.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate ;

    @Autowired
    AmqpAdmin amqpAdmin ;

    /**
     * rabbitmq 发送 点对点消息 测试
     */
    @Test
    public void contextLoads() {
        Map<String,Object> map = new HashMap<>();
        map.put("keys","这是第一个消息！") ;
        map.put("data", Arrays.asList("helloword",123,true)) ;
        rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",map);
    }

    /**
     * rabbitmq 接收 点对点消息 测试
     */
    @Test
    public void test1(){
        Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    @Test
    public void test2(){
        String message = "这是一个广播消息！" ;
        rabbitTemplate.convertAndSend("exchange.fanout","",message);
    }

    /**
     * 创建交换器
     * 方法declareExchange()的参数为Exchange接口类型 该接口有5个实现类，
     *	分别为direct，fanout, topic, headers 和 自定义 类型。
     *
     * 方法declareQueue()的参数为Queue类型
     * 类Queue构造函数的参数列表：队列的名称，是否持久化。
     */
    @Test
    public void create(){
        //创建交换器
        amqpAdmin.declareExchange(new DirectExchange("amqpAdmin.exchange"));
        //创建队列
        amqpAdmin.declareQueue(new Queue("amqpAdmin.queue",true));
        //创建绑定规则
        /* Binding()
         * destination:目的地
         * destinationType:目的地类型(QUEUE,EXCHANGE)队列和交换器
         * exchange:交换器名称
         * routingkey:路由键
         * arguments:参数头
         */
        amqpAdmin.declareBinding(new Binding("amqpAdmin.queue",Binding.DestinationType.QUEUE,
                "amqpAdmin.exchange","amqp.haha",null));

    }

}
