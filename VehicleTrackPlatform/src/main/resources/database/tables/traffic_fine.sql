create table TRAFFIC_FINE
(
    ID                    NUMBER        default "NBP04"."ISEQ$$_286313".nextval generated as identity
        primary key,
    ISSUE_DATE            DATE                                    not null,
    PAYMENT_DUE_DATE      DATE,
    VIOLATION_DESCRIPTION VARCHAR2(255)                           not null,
    VIOLATION_TYPE        NUMBER                                  not null,
    LOCATION              VARCHAR2(255)                           not null,
    PAYMENT_STATUS        NUMBER                                  not null,
    AMOUNT                NUMBER(10, 2)                           not null,
    VEHICLE_ID            NUMBER                                  not null
        constraint FK_TRAFFIC_FINE_VEHICLE
            references VEHICLE,
    DRIVER_ID             NUMBER                                  not null
        constraint FK_TRAFFIC_FINE_DRIVER
            references DRIVER,
    CREATED_BY            VARCHAR2(255) default user              not null,
    CREATED_AT            TIMESTAMP(6)  default current_timestamp not null,
    MODIFIED_BY           VARCHAR2(255) default user              not null,
    MODIFIED_AT           TIMESTAMP(6)  default current_timestamp not null
)
/

