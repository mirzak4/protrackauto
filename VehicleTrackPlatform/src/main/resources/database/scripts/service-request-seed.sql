BEGIN
    DECLARE SCRIPT_ID RAW(16) := '339AF90AD65FF89BE063143410ACC0C4';
    BEGIN
        DECLARE NOW DATE;
        BEGIN
            DECLARE
                EXEC_COUNT INT := 0;
                SPEEDY_ID    NUMBER;
                ELITE_ID     NUMBER;
                FIXFAST_ID   NUMBER;
            BEGIN
                SELECT COUNT(1) INTO EXEC_COUNT FROM SCRIPT_EXECUTION_HISTORY WHERE ID = SCRIPT_ID;
                IF EXEC_COUNT = 0 THEN

                    ----------------------------------------------------------------
                    -- 1) Retrieve IDs for service-provider companies
                    ----------------------------------------------------------------
                    SELECT ID INTO SPEEDY_ID FROM COMPANY WHERE COMPANY_TYPE = 2 AND NAME = 'Speedy Auto Services';
                    SELECT ID INTO ELITE_ID FROM COMPANY WHERE COMPANY_TYPE = 2 AND NAME = 'Elite Car Care';
                    SELECT ID INTO FIXFAST_ID FROM COMPANY WHERE COMPANY_TYPE = 2 AND NAME = 'FixFast Garage';

                    ----------------------------------------------------------------
                    -- 2) Insert seed data into SERVICE_REQUEST
                    --    service_type: 1 = REGULAR_SERVICE, 2 = TECHNICAL_INSPECTION
                    --    status:       1 = REQUESTED, 2 = SCHEDULED,
                    --                  3 = IN_PROGRESS, 4 = AWAITING_PARTS,
                    --                  5 = COMPLETED, 6 = CANCELED
                    ----------------------------------------------------------------

                    -- a) Regular service request at Speedy Auto Services
                    INSERT INTO SERVICE_REQUEST (
                        SERVICE_TYPE,
                        FISCAL_RECEIPT_NUMBER,
                        COST,
                        STATUS,
                        REQUEST_DATE,
                        REQUESTED_BY,
                        VEHICLE_ID,
                        SERVICER_ID
                    ) VALUES (
                        1,                                        -- REGULAR_SERVICE
                        3001,
                        300.00,
                        1,                                        -- REQUESTED
                        TO_DATE('2025-03-01','YYYY-MM-DD'),
                        'Alice Mechanic',
                        (SELECT ID FROM VEHICLE WHERE LICENSE_PLATE = 'BG888HH'),
                        SPEEDY_ID
                    );

                    -- b) Technical inspection request at Elite Car Care
                    INSERT INTO SERVICE_REQUEST (
                        SERVICE_TYPE, FISCAL_RECEIPT_NUMBER, COST, STATUS,
                        REQUEST_DATE, REQUESTED_BY, VEHICLE_ID, SERVICER_ID
                    ) VALUES (
                        2,             -- TECHNICAL_INSPECTION
                        3002,
                        150.00,
                        2,             -- SCHEDULED
                        TO_DATE('2025-03-02','YYYY-MM-DD'),
                        'Bob Inspector',
                        (SELECT ID FROM VEHICLE WHERE LICENSE_PLATE = 'BG333SS'),
                        ELITE_ID
                    );

                    -- c) In-progress regular service at FixFast Garage
                    INSERT INTO SERVICE_REQUEST (
                        SERVICE_TYPE, FISCAL_RECEIPT_NUMBER, COST, STATUS,
                        REQUEST_DATE, REQUESTED_BY, VEHICLE_ID, SERVICER_ID
                    ) VALUES (
                        1,             -- REGULAR_SERVICE
                        3003,
                        200.00,
                        3,             -- IN_PROGRESS
                        TO_DATE('2025-03-03','YYYY-MM-DD'),
                        'Carol Technician',
                        (SELECT ID FROM VEHICLE WHERE LICENSE_PLATE = 'BG456MM'),
                        FIXFAST_ID
                    );

                    -- d) Awaiting parts for bus maintenance at Speedy Auto Services
                    INSERT INTO SERVICE_REQUEST (
                        SERVICE_TYPE, FISCAL_RECEIPT_NUMBER, COST, STATUS,
                        REQUEST_DATE, REQUESTED_BY, VEHICLE_ID, SERVICER_ID
                    ) VALUES (
                        1,             -- REGULAR_SERVICE
                        3004,
                        1200.00,
                        4,             -- AWAITING_PARTS
                        TO_DATE('2025-03-10','YYYY-MM-DD'),
                        'Dave Busman',
                        (SELECT ID FROM VEHICLE WHERE LICENSE_PLATE = 'BG111BB'),
                        SPEEDY_ID
                    );

                    -- e) Completed technical inspection at Elite Car Care
                    INSERT INTO SERVICE_REQUEST (
                        SERVICE_TYPE, FISCAL_RECEIPT_NUMBER, COST, STATUS,
                        REQUEST_DATE, REQUESTED_BY, VEHICLE_ID, SERVICER_ID
                    ) VALUES (
                        2,             -- TECHNICAL_INSPECTION
                        3005,
                        180.00,
                        5,             -- COMPLETED
                        TO_DATE('2025-03-15','YYYY-MM-DD'),
                        'Eve Inspector',
                        (SELECT ID FROM VEHICLE WHERE LICENSE_PLATE = 'BG222VV'),
                        ELITE_ID
                    );
                    COMMIT;

                    SELECT CAST(SYSTIMESTAMP AT TIME ZONE 'UTC' AS DATE) INTO NOW FROM DUAL;

                    INSERT INTO SCRIPT_EXECUTION_HISTORY
                    VALUES (SCRIPT_ID, 'service-request-seed.sql', NOW);
                    COMMIT;
                END IF;
            END;
        END;
    END;
END;
