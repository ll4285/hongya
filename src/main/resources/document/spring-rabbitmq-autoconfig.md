## 1 介绍

RabbitMQ是一个由erlang开发的AMQP(Advanved Message Queue Protocol)的开源实现。

### 核心概念

**Message**
消息，消息是不具名的，它由消息头和消息体组成。消息体是不透明的，而消息头则由一系列的可选属性组成，这些属性包括routing-key（路由键）、priority（相对于其他消息的优先权）、delivery-mode（指出该消息可能需要持久性存储）等。

**Publisher**
消息的生产者，也是一个向交换器发布消息的客户端应用程序。

**Exchange**
交换器，用来接收生产者发送的消息并将这些消息路由给服务器中的队列。
**Exchange**有4种类型：**direct**(默认)，**fanout**, **topic**, 和**headers**，不同类型的Exchange转发消息的策略有所区别。

**Queue**
消息队列，用来保存消息直到发送给消费者。它是消息的容器，也是消息的终点。一个消息可投入一个或多个队列。消息一直在队列里面，等待消费者连接到这个队列将其取走。

**Binding**
绑定，用于消息队列和交换器之间的关联。一个绑定就是基于路由键将交换器和消息队列连接起来的路由规则，所以可以将交换器理解成一个由绑定构成的路由表。
Exchange 和Queue的绑定可以是多对多的关系。

**Connection**
网络连接，比如一个TCP连接。

Channel
信道，多路复用连接中的一条独立的双向数据流通道。信道是建立在真实的TCP连接内的虚拟连接，AMQP 命令都是通过信道发出去的，不管是发布消息、订阅队列还是接收消息，这些动作都是通过信道完成。因为对于操作系统来说建立和销毁 TCP 都是非常昂贵的开销，所以引入了信道的概念，以复用一条 TCP 连接。

**Consumer**
消息的消费者，表示一个从消息队列中取得消息的客户端应用程序。

**Virtual** **Host**
虚拟主机，表示一批交换器、消息队列和相关对象。虚拟主机是共享相同的身份认证和加密环境的独立服务器域。每个 vhost 本质上就是一个 mini 版的 RabbitMQ 服务器，拥有自己的队列、交换器、绑定和权限机制。vhost 是 AMQP 概念的基础，必须在连接时指定，RabbitMQ 默认的 vhost 是 / 。

**Broker**
表示消息队列服务器实体。

## 2 自动配置

1、RabbitAutoConfiguration

2、有自动配置了连接工厂 ConnectionFactory ;

3、RabbitProperties 封装了 RabbitMQ的配置项 ；

4、RabbitTemplate : 给RabbitMQ发送和接收消息 ；

5、AmqpAdmin : RabbitMQ系统管理功能组件 ；

​		|__:AmqpAdmin 可以添加或删除 Exchange, queue, binding

6、@EnableRabbit + @RabbitListener 监听消息队列的内容。

## 3 使用

### 一、引入pom依赖

~~~xml
<dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
~~~

### 二、添加配置

~~~properties
#rabbitMq-config
spring.rabbitmq.host=47.98.164.201
spring.rabbitmq.port=5672
spring.activemq.user=guest
spring.activemq.password=guest
spring.rabbitmq.virtual-host=/
~~~

### 三、配置类(添加MessageConverter 消息序列化配置)

~~~java
@Configuration
public class AMQPConfig {

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
~~~

### 四、具体使用

发送消息

~~~java
/**
* 参数一：Exchange-交换器，用来接收生产者发送的消息并将这些消息路由给服务器中的队列。
* 参数二：routingKey-路由key，基于路由键将交换器和消息队列连接起来。
* 参数三：Message-需要发送的消息。
*/
rabbitTemplate.convertAndSend("exchange.direct","atguigu.news",message);
~~~

接收消息

~~~java
/**
* queueName : 消息队列名称
*/
Object o = rabbitTemplate.receiveAndConvert("atguigu.news");
~~~

监听场景使用

~~~java
/**
*	在启动类上添加@EnableRabbit注解，开启基于注解的Rabbitmq
*/
@EnableRabbit
/* ... 此处启动类忽略不写 ... */

/**
* 使用@RabbitListener注解来监听消息队列
* queues可以写多个消息队列
* 参数可以是消息队列返回的message的类型
* 	|__:参数可以是Message 消息；
*   |__:message.getBody() 消息体，message.getMessageProperties() 消息头信息。
*/
@RabbitListener(queues="atguigu.news")
public void receive(Book book){
  System.out.println("收到消息:"+book);
}
~~~

### AmqpAdmin 管理组件

创建Exchange，Queue，Binding 

~~~java
@Autowired
AmqpAdmin amqpAdmin ;

/**
* 创建交换器
* 方法declareExchange()的参数为Exchange接口类型 该接口有5个实现类，
*	分别为direct，fanout, topic, headers 和 自定义 类型。
*
* 方法declareQueue()的参数为Queue类型 
* 类Queue构造函数的参数列表：队列的名称，是否持久化。
*/
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
~~~

删除Exchange，Queue，Binding 

~~~java
public void delete(){
  amqpAdmin.deleteExchange("amqpAdmin.exchange");
  amqpAdmin.deleteQueue("amqpAdmin.queue");
   amqpAdmin.removeBinding(new Binding("amqpAdmin.queue",Binding.DestinationType.QUEUE,
                                       "amqpAdmin.exchange","amqp.haha",null));
}
~~~

