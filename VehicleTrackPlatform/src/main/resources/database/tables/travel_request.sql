create table TRAVEL_REQUEST
(
    ID                 NUMBER        default "NBP04"."ISEQ$$_286316".nextval generated as identity
        primary key,
    APPROVED_BY        VARCHAR2(255),
    APPROVAL_DATE      DATE,
    DEPARTURE_LOCATION VARCHAR2(255)                           not null,
    DESTINATION        VARCHAR2(255)                           not null,
    START_DATE         DATE                                    not null,
    END_DATE           DATE,
    REQUEST_STATUS     NUMBER                                  not null,
    VEHICLE_ID         NUMBER                                  not null
        constraint FK_TRAVEL_REQUEST_VEHICLE
            references VEHICLE,
    DRIVER_ID          NUMBER                                  not null
        constraint FK_TRAVEL_REQUEST_DRIVER
            references DRIVER,
    CREATED_BY         VARCHAR2(255) default user              not null,
    CREATED_AT         TIMESTAMP(6)  default current_timestamp not null,
    MODIFIED_BY        VARCHAR2(255) default user              not null,
    MODIFIED_AT        TIMESTAMP(6)  default current_timestamp not null
)
/

