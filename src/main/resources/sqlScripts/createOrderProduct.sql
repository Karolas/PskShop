CREATE TABLE ORDER_PRODUCT
(
	ID               INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	ORDER_ID         INTEGER NOT NULL,
	PRODUCT_NAME     VARCHAR(256) NOT NULL,
  AMOUNT           INTEGER NOT NULL,
  PRICE            NUMERIC(19, 2),
  FOREIGN KEY (ORDER_ID) REFERENCES orders(ID)
);