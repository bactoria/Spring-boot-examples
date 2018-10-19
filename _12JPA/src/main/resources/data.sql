
/*hibernate의 auto ddl 생성을 사용하지 않고, schema.sql을 사용할 경우, data.sql 이 자동으로 읽혀짐.
여기에는 schema가 아닌 DDL을 추가할 것.*/

insert into account values (1, 'dataSQL', 'pass')