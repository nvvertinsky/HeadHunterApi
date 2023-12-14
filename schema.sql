drop table hh.snapb;
drop table hh.vacancyb;
drop table hh.userb;

create table hh.snapb (snapb_id bigint generated always as identity primary key, dt timestamp without time zone not null);
create table hh.vacancyb (vacancyb_id bigint primary key,
                          name varchar(240),
                          description text,
                          snapb_id bigint,
                          foreign key (snapb_id) references hh.snapb (snapb_id));
create table hh.userb(userb_id bigint generated always as identity primary key, username varchar(240) not null, password varchar(240) not null);