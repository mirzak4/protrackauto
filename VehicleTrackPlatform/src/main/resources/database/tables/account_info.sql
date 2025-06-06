create table ACCOUNT_INFO
(
    ID                     NUMBER default "NBP04"."ISEQ$$_296346".nextval generated as identity
        constraint ACCOUNT_INFO_PK
        primary key,
    ACTIVE                 CHAR   default '0' not null,
    LAST_VERIFICATION_CODE NVARCHAR2(5)       not null,
    USER_ID                NUMBER             not null
        constraint ACCOUNT_INFO__USER_FK
            references NBP.NBP_USER ()
)
/