create table REGISTRATION
(
    ID                   NUMBER       default "NBP04"."ISEQ$$_281218".nextval generated as identity
        constraint REGISTRATION_PK
        primary key,
    POLICY_NUMBER        VARCHAR2(50)  not null,
    INSURANCE_TYPE       NUMBER        not null,
    INSURED_FROM         DATE          not null,
    INSURED_UNTIL        DATE          not null,
    INSURANCE_COST       NUMBER(10, 2) not null,
    ADDITIONAL_COSTS     NUMBER(10, 2),
    VEHICLE_ID           NUMBER        not null
        constraint FK_VEHICLE
            references VEHICLE,
    INSURANCE_COMPANY_ID NUMBER        not null
        constraint FK_INSURANCE_COMPANY
            references COMPANY,
    CREATED_BY           VARCHAR2(50) default user,
    CREATED_AT           TIMESTAMP(6) default CURRENT_TIMESTAMP,
    MODIFIED_BY          VARCHAR2(50) default user,
    MODIFIED_AT          TIMESTAMP(6) default CURRENT_TIMESTAMP
)
/

