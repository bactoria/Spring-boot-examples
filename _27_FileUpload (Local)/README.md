
파일 업로드 / 다운로드

로컬 스토리지에 저장

각 파일의 이름이 중복될 경우, 덮어씌움 (Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);)

&nbsp;

**파일 업로드 (단일)**
```java
POST http://localhost:8080/uploadFile

form-data

Body
    - key: file
    - value: 파일첨부
```

&nbsp;

**파일 업로드 (여러개)**
```java
POST http://localhost:8080/uploadMultipleFile

form-data

Body
    - key: file
    - value: 파일첨부

    - key: file
    - value: 파일첨부

    //...
```

&nbsp;

**파일 다운로드**
```java
GET http://localhost:8080/downloadFile/파일이름.확장자
```

&nbsp;

**application.yml**
```yarm
spring:
 servlet:
    multipart:
      max-file-size: 15MB  # 한 파일의 최대 크기
      max-request-size: 15MB  # 한번에 올릴 수 있는 최대 크기
      file-size-threshold: 0  # 메모리에 올릴 수 있는 최대 크기
```
