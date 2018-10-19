
/*
DB Migration 중에 Flyway 임.
dependency 추가 해야함.
SQL을 하나씩 저장.. 차곡차곡 모음. classPath는 db.migration 이고
V숫자__이름.sql
*/

/*

주의) 한번 실행시킨 파일은 수정하면 안됨.
수정하면 체크섬 값이 바뀌게 되는데, 체크섬 값이 다르면 에러뜸.
심지어 이 주석마저도 체크섬이 달라져버려서 지우고 다시 실행시켜야됨.
체크섬은 fltway_schema_history 테이블에서 확인 가능함.

*/

drop table if exists account cascade;
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start 1 increment 1;
create table account (id int8 not null, password varchar(255), username varchar(255), primary key (id));