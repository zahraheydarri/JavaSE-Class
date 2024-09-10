create table profile_tbl(
  username nvarchar2(20) primary key,
  password nvarchar2(20) not null,
  name nvarchar2(20) not null,
  family nvarchar2(20) not null,
  active number(1),
  access_level char(4) default '0000'
);