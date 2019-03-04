### Cache

**dependency 추가**
```xml
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-cache</artifactId>
        </dependency>

        <dependency>
            <groupId>net.sf.ehcache</groupId>
            <artifactId>ehcache</artifactId>
        </dependency>
```

&nbsp;

**Config 추가**
```java
@Configuration
@EnableCaching
public class CacheConfig {
}
```

&nbsp;

**classpath:ehcache.xml**
```xml
<?xml version="1.0" encoding="UTF-8"?>
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:noNamespaceSchemaLocation="http://www.ehcache.org/ehcache.xsd"
         updateCheck="true"
         monitoring="autodetect"
         dynamicConfig="true">

    <cache name="users"
           maxElementsInMemory="1000"
           eternal="true"
           overflowToDisk="false"
           timeToLiveSeconds="3"
           timeToIdleSeconds="0"
           memoryStoreEvictionPolicy="LFU"
           transactionalMode="off">
    </cache>

    <cache name="user"
           maxElementsInMemory="2"
           eternal="true"
           overflowToDisk="false"
           timeToLiveSeconds="300"
           timeToIdleSeconds="0"
           memoryStoreEvictionPolicy="LRU"
           transactionalMode="off">
    </cache>

</ehcache>
```

&nbsp;

**service단 코드추가**
```java
    @Cacheable(cacheNames="users")
    public List<User> getUsers() throws InterruptedException {
        Thread.sleep(3000);
        return userRepository.findAll();
    }

    @Cacheable(cacheNames="user", key="#id")
    public User getUser(String id) throws NullUserException, InterruptedException {
        Thread.sleep(3000);
        return userRepository.findById(id).orElseThrow(NullUserException::new);
    }
```

&nbsp;
&nbsp;

### 참고

[Spring Boot Cache Sample - github](https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-cache)

[SpringBoot + Ehcache 기본 예제 및 소개 - jojoldu 블로그](https://jojoldu.tistory.com/57)
