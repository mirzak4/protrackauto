create table DRIVER
(
    ID             NUMBER generated as identity
        constraint DRIVER_PK
            primary key,
    USER_ID        NUMBER        not null
        constraint DRIVER_USER___FK
            references NBP.NBP_USER (),
    LICENSE_NUMBER NVARCHAR2(50) not null,
    LICENSE_EXPIRY TIMESTAMP(6)  not null,
    EMPLOYMENT_DATE TIMESTAMP(6)
)
/

