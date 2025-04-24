create table SERVICE_REQUEST
(
    ID                    NUMBER        default "NBP04"."ISEQ$$_285770".nextval generated as identity
        primary key,
    SERVICE_TYPE          NUMBER                                  not null,
    FISCAL_RECEIPT_NUMBER NUMBER                                  not null,
    COST                  NUMBER(10, 2)                           not null,
    STATUS                NUMBER                                  not null,
    REQUEST_DATE          DATE                                    not null,
    REQUESTED_BY          VARCHAR2(255)                           not null,
    VEHICLE_ID            NUMBER                                  not null
        constraint FK_SERVICE_REQUEST_VEHICLE
            references VEHICLE,
    SERVICER_ID           NUMBER                                  not null
        constraint FK_SERVICE_REQUEST_COMPANY
            references COMPANY,
    CREATED_BY            VARCHAR2(255) default user              not null,
    CREATED_AT            TIMESTAMP(6)  default current_timestamp not null,
    MODIFIED_BY           VARCHAR2(255) default user              not null,
    MODIFIED_AT           TIMESTAMP(6)  default current_timestamp not null
)
/

