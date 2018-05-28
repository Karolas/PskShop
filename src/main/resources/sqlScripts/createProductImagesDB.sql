CREATE TABLE PRODUCT_IMAGES
(
	ID               INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	PRODUCT_ID       INTEGER NOT NULL,
	IMAGE            BYTEA,
  FOREIGN KEY (PRODUCT_ID) REFERENCES products(ID)
);