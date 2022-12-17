CREATE TABLE PRICES (
    ID bigint auto_increment primary key,
	BRAND_ID int NOT NULL,
	START_DATE timestamp NOT NULL,
	END_DATE timestamp NOT NULL,
	PRICE_LIST int NOT NULL,
	PRODUCT_ID int NOT NULL,
	PRIORITY int NOT NULL,
	PRICE decimal(6,2) NOT NULL,
	CURR varchar(3) NOT NULL
);