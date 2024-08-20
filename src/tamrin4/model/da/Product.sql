CREATE TABLE PRODUCT(
  ID NUMBER PRIMARY KEY ,
  NAME NVARCHAR2(30),
  BRAND NVARCHAR2(20),
  PRICE NUMBER,
  COUNT NUMBER
);

CREATE SEQUENCE PRODUCT_SEQ START WITH 1 INCREMENT BY 1;