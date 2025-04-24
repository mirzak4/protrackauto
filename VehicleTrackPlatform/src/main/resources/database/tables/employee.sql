create table EMPLOYEE
(
    ID         NUMBER generated as identity
        constraint EMPLOYEE_PK
            primary key,
    COMPANY_ID NUMBER not null
        constraint EMPLOYEE_COMPANY___FK
            references COMPANY,
    USER_ID    NUMBER not null
        constraint EMPLOYEE_USER___FK
            references NBP.NBP_USER ()
)
/

