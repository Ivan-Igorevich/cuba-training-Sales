-- begin SALES_ORDER
alter table SALES_ORDER add constraint FK_SALES_ORDER_CUSTOMER foreign key (CUSTOMER_ID) references SALES_CUSTOMER(ID)^
alter table SALES_ORDER add constraint FK_SALES_ORDER_SHOP foreign key (SHOP_ID) references SALES_SHOP(ID)^
create index IDX_SALES_ORDER_CUSTOMER on SALES_ORDER (CUSTOMER_ID)^
create index IDX_SALES_ORDER_SHOP on SALES_ORDER (SHOP_ID)^
-- end SALES_ORDER
-- begin SALES_ITEM
create unique index IDX_SALES_ITEM_UNQ on SALES_ITEM (NAME, COST) where DELETE_TS is null ^
create unique index IDX_SALES_ITEM_UK_NAME on SALES_ITEM (NAME) where DELETE_TS is null ^
-- end SALES_ITEM
-- begin SALES_SHOP_ITEM_LINK
alter table SALES_SHOP_ITEM_LINK add constraint FK_SHOITE_SHOP foreign key (SHOP_ID) references SALES_SHOP(ID)^
alter table SALES_SHOP_ITEM_LINK add constraint FK_SHOITE_ITEM foreign key (ITEM_ID) references SALES_ITEM(ID)^
-- end SALES_SHOP_ITEM_LINK
-- begin SALES_ORDER_ITEM_LINK
alter table SALES_ORDER_ITEM_LINK add constraint FK_ORDITE_ITEM foreign key (ITEM_ID) references SALES_ITEM(ID)^
alter table SALES_ORDER_ITEM_LINK add constraint FK_ORDITE_ORDER foreign key (ORDER_ID) references SALES_ORDER(ID)^
-- end SALES_ORDER_ITEM_LINK
