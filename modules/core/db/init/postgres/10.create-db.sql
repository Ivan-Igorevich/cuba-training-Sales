-- begin SALES_CUSTOMER
create table SALES_CUSTOMER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(100) not null,
    EMAIL varchar(50),
    --
    primary key (ID)
)^
-- end SALES_CUSTOMER
-- begin SALES_ORDER
create table SALES_ORDER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CUSTOMER_ID uuid,
    DATE_ date not null,
    AMOUNT decimal(19, 2),
    SHOP_ID uuid not null,
    --
    primary key (ID)
)^
-- end SALES_ORDER
-- begin SALES_ITEM
create table SALES_ITEM (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    COST varchar(255) not null,
    --
    primary key (ID)
)^
-- end SALES_ITEM
-- begin SALES_SHOP
create table SALES_SHOP (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    ADDRESS_COUNTRY varchar(255),
    ADDRESS_TOWN varchar(255),
    ADDRESS_STREET varchar(255),
    ADDRESS_HOUSE varchar(255) not null,
    --
    TYPE_ integer not null,
    --
    primary key (ID)
)^
-- end SALES_SHOP
-- begin SALES_SHOP_ITEM_LINK
create table SALES_SHOP_ITEM_LINK (
    SHOP_ID uuid,
    ITEM_ID uuid,
    primary key (SHOP_ID, ITEM_ID)
)^
-- end SALES_SHOP_ITEM_LINK
-- begin SALES_ORDER_ITEM_LINK
create table SALES_ORDER_ITEM_LINK (
    ITEM_ID uuid,
    ORDER_ID uuid,
    primary key (ITEM_ID, ORDER_ID)
)^
-- end SALES_ORDER_ITEM_LINK
