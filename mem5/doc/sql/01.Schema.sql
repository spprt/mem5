create schema makao default character set utf8;
use makao;
create user 'mem5'@'%' identified by 'makao123';

grant all privileges on makao.* to 'mem5'@'%';

flush privileges;
