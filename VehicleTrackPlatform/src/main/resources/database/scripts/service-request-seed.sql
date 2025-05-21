BEGIN
    DECLARE SCRIPT_ID RAW(16) := '3518B3BF3703E195E063143410ACAEDB';
    BEGIN
        DECLARE NOW DATE;
        BEGIN
            DECLARE EXEC_COUNT INT := 0;
            BEGIN
                SELECT COUNT(1) INTO EXEC_COUNT FROM SCRIPT_EXECUTION_HISTORY WHERE ID = SCRIPT_ID;
                IF EXEC_COUNT = 0 THEN

                    DECLARE
                        V_ID1 NUMBER; V_ID2 NUMBER; V_ID3 NUMBER;
                        V_ID4 NUMBER; V_ID5 NUMBER; V_ID6 NUMBER;
                        V_ID7 NUMBER; V_ID8 NUMBER; V_ID9 NUMBER;
                        V_ID10 NUMBER; V_ID11 NUMBER; V_ID12 NUMBER;
                        V_ID13 NUMBER; V_ID14 NUMBER; V_ID15 NUMBER;
                        V_ID16 NUMBER; V_ID17 NUMBER; V_ID18 NUMBER;
                        V_ID19 NUMBER; V_ID20 NUMBER;

                        C_ID1 NUMBER; C_ID2 NUMBER; C_ID3 NUMBER;
                        C_ID4 NUMBER; C_ID5 NUMBER; C_ID6 NUMBER;

                        FULL_NAME VARCHAR2(200) := 'Admin';
                    BEGIN
                        SELECT ID INTO V_ID1 FROM VEHICLE WHERE LICENSE_PLATE = 'BG123AA';
                        SELECT ID INTO V_ID2 FROM VEHICLE WHERE LICENSE_PLATE = 'BG789TT';
                        SELECT ID INTO V_ID3 FROM VEHICLE WHERE LICENSE_PLATE = 'BG456MM';
                        SELECT ID INTO V_ID4 FROM VEHICLE WHERE LICENSE_PLATE = 'BG111BB';
                        SELECT ID INTO V_ID5 FROM VEHICLE WHERE LICENSE_PLATE = 'BG222VV';
                        SELECT ID INTO V_ID6 FROM VEHICLE WHERE LICENSE_PLATE = 'BG333SS';
                        SELECT ID INTO V_ID7 FROM VEHICLE WHERE LICENSE_PLATE = 'BG444PP';
                        SELECT ID INTO V_ID8 FROM VEHICLE WHERE LICENSE_PLATE = 'BG555TT';
                        SELECT ID INTO V_ID9 FROM VEHICLE WHERE LICENSE_PLATE = 'BG666SS';
                        SELECT ID INTO V_ID10 FROM VEHICLE WHERE LICENSE_PLATE = 'BG777BB';
                        SELECT ID INTO V_ID11 FROM VEHICLE WHERE LICENSE_PLATE = 'BG888HH';
                        SELECT ID INTO V_ID12 FROM VEHICLE WHERE LICENSE_PLATE = 'BG999CC';
                        SELECT ID INTO V_ID13 FROM VEHICLE WHERE LICENSE_PLATE = 'BG101CV';
                        SELECT ID INTO V_ID14 FROM VEHICLE WHERE LICENSE_PLATE = 'BG202WG';
                        SELECT ID INTO V_ID15 FROM VEHICLE WHERE LICENSE_PLATE = 'BG303MV';
                        SELECT ID INTO V_ID16 FROM VEHICLE WHERE LICENSE_PLATE = 'BG404JP';
                        SELECT ID INTO V_ID17 FROM VEHICLE WHERE LICENSE_PLATE = 'BG505LM';
                        SELECT ID INTO V_ID18 FROM VEHICLE WHERE LICENSE_PLATE = 'BG606CM';
                        SELECT ID INTO V_ID19 FROM VEHICLE WHERE LICENSE_PLATE = 'BG707RD';
                        SELECT ID INTO V_ID20 FROM VEHICLE WHERE LICENSE_PLATE = 'BG909HY';

                        SELECT ID INTO C_ID1 FROM COMPANY WHERE COMPANY_TYPE = 2 AND NAME = 'Speedy Auto Services';
                        SELECT ID INTO C_ID2 FROM COMPANY WHERE COMPANY_TYPE = 2 AND NAME = 'Elite Car Care';
                        SELECT ID INTO C_ID3 FROM COMPANY WHERE COMPANY_TYPE = 2 AND NAME = 'FixFast Garage';
                        SELECT ID INTO C_ID4 FROM COMPANY WHERE COMPANY_TYPE = 2 AND NAME = 'QuickFix Auto';
                        SELECT ID INTO C_ID5 FROM COMPANY WHERE COMPANY_TYPE = 2 AND NAME = 'ProTune Mechanics';
                        SELECT ID INTO C_ID6 FROM COMPANY WHERE COMPANY_TYPE = 2 AND NAME = 'UrbanCar Service';

                        ----------------------------------------------------------------
                        -- 2) Insert seed data into SERVICE_REQUEST
                        --    service_type: 1 = REGULAR_SERVICE, 2 = TECHNICAL_INSPECTION
                        --    status:       1 = REQUESTED, 2 = SCHEDULED,
                        --                  3 = IN_PROGRESS, 4 = AWAITING_PARTS,
                        --                  5 = COMPLETED, 6 = CANCELED
                        ----------------------------------------------------------------

                        -- a) Regular service request
                        INSERT INTO SERVICE_REQUEST (
                            SERVICE_TYPE, FISCAL_RECEIPT_NUMBER, COST, STATUS, REQUEST_DATE, REQUESTED_BY, VEHICLE_ID, SERVICER_ID
                        ) VALUES (
                            1, 3001, 300.00, 1, TO_DATE('2025-03-01','YYYY-MM-DD'), FULL_NAME, V_ID1, C_ID1
                        );

                        -- b) Technical inspection request
                        INSERT INTO SERVICE_REQUEST (
                            SERVICE_TYPE, FISCAL_RECEIPT_NUMBER, COST, STATUS, REQUEST_DATE, REQUESTED_BY, VEHICLE_ID, SERVICER_ID
                        ) VALUES (
                            2, 3002, 150.00, 2, TO_DATE('2025-03-02','YYYY-MM-DD'), FULL_NAME, V_ID2, C_ID2
                        );

                        -- c) In-progress regular service
                        INSERT INTO SERVICE_REQUEST (
                            SERVICE_TYPE, FISCAL_RECEIPT_NUMBER, COST, STATUS, REQUEST_DATE, REQUESTED_BY, VEHICLE_ID, SERVICER_ID
                        ) VALUES (
                            1, 3003, 200.00, 3, TO_DATE('2025-03-03','YYYY-MM-DD'), FULL_NAME, V_ID3, C_ID3
                        );

                        -- d) Awaiting parts for bus maintenance
                        INSERT INTO SERVICE_REQUEST (
                            SERVICE_TYPE, FISCAL_RECEIPT_NUMBER, COST, STATUS, REQUEST_DATE, REQUESTED_BY, VEHICLE_ID, SERVICER_ID
                        ) VALUES (
                            1, 3004, 1200.00, 4, TO_DATE('2025-03-10','YYYY-MM-DD'), FULL_NAME, V_ID4, C_ID4
                        );

                        -- e) Completed technical inspection
                        INSERT INTO SERVICE_REQUEST (
                            SERVICE_TYPE, FISCAL_RECEIPT_NUMBER, COST, STATUS, REQUEST_DATE, REQUESTED_BY, VEHICLE_ID, SERVICER_ID
                        ) VALUES (
                            2, 3005, 180.00, 5, TO_DATE('2025-03-15','YYYY-MM-DD'), FULL_NAME, V_ID5, C_ID5
                        );

                        -- f) Scheduled regular service
                        INSERT INTO SERVICE_REQUEST (
                            SERVICE_TYPE, FISCAL_RECEIPT_NUMBER, COST, STATUS, REQUEST_DATE, REQUESTED_BY, VEHICLE_ID, SERVICER_ID
                        ) VALUES (
                            1, 3006, 250.00, 2, TO_DATE('2025-03-20','YYYY-MM-DD'), FULL_NAME, V_ID6, C_ID6
                        );

                        -- g) Canceled technical inspection
                        INSERT INTO SERVICE_REQUEST (
                            SERVICE_TYPE, FISCAL_RECEIPT_NUMBER, COST, STATUS, REQUEST_DATE, REQUESTED_BY, VEHICLE_ID, SERVICER_ID
                        ) VALUES (
                            2, 3007, 160.00, 6, TO_DATE('2025-03-21','YYYY-MM-DD'), FULL_NAME, V_ID7, C_ID1
                        );

                        -- h) New request for technnical inspection
                        INSERT INTO SERVICE_REQUEST (
                            SERVICE_TYPE, FISCAL_RECEIPT_NUMBER, COST, STATUS, REQUEST_DATE, REQUESTED_BY, VEHICLE_ID, SERVICER_ID
                        ) VALUES (
                            2, 3008, 170.00, 1, TO_DATE('2025-03-22','YYYY-MM-DD'), FULL_NAME, V_ID8, C_ID2
                        );

                        -- i) Service in progress
                        INSERT INTO SERVICE_REQUEST (
                            SERVICE_TYPE, FISCAL_RECEIPT_NUMBER, COST, STATUS, REQUEST_DATE, REQUESTED_BY, VEHICLE_ID, SERVICER_ID
                        ) VALUES (
                            1, 3009, 900.00, 3, TO_DATE('2025-03-25','YYYY-MM-DD'), FULL_NAME, V_ID9, C_ID3
                        );

                        -- j) Completed regular service
                        INSERT INTO SERVICE_REQUEST (
                            SERVICE_TYPE, FISCAL_RECEIPT_NUMBER, COST, STATUS, REQUEST_DATE, REQUESTED_BY, VEHICLE_ID, SERVICER_ID
                        ) VALUES (
                            1, 3010, 320.00, 5, TO_DATE('2025-03-27','YYYY-MM-DD'), FULL_NAME, V_ID10, C_ID4
                        );

                        -- k) Awaiting parts for technical inspection
                        INSERT INTO SERVICE_REQUEST (
                            SERVICE_TYPE, FISCAL_RECEIPT_NUMBER, COST, STATUS, REQUEST_DATE, REQUESTED_BY, VEHICLE_ID, SERVICER_ID
                        ) VALUES (
                            2, 3011, 180.00, 4, TO_DATE('2025-03-29','YYYY-MM-DD'), FULL_NAME, V_ID11, C_ID5
                        );

                        -- l) Completed technical inspection
                        INSERT INTO SERVICE_REQUEST (
                            SERVICE_TYPE, FISCAL_RECEIPT_NUMBER, COST, STATUS, REQUEST_DATE, REQUESTED_BY, VEHICLE_ID, SERVICER_ID
                        ) VALUES (
                            2, 3012, 190.00, 5, TO_DATE('2025-03-30','YYYY-MM-DD'), FULL_NAME, V_ID12, C_ID6
                        );

                        -- m) New requested regular service
                        INSERT INTO SERVICE_REQUEST (
                            SERVICE_TYPE, FISCAL_RECEIPT_NUMBER, COST, STATUS, REQUEST_DATE, REQUESTED_BY, VEHICLE_ID, SERVICER_ID
                        ) VALUES (
                            1, 3013, 280.00, 1, TO_DATE('2025-04-01','YYYY-MM-DD'), FULL_NAME, V_ID13, C_ID1
                        );

                        -- n) Canceled regular service
                        INSERT INTO SERVICE_REQUEST (
                            SERVICE_TYPE, FISCAL_RECEIPT_NUMBER, COST, STATUS, REQUEST_DATE, REQUESTED_BY, VEHICLE_ID, SERVICER_ID
                        ) VALUES (
                            1, 3014, 260.00, 6, TO_DATE('2025-04-02','YYYY-MM-DD'), FULL_NAME, V_ID14, C_ID2
                        );

                        -- o) Scheduled technical inspection
                        INSERT INTO SERVICE_REQUEST (
                            SERVICE_TYPE, FISCAL_RECEIPT_NUMBER, COST, STATUS, REQUEST_DATE, REQUESTED_BY, VEHICLE_ID, SERVICER_ID
                        ) VALUES (
                            2, 3015, 200.00, 2, TO_DATE('2025-04-03','YYYY-MM-DD'), FULL_NAME, V_ID15, C_ID3
                        );

                        -- p) Service in progress for technical inspection
                        INSERT INTO SERVICE_REQUEST (
                            SERVICE_TYPE, FISCAL_RECEIPT_NUMBER, COST, STATUS, REQUEST_DATE, REQUESTED_BY, VEHICLE_ID, SERVICER_ID
                        ) VALUES (
                            2, 3016, 210.00, 3, TO_DATE('2025-04-04','YYYY-MM-DD'), FULL_NAME, V_ID16, C_ID4
                        );

                        -- q) Awaiting parts for regular service
                        INSERT INTO SERVICE_REQUEST (
                            SERVICE_TYPE, FISCAL_RECEIPT_NUMBER, COST, STATUS, REQUEST_DATE, REQUESTED_BY, VEHICLE_ID, SERVICER_ID
                        ) VALUES (
                            1, 3017, 450.00, 4, TO_DATE('2025-04-05','YYYY-MM-DD'), FULL_NAME, V_ID17, C_ID5
                        );

                        -- r) Completed technical inspection
                        INSERT INTO SERVICE_REQUEST (
                            SERVICE_TYPE, FISCAL_RECEIPT_NUMBER, COST, STATUS, REQUEST_DATE, REQUESTED_BY, VEHICLE_ID, SERVICER_ID
                        ) VALUES (
                            2, 3018, 195.00, 5, TO_DATE('2025-04-06','YYYY-MM-DD'), FULL_NAME, V_ID18, C_ID6
                        );

                        -- s) Requested regular service
                        INSERT INTO SERVICE_REQUEST (
                            SERVICE_TYPE, FISCAL_RECEIPT_NUMBER, COST, STATUS, REQUEST_DATE, REQUESTED_BY, VEHICLE_ID, SERVICER_ID
                        ) VALUES (
                            1, 3019, 300.00, 1, TO_DATE('2025-04-07','YYYY-MM-DD'), FULL_NAME, V_ID19, C_ID1
                        );

                        -- t) Scheduled technical inspection
                        INSERT INTO SERVICE_REQUEST (
                            SERVICE_TYPE, FISCAL_RECEIPT_NUMBER, COST, STATUS, REQUEST_DATE, REQUESTED_BY, VEHICLE_ID, SERVICER_ID
                        ) VALUES (
                            2, 3020, 185.00, 2, TO_DATE('2025-04-08','YYYY-MM-DD'), FULL_NAME, V_ID20, C_ID2
                        );
                    END;

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
