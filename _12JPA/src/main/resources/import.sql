
/*hibernate의 auto ddl 생성을 사용할 경우, import.sql 이 자동으로 읽혀짐.
여기에는 schema가 아닌 DDL을 추가할 것.*/

insert into account values (1, 'importSQL', 'pass')
