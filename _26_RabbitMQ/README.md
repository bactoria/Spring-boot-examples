### Dependency

- RabbitMQ 3.7.13
- Erlang 21.3
- Java 8
- Spring Boot 2.1.3 (amqp, lombok)

&nbsp;

[RabbitMQ 설치 & 모니터링 활설화](http://blog.naver.com/PostView.nhn?blogId=willygwu2003&logNo=130171891352)

**(docker)**
```
docker run -d -p 5672:5672 -p 15672:15672 rabbitmq:3.7.13-management
```

(모니터링은 http://localhost:15672 로 접속해야 함.)

&nbsp;
&nbsp;

### 1. Queue 생성

`springMQ1` 큐 생성

![image](https://user-images.githubusercontent.com/25674959/54711891-45343780-4b8e-11e9-8f3a-0c8cfe7c7a63.png)

&nbsp;
&nbsp;

### 2. ProviderApplication 실행

큐로 메시지를 보내는 역할. 

메시지는 직렬화로 보냈음. 가끔 에러가 뜨던데 직렬화 때문인지 모르겠음.

Json으로 바꿔서 보내는 것이 좋아보이는데.. 하다가 잘 안되서 일단 보류

&nbsp;

#### 확인

![image](https://user-images.githubusercontent.com/25674959/54712415-8b3dcb00-4b8f-11e9-9abe-f5f915bbad01.png)

큐에 메시지가 엄청 쌓였다.

&nbsp;
&nbsp;

### 3. ReceiverApplication 실행

큐에 쌓여있는 메시지를 받아온다.

큐가 비어있어도 큐에 메시지 쌓이기를 계속 기다린다.

&nbsp;

#### 확인

![image](https://user-images.githubusercontent.com/25674959/54712609-128b3e80-4b90-11e9-82cf-f884933fa618.png)


&nbsp;
&nbsp;

### Todo

1. Receiver에서 메시징 처리 도중 에러났을 때의 복원
2. Message Json 변환

&nbsp;
&nbsp;

## 참고

- [Spring Boot - RabbitMQ (by heowc)](https://heowc.tistory.com/36?category=677973)

- [Github :: SpringBootRabbitMQ (by heowc)](https://github.com/heowc/SpringBootSample/tree/master/SpringBootRabbitMQ)

