create table  product_tbl (
     id number primary key ,
     productName varchar2(20),
     price number,
     count number
);

create sequence product_seq start with 1 increment by 1;