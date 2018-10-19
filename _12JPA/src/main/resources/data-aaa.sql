
/*hibernate의 auto ddl 생성을 사용하지 않고, schema.sql을 사용할 경우, data.sql 이 자동으로 읽혀짐.
여기에는 schema가 아닌 DDL을 추가할 것.*/

/*
property에 spring.datasource.platform=aaa 설정이 있으면 실행됨.
( hibernate가 auto-ddl을 하든 말든 상관X)
*/

insert into accountAAA values (1, 'dataSQL-AAA', 'pass')