create table FUEL
(
    ID          NUMBER        default "NBP04"."ISEQ$$_285761".nextval generated as identity
        primary key,
    NAME        VARCHAR2(255)                           not null,
    CREATED_BY  VARCHAR2(255) default user              not null,
    CREATED_AT  TIMESTAMP(6)  default current_timestamp not null,
    MODIFIED_BY VARCHAR2(255) default user              not null,
    MODIFIED_AT TIMESTAMP(6)  default current_timestamp not null
)
/

