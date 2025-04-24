BEGIN
    DECLARE SCRIPT_ID RAW(16) := '33880B3146657FE5E063143410AC812D';
    BEGIN
        DECLARE NOW DATE;
        BEGIN
            DECLARE EXEC_COUNT INT := 0;
            BEGIN

                SELECT COUNT(1) INTO EXEC_COUNT FROM SCRIPT_EXECUTION_HISTORY WHERE ID = SCRIPT_ID;
                IF EXEC_COUNT = 0 THEN

                    -- Vehicle 1 (Travel Car - Hatchback)
                    INSERT INTO REFUEL (
                        FISCAL_RECEIPT_NUMBER, REFUEL_DATE, QUANTITY, TOTAL_CHARGE_AMOUNT, GAS_STATION_ID, VEHICLE_ID
                    ) VALUES (
                        1001,
                        TO_DATE('2023-03-01', 'YYYY-MM-DD'),
                        40.00,
                        100.00,
                        (SELECT ID FROM COMPANY WHERE COMPANY_TYPE = 3 AND NAME = 'FuelExpress Station'),
                        (SELECT ID FROM VEHICLE WHERE LICENSE_PLATE = 'BG888HH')
                    );

                    -- Vehicle 2 (SUV - SUV)
                    INSERT INTO REFUEL (
                        FISCAL_RECEIPT_NUMBER, REFUEL_DATE, QUANTITY, TOTAL_CHARGE_AMOUNT, GAS_STATION_ID, VEHICLE_ID
                    ) VALUES (
                        1002,
                        TO_DATE('2023-04-12', 'YYYY-MM-DD'),
                        60.00,
                        144.00,
                        (SELECT ID FROM COMPANY WHERE COMPANY_TYPE = 3 AND NAME = 'GoGas Plus'),
                        (SELECT ID FROM VEHICLE WHERE LICENSE_PLATE = 'BG333SS')
                    );

                    -- Vehicle 3 (Motorcycle - Motorcycle)
                    INSERT INTO REFUEL (
                        FISCAL_RECEIPT_NUMBER, REFUEL_DATE, QUANTITY, TOTAL_CHARGE_AMOUNT, GAS_STATION_ID, VEHICLE_ID
                    ) VALUES (
                        1003,
                        TO_DATE('2023-05-10', 'YYYY-MM-DD'),
                        10.00,
                        30.00,
                        (SELECT ID FROM COMPANY WHERE COMPANY_TYPE = 3 AND NAME = 'Highway Fuel Hub'),
                        (SELECT ID FROM VEHICLE WHERE LICENSE_PLATE = 'BG456MM')
                    );

                    -- Vehicle 4 (Bus - Bus)
                    INSERT INTO REFUEL (
                        FISCAL_RECEIPT_NUMBER, REFUEL_DATE, QUANTITY, TOTAL_CHARGE_AMOUNT, GAS_STATION_ID, VEHICLE_ID
                    ) VALUES (
                        1004,
                        TO_DATE('2023-06-15', 'YYYY-MM-DD'),
                        150.00,
                        390.00,
                        (SELECT ID FROM COMPANY WHERE COMPANY_TYPE = 3 AND NAME = 'FuelExpress Station'),
                        (SELECT ID FROM VEHICLE WHERE LICENSE_PLATE = 'BG111BB')
                    );

                    -- Vehicle 5 (Van - Van)
                    INSERT INTO REFUEL (
                        FISCAL_RECEIPT_NUMBER, REFUEL_DATE, QUANTITY, TOTAL_CHARGE_AMOUNT, GAS_STATION_ID, VEHICLE_ID
                    ) VALUES (
                        1005,
                        TO_DATE('2023-07-20', 'YYYY-MM-DD'),
                        70.00,
                        178.50,
                        (SELECT ID FROM COMPANY WHERE COMPANY_TYPE = 3 AND NAME = 'GoGas Plus'),
                        (SELECT ID FROM VEHICLE WHERE LICENSE_PLATE = 'BG222VV')
                    );

                    COMMIT;

                    SELECT CAST(SYSTIMESTAMP AT TIME ZONE 'UTC' AS DATE) INTO NOW FROM DUAL;

                    INSERT INTO SCRIPT_EXECUTION_HISTORY
                    VALUES (SCRIPT_ID, 'refuel-seed.sql', NOW);
                    COMMIT;
                END IF;
            END;
        END;
    END;
END;
