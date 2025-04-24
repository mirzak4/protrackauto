create table COMPANY
(
    ID             NUMBER         default "NBP04"."ISEQ$$_275986".nextval generated as identity
        constraint COMPANY_PK
        primary key,
    COMPANY_TYPE   NUMBER                                   not null,
    NAME           NVARCHAR2(255)                           not null
        constraint COMPANY_UQ_NAME
            unique,
    ADDRESS        NVARCHAR2(255)                           not null,
    PHONE_NUMBER   NVARCHAR2(255)                           not null,
    EMAIL          NVARCHAR2(255)                           not null,
    CONTACT_PERSON NVARCHAR2(255)                           not null,
    CREATED_AT     TIMESTAMP(6)   default current_timestamp not null,
    CREATED_BY     NVARCHAR2(100) default user              not null,
    MODIFIED_AT    TIMESTAMP(6)   default current_timestamp not null,
    MODIFIED_BY    NVARCHAR2(100) default user              not null
)
/

