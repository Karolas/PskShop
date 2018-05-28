CREATE TABLE PRODUCT_ATTRIBUTES
(
	ID               INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	PRODUCT_ID       INTEGER NOT NULL,
	ATTRIBUTE_NAME   VARCHAR(256) NOT NULL,
  ATTRIBUTE_DESCRIPTION   VARCHAR(256) NOT NULL,
  FOREIGN KEY (PRODUCT_ID) REFERENCES products(ID)
);