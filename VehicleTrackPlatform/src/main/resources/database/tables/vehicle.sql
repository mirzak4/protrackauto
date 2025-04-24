create table VEHICLE
(
    ID                       NUMBER         default "NBP04"."ISEQ$$_280076".nextval generated as identity
        constraint PK_VEHICLE
        primary key,
    LICENSE_PLATE            NVARCHAR2(50) not null,
    FIRST_REGISTRATION_DATE  DATE          not null,
    FIRST_REGISTRATION_PLACE NVARCHAR2(50) not null,
    FIRST_LICENSE_PLATE      NVARCHAR2(50),
    REGISTRATION_ISSUE_DATE  DATE          not null,
    REGISTRATION_ISSUE_PLACE NVARCHAR2(50) not null,
    FUEL_ID                  NUMBER(10)    not null,
    VEHICLE_CATEGORY         NUMBER(10)    not null,
    VEHICLE_BODY_TYPE        NUMBER(10)    not null,
    COLOR                    VARCHAR2(50)  not null,
    VEHICLE_BRAND_TYPE       VARCHAR2(50)  not null,
    REGISTRATION_NUMBER      VARCHAR2(50)  not null,
    COMMERCIAL_DESCRIPTION   VARCHAR2(50),
    CHASSIS_NUMBER           VARCHAR2(50)  not null,
    PRODUCTION_YEAR          NUMBER(4)     not null,
    MAX_WEIGHT               NUMBER(10)    not null,
    PAYLOAD                  NUMBER(10),
    VEHICLE_WEIGHT           NUMBER(10)    not null,
    POWER_WEIGHT_RATIO       NUMBER(10),
    SEAT_COUNT               NUMBER(3)     not null,
    ENGINE_DISPLACEMENT      NUMBER(10)    not null,
    MAX_POWER                NUMBER(10)    not null,
    ECO_CHARACTERISTICS      VARCHAR2(50)  not null,
    CATALYST                 VARCHAR2(50)  not null,
    ENGINE_NUMBER            VARCHAR2(50)  not null,
    CREATED_AT               TIMESTAMP(6)   default current_timestamp,
    CREATED_BY               NVARCHAR2(100) default user,
    MODIFIED_AT              TIMESTAMP(6)   default current_timestamp,
    MODIFIED_BY              NVARCHAR2(100) default user
)
/

