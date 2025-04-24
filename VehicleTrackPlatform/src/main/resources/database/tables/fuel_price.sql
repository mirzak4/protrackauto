create table FUEL_PRICE
(
    ID             NUMBER        default "NBP04"."ISEQ$$_285764".nextval generated as identity
        primary key,
    PRICE          NUMBER(10, 2)                           not null,
    ISSUE_DATE     DATE                                    not null,
    FUEL_ID        NUMBER                                  not null
        constraint FK_FUEL_PRICE_FUEL
            references FUEL,
    GAS_STATION_ID NUMBER                                  not null
        constraint FK_FUEL_PRICE_COMPANY
            references COMPANY,
    CREATED_BY     VARCHAR2(255) default user              not null,
    CREATED_AT     TIMESTAMP(6)  default current_timestamp not null,
    MODIFIED_BY    VARCHAR2(255) default user              not null,
    MODIFIED_AT    TIMESTAMP(6)  default current_timestamp not null
)
/

