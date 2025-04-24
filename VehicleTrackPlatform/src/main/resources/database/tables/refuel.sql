create table REFUEL
(
    ID                    NUMBER        default "NBP04"."ISEQ$$_285767".nextval generated as identity
        primary key,
    FISCAL_RECEIPT_NUMBER NUMBER                                  not null,
    REFUEL_DATE           DATE                                    not null,
    QUANTITY              NUMBER(10, 2)                           not null,
    TOTAL_CHARGE_AMOUNT   NUMBER(10, 2)                           not null,
    GAS_STATION_ID        NUMBER                                  not null
        constraint FK_REFUEL_COMPANY
            references COMPANY,
    VEHICLE_ID            NUMBER                                  not null
        constraint FK_REFUEL_VEHICLE
            references VEHICLE,
    CREATED_BY            VARCHAR2(255) default user              not null,
    CREATED_AT            TIMESTAMP(6)  default current_timestamp not null,
    MODIFIED_BY           VARCHAR2(255) default user              not null,
    MODIFIED_AT           TIMESTAMP(6)  default current_timestamp not null
)
/

