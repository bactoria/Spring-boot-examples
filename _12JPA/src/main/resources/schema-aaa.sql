
/*
property에 spring.datasource.platform=aaa 설정이 있으면 실행됨.
( hibernate가 auto-ddl을 하든 말든 상관X)
*/

drop table if exists account cascade
drop sequence if exists hibernate_sequence
create sequence hibernate_sequence start 1 increment 1
create table accountAAA (id int8 not null, password varchar(255), username varchar(255), primary key (id))