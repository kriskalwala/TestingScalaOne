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


# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;
drop table if exists caradvert;


SET REFERENTIAL_INTEGRITY TRUE;
drop sequence if exists caradvert_seq;