**RabbitMQ Topics Exchange example (using Spring Boot)**

### Dependency

- Java 8
- Spring Boot 2.1.3 (starter-amqp, starter-json, lombok)
- Erlang 21.3
- RabbitMQ 3.7.13

&nbsp;

#### Object -> Serialize (Default)

`RabbitTemplate` 를 이용하여 Message 객체를 보낼 수 있는데, `RabbitTemplate.class` 를 확인해보면 `MessageConverter` 에 기본적으로 **Serialize**를 사용하는 것을 알 수 있다. 따라서 Serialize를 사용하기 위해서는 Message.class 에 `Serializable 인터페이스`만 추가하면 된다.

&nbsp;

#### Object -> Json

Message 객체를 **Serialize**가 아닌, **JSON**로 보내고 싶다면 `RabbitTemplate`에 `Jackson2JsonMessageConverter` 를 주입한다. 관련 Config는 [RabbitMQConfig](./src/main/java/com/example/RabbitMQ/provider_topic/config/RabbitMQConfig.java)에서 볼 수 있다.

`Jackson2JsonMessageConverter` 가 `ObjectMapper`를 필요로 하기 때문에 `spring-boot-starter-json` 의존성을 추가해야 한다. 

`Jackson2JsonMessageConverter` 는 `spring-boot-starter-amqp` 에서 가져온 것인데, `spring-boot-starter-amqp` 에 json 관련 의존성이 없다.

보니까 Spring Boot 1.x.x 에서는 `spring-boot-starter-amqp`에 `jackson-databind`가 들어가있던데...

Converter들은 `org.springframework.amqp.support.converter` 에서 확인 가능하다.

&nbsp;

위가 Provider 이야기였고, Receiver에서는 `MessageConverter`를 `Jackson2JsonMessageConverter` 로 구현하면 된다. 관련 Config는 [RabbitMQConfig](./src/main/java/com/example/RabbitMQ/receiver_topic/config/RabbitMQConfig.java)에서 볼 수 있다. 

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

## Exchange

### Exchange Type

- direct
- fanout
- topic
- header
- ...

&nbsp;

#### 1. direct

![image](https://user-images.githubusercontent.com/25674959/54743500-9d5b5000-4c07-11e9-9054-27700f9f762a.png)
(https://www.aegissofttech.com/articles/difference-between-amqp-concepts-and-jms-api-rabbitmq-installation-guide.html)

해당 Exchange와 Queue는 RoutingKey 값으로 1:1 메시지 라우팅.

&nbsp;
&nbsp;

#### 2. fanout

![image](https://user-images.githubusercontent.com/25674959/54743510-a77d4e80-4c07-11e9-8536-f3409095a070.png)
(https://www.aegissofttech.com/articles/difference-between-amqp-concepts-and-jms-api-rabbitmq-installation-guide.html)

브로드캐스팅 방식.

해당 Exchange에 바인딩된 모든 Queue로 메시지 라우팅.

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
 
### 1. Provider

큐로 메시지를 보내는 역할. 

메시지는 직렬화로 보냈음. 가끔 에러가 뜨던데 직렬화 때문인지 모르겠음.

Json으로 바꿔서 보내는 것이 좋아보이는데.. 하다가 잘 안되서 일단 보류

&nbsp;

#### 확인

![image](https://user-images.githubusercontent.com/25674959/54712415-8b3dcb00-4b8f-11e9-9abe-f5f915bbad01.png)

큐에 메시지가 엄청 쌓였다.

&nbsp;
&nbsp;

### 2. Receiver

큐에 쌓여있는 메시지를 받아온다.

큐가 비어있어도 큐에 메시지 쌓이기를 계속 기다린다.

&nbsp;

#### 확인

![image](https://user-images.githubusercontent.com/25674959/54712609-128b3e80-4b90-11e9-82cf-f884933fa618.png)

&nbsp;
&nbsp;

## 참고

- [RabbitMQ 이해하기](https://github.com/gjchoi/gjchoi.github.io/blob/master/_posts/2016-02-27-rabbit-mq-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0.md)  
- [Spring Boot - RabbitMQ (by heowc)](https://heowc.tistory.com/36?category=677973)  
- [Github :: SpringBootRabbitMQ (by heowc)](https://github.com/heowc/SpringBootSample/tree/master/SpringBootRabbitMQ)

