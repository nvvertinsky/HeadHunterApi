drop table hh.snapb;
drop table hh.vacancyb;

create table hh.snapb (snapb_id bigint GENERATED ALWAYS AS IDENTITY primary key, dt timestamp without time zone not null);


alter table hh.vacancyb add snapb_id bigint not null;

ALTER TABLE hh.vacancyb ADD CONSTRAINT vacancyb_snapb_01fk
FOREIGN KEY (snap_id)
REFERENCES hh.snapb (snap_id);