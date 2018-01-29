CREATE SCHEMA IF NOT EXISTS SAMARA;

DROP TABLE IF EXISTS SAMARA.ARTICLE;

CREATE TABLE SAMARA.ARTICLE
	(ID NUMBER GENERATED ALWAYS AS IDENTITY,
  	NAME NVARCHAR2(128) NOT NULL,
  	PRICE NUMBER (8, 2) NOT NULL,
	  CREATED TIMESTAMP NOT NULL
)