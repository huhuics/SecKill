# SecKill 秒杀系统
## 第一阶段 多线程模拟抢票
 1. 数据库设计
  + 商品表
  ```SQL
  drop table GOODS cascade constraints;
/*==============================================================*/
/* Table: GOODS                                                 */
/*==============================================================*/
create table GOODS 
(
   ID                   NUMBER               not null,
   NAME                 VARCHAR2(128)        not null,
   DESCRIPTION          VARCHAR2(512),
   TOTAL_AMOUNT         NUMBER(18)           not null,
   QUANTITY             NUMBER               not null,
   GMT_UPDATE           DATE                 not null,
   GMT_CREATE           DATE                 not null,
   constraint PK_GOODS primary key (ID)
);
comment on table GOODS is
'商品表';
comment on column GOODS.ID is
'主键，自增';
comment on column GOODS.NAME is
'商品名称';
comment on column GOODS.DESCRIPTION is
'商品描述';
comment on column GOODS.TOTAL_AMOUNT is
'商品价格';
comment on column GOODS.QUANTITY is
'库存数量';
comment on column GOODS.GMT_UPDATE is
'修改时间';
comment on column GOODS.GMT_CREATE is
'创建时间';
  ```
  + 订单表
  ```SQL
  drop table "ORDER" cascade constraints;
/*==============================================================*/
/* Table: "ORDER"                                               */
/*==============================================================*/
create table "ORDER" 
(
   ID                   NUMBER               not null,
   ORDER_NO             VARCHAR2(64)         not null,
   GOODS_ID             NUMBER               not null,
   GOODS_NAME           VARCHAR2(128)        not null,
   BUYER_ID             VARCHAR2(64)         not null,
   TOTAL_AMOUNT         NUMBER(18)           not null,
   GMT_UPDATE           DATE                 not null,
   GMT_CREATE           DATE,
   constraint PK_ORDER primary key (ID)
);
comment on table "ORDER" is
'订单表';
comment on column "ORDER".ID is
'主键，自增';
comment on column "ORDER".ORDER_NO is
'订单编号';
comment on column "ORDER".GOODS_ID is
'外键，商品编号';
comment on column "ORDER".GOODS_NAME is
'商品名称';
comment on column "ORDER".BUYER_ID is
'购买人ID';
comment on column "ORDER".TOTAL_AMOUNT is
'商品总价';
comment on column "ORDER".GMT_UPDATE is
'修改时间';
comment on column "ORDER".GMT_CREATE is
'创建时间';
  ```
