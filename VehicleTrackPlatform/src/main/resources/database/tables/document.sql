create table DOCUMENT
(
    ID         NUMBER       default "NBP04"."ISEQ$$_308329".nextval generated as identity
        constraint DOCUMENT_PK
        primary key,
    FILE_NAME  NVARCHAR2(255)                         not null,
    FILE_TYPE  NVARCHAR2(50)                          not null,
    CONTENT    BLOB                                   not null,
    CREATED_AT TIMESTAMP(6) default CURRENT_TIMESTAMP not null
)
/