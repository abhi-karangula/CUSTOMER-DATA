create table customer
(
   id integer(5) not null AUTO_INCREMENT,
   customer_code varchar(15) not null,
   name varchar(40),
   email varchar(40),
   ph_number varchar2(10),
   status character(1),
   created_on date,
   updated_on date,
   primary key(id)
);