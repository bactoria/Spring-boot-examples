**RabbitMQ Topics Exchange example (using Spring Boot)**

### Dependency

- Java 8
- Spring Boot 2.1.3 (starter-amqp, starter-json, lombok)
- Erlang 21.3
- RabbitMQ 3.7.13

&nbsp;
&nbsp;

## RabbitMQ 설치

[RabbitMQ 설치 & 모니터링 활성화](http://blog.naver.com/PostView.nhn?blogId=willygwu2003&logNo=130171891352)

**(docker 사용 시)**
```
docker run -d -p 5672:5672 -p 15672:15672 rabbitmq:3.7.13-management
```

(모니터링은 http://localhost:15672 로 접속해야 함.)

---

![Spring-RabbitMq-Send-Java-Objects-architecture](https://user-images.githubusercontent.com/25674959/54738702-a7298700-4bf8-11e9-97db-c56ea02172d3.png)

&nbsp;
&nbsp;

## Provider

메시지를 큐에 보내는 역할.

&nbsp;

### Queue's Durability
```java
    final boolean DURABLE = true;
    channel.queueDeclare(constant.QUEUE_NAME, DURABLE, false, false, null);
```

`RabbitMQ Server` 가 재실행되어도 Queue는 영구성을 가짐.

&nbsp;

### Message's Durability
```java
    channel.basicPublish("",constant.QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
```

Queue 안의 Message까지 영구성을 가지기 위해서는 위처럼 **PERSISTENT** 설정을 추가해야 함.

&nbsp;

### Publisher Confirm

`Publisher`가 `Message`를 보냈지만, 제대로 보내졌는지 확인할 수 있는 방법이 있다.

`channel`에 `ConfirmListener`를 추가하면 된다.

[Publisher Confirm 코드](./src/main/java/com/example/RabbitMQ/_examples/_2_publisher_confirm_Publisher.java)

&nbsp;

### Serialize (Default)

`RabbitTemplate` 를 이용하여 Message 객체를 보낼 수 있는데, `RabbitTemplate.class` 를 확인해보면 `MessageConverter` 에 기본적으로 **Serialize**를 사용하는 것을 알 수 있다. 따라서 Serialize를 사용하기 위해서는 Message.class 에 `Serializable 인터페이스`만 추가하면 된다.

&nbsp;

### Convert to  Json

Message 객체를 **Serialize**가 아닌, **JSON**로 보내고 싶다면 `RabbitTemplate`에 `Jackson2JsonMessageConverter` 를 주입한다. 관련 Config는 [RabbitMQConfig](./src/main/java/com/example/RabbitMQ/provider_topic/config/RabbitMQConfig.java)에서 볼 수 있다.

`Jackson2JsonMessageConverter` 가 `ObjectMapper`를 필요로 하기 때문에 `spring-boot-starter-json` 의존성을 추가해야 한다. 

`Jackson2JsonMessageConverter` 는 `spring-boot-starter-amqp` 에서 가져온 것인데, `spring-boot-starter-amqp` 에 json 관련 의존성이 없다.

보니까 Spring Boot 1.x.x 에서는 `spring-boot-starter-amqp`에 `jackson-databind`가 들어가있던데...

Converter들은 `org.springframework.amqp.support.converter` 에서 확인 가능하다.

&nbsp;

위가 Provider 이야기였고, Receiver에서는 `MessageConverter`를 `Jackson2JsonMessageConverter` 로 구현하면 된다. 관련 Config는 [RabbitMQConfig](./src/main/java/com/example/RabbitMQ/receiver_topic/config/RabbitMQConfig.java)에서 볼 수 있다. 

&nbsp;
&nbsp;

## Receiver

큐에 쌓여있는 메시지를 받아온다.

![image](https://user-images.githubusercontent.com/25674959/54712609-128b3e80-4b90-11e9-82cf-f884933fa618.png)

&nbsp;

### prefetchCount

```
channel.basicQos(1);
```

Queue에서 1개씩 가져옴.

순차적인 처리가 중요한 상황에서는 1로 써야함.

&nbsp;

### AutoAck

```java
channel.basicConsume(constant.QUEUE_NAME, true, consumer); // AutoAck = true
```

`AutoAck = true` 를 하게 되면 메시지 처리하다가 에러떠도 무시한다. 즉, 메시지가 분실된다.

&nbsp;


```java
channel.basicAck(envelope.getDeliveryTag(), true);
```

true : unacked 에서 Ready로 가네...

true : `delivery tag` 를 포함한 모든 메시지 확인 (Ready + Unacked 메시지)
false : `delivery tag` 만 확인 (Ready 메시지)

`delivary tag` 에 대해서 아직 잘 모르겠음

&nbsp;
&nbsp;

## Exchange

각 `Message`를 적재적소의 `Queue`에 들어가도록 라우팅 해줌.

각 `Message의 RoutingKey` 값을 토대로 `Exchange - Queue의 BindingKey`을 확인.

### Exchange Type

라우팅 기법에는 여러가지가 있음.

- direct
- fanout
- topic
- header
- ...

&nbsp;

#### 1. direct

![image](https://user-images.githubusercontent.com/25674959/54743500-9d5b5000-4c07-11e9-9054-27700f9f762a.png)
(https://www.aegissofttech.com/articles/difference-between-amqp-concepts-and-jms-api-rabbitmq-installation-guide.html)

Message를 Exchanges에 보내고, Message의 RoutingKey와 동일한 BindingKey를 가진 Queue로 라우팅

(1:1)

&nbsp;
&nbsp;

#### 2. fanout

![image](https://user-images.githubusercontent.com/25674959/54743510-a77d4e80-4c07-11e9-8536-f3409095a070.png)
(https://www.aegissofttech.com/articles/difference-between-amqp-concepts-and-jms-api-rabbitmq-installation-guide.html)

Message를 Exchanges에 보내고, 해당 Exchange에 바인딩된 모든 Queue로 라우팅.

(브로드캐스팅)

&nbsp;
&nbsp;

#### 3. topic

![image](https://user-images.githubusercontent.com/25674959/54743554-c2e85980-4c07-11e9-93ba-a2105ea91ac8.png)

패턴에 맞는 Queue로 들어감.

Message1(routingKey : apple.orange.rabbit) -> Q1, Q2  
Message2(routingKey : apple.orange.red) -> Q1    
Message3(routingKey : lazy) -> Q2  
Message4(routingKey : lazy.a.b.c.d) -> Q2  
Message5(routingKey : lazy.a.labbit) -> Q2 (Message 1개가 Q2로 2번 들어가지는 않음.)
Message6(routingKey : apple.banana.car) -> X  

&nbsp;

Exchange의 모든 routingKey가 **#** 라면? `fanout`과 동일

Exchange의 모든 routingKey에 **\***, **#** 가 한개도 없다면? -> `direct`와 동일.

&nbsp;
&nbsp;

#### exchange 없이 큐로 직접 Send

![image](https://user-images.githubusercontent.com/25674959/54798332-9a11a400-4c9b-11e9-8b64-30fba1e0d627.png)

RabbitMQ 튜토리얼을 시작하면 다음 그림을 먼저 볼 수 있다.

Exchange가 없이 큐로 보낸 것 같지만, 실제로 메시지들은 direct 방식인 `AMQP default` 로 보내지고, Routing Key에 해당하는 Queue로 들어가게 된다.

&nbsp;
&nbsp;


## 참고

- [RabbitMQ 이해하기](https://github.com/gjchoi/gjchoi.github.io/blob/master/_posts/2016-02-27-rabbit-mq-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0.md)  
- [Spring Boot - RabbitMQ (by heowc)](https://heowc.tistory.com/36?category=677973)  
- [Github :: SpringBootRabbitMQ (by heowc)](https://github.com/heowc/SpringBootSample/tree/master/SpringBootRabbitMQ)
- [Github :: RabbitmqExample (by lilei644)](https://github.com/lilei644/spring-java-example/tree/master/JavaRabbitmqExample/src/main/java)
