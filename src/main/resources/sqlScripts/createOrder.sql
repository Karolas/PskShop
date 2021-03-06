CREATE TABLE ORDERS
(
	ID               INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
	ACCOUNT_ID       INTEGER,
	STATUS           VARCHAR(25),
	ORDER_CREATED    TIMESTAMP,
	FOREIGN KEY (ACCOUNT_ID) REFERENCES accounts(ID)
);