create table SCRIPT_EXECUTION_HISTORY
(
    ID          RAW(16)        not null
        constraint SCRIPT_EXECUTION_HISTORY_PK
            primary key,
    NAME        NVARCHAR2(100) not null,
    EXECUTED_AT TIMESTAMP(6)   not null
)
/

