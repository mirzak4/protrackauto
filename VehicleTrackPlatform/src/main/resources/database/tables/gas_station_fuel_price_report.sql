create table GAS_STATION_FUEL_PRICE_REPORT
(
    ID          NUMBER generated as identity,
    COMPANY_ID  NUMBER         not null
        constraint GAS_STATION_FUEL_PRICE_REPORT_COMPANY_FK
            references COMPANY,
    NAME        NVARCHAR2(255) not null,
    DOCUMENT_ID NUMBER         not null
        constraint GAS_STATION_FUEL_PRICE_REPORT_DOCUMENT_FK
            references DOCUMENT
)
/