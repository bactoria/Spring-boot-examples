ORM 이용 가능

### Hash

accounts의 정보들을 저장하기 위해 `hash`를 씀.

![image](https://user-images.githubusercontent.com/25674959/51247959-00dfbc00-19d2-11e9-8a17-70bf1e17f322.png)

![image](https://user-images.githubusercontent.com/25674959/51247953-fc1b0800-19d1-11e9-9bb8-2157f79e227a.png)

![image](https://user-images.githubusercontent.com/25674959/51247932-edccec00-19d1-11e9-8a53-1b945a4323a4.png)

accounts Set이 별도로 생성되며, account id 정보가 들어감.

hash는 키, 밸류로 이루어져있고, id가 키에 해당하며 밸류에 객체의 정보가 들어있음.

---

### sorted Set

자동완성, 랭킹매기기 위해 Redis의 `sortedSet`을 쓴다.

다음에 추가하기로..