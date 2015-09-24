# --- First database schema

# --- !Ups

set ignorecase true;


create table caradvert (
  id                        bigint not null,
  title                     varchar(255) not null,
  fuel                      varchar(255) not null,
  price                     bigint not null,
  issnew                    varchar(255) not null,
  mileage                   varchar(255) not null,
  carregister               varchar(255) not null,
  constraint pk_caradvert primary key (id))
;


create sequence caradvert_seq start with 1000;


insert into caradvert (id,title,fuel,price,issnew, mileage, carregister) values (  1,'Opel Astra', 'GASOLINE', 1234, 'YES','2000','NO');
insert into caradvert (id,title,fuel,price,issnew, mileage, carregister) values (  2,'Audi', 'DIESEL', 1234, 'YES','15000','NO');
insert into caradvert (id,title,fuel,price,issnew, mileage, carregister) values (  3,'BMW', 'GASOLINE', 1234, 'YES','4000','NO');
insert into caradvert (id,title,fuel,price,issnew, mileage, carregister) values (  4,'Fiat', 'GASOLINE', 1234, 'YES','320','NO');
insert into caradvert (id,title,fuel,price,issnew, mileage, carregister) values (  5,'Toyota Corolla', 'GASOLINE', 1234, 'YES','1','NO');
insert into caradvert (id,title,fuel,price,issnew, mileage, carregister) values (  6,'Jaguar XT', 'GASOLINE', 131234, 'YES','10000','NO');
insert into caradvert (id,title,fuel,price,issnew, mileage, carregister) values (  7,'Mazda', 'GASOLINE', 1234, 'YES','98383','NO');
insert into caradvert (id,title,fuel,price,issnew, mileage, carregister) values (  8,'JOpel Vectra', 'GASOLINE', 1234, 'YES','10000','NO');
insert into caradvert (id,title,fuel,price,issnew, mileage, carregister) values (  9,'Trabant', 'GASOLINE', 1234, 'YES','98383','YES');
insert into caradvert (id,title,fuel,price,issnew, mileage, carregister) values (  10,'Beast', 'DIESEL', 1234, 'YES','98383','NO');
insert into caradvert (id,title,fuel,price,issnew, mileage, carregister) values (  11,'Sunshine', 'GASOLINE', 3312, 'NO','10000','NO');
insert into caradvert (id,title,fuel,price,issnew, mileage, carregister) values (  12,'BMW', 'GASOLINE', 1234, 'YES','98383','NO');



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;
drop table if exists caradvert;


SET REFERENTIAL_INTEGRITY TRUE;
drop sequence if exists caradvert_seq;