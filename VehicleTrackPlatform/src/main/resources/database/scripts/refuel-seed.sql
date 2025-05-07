BEGIN
    DECLARE SCRIPT_ID RAW(16) := '348A8A0E30F98770E063143410ACB4A1';
    BEGIN
        DECLARE NOW DATE;
        BEGIN
            DECLARE EXEC_COUNT INT := 0;
            BEGIN

                SELECT COUNT(1) INTO EXEC_COUNT FROM SCRIPT_EXECUTION_HISTORY WHERE ID = SCRIPT_ID;
                IF EXEC_COUNT = 0 THEN

                    -- Vehicle 1 (Travel Car - Hatchback)
                    --INSERT INTO REFUEL (FISCAL_RECEIPT_NUMBER, REFUEL_DATE, QUANTITY, TOTAL_CHARGE_AMOUNT, GAS_STATION_ID, VEHICLE_ID)
                    --VALUES (
                    --    1001,
                    --    TO_DATE('2023-03-01', 'YYYY-MM-DD'),
                    --    40.00,
                    --    100.00,
                    --    (SELECT ID FROM COMPANY WHERE COMPANY_TYPE = 3 AND NAME = 'FuelExpress Station'),
                    --    (SELECT ID FROM VEHICLE WHERE LICENSE_PLATE = 'BG888HH')
                    --);

                    -- Vehicle 2 (SUV - SUV)
                    --INSERT INTO REFUEL (FISCAL_RECEIPT_NUMBER, REFUEL_DATE, QUANTITY, TOTAL_CHARGE_AMOUNT, GAS_STATION_ID, VEHICLE_ID)
                    --VALUES (
                    --    1002,
                    --    TO_DATE('2023-04-12', 'YYYY-MM-DD'),
                    --    60.00,
                    --    144.00,
                    --    (SELECT ID FROM COMPANY WHERE COMPANY_TYPE = 3 AND NAME = 'GoGas Plus'),
                    --    (SELECT ID FROM VEHICLE WHERE LICENSE_PLATE = 'BG333SS')
                    --);

                    -- Vehicle 3 (Motorcycle - Motorcycle)
                    --INSERT INTO REFUEL (FISCAL_RECEIPT_NUMBER, REFUEL_DATE, QUANTITY, TOTAL_CHARGE_AMOUNT, GAS_STATION_ID, VEHICLE_ID)
                    --VALUES (
                    --    1003,
                    --    TO_DATE('2023-05-10', 'YYYY-MM-DD'),
                    --    10.00,
                    --    30.00,
                    --    (SELECT ID FROM COMPANY WHERE COMPANY_TYPE = 3 AND NAME = 'Highway Fuel Hub'),
                    --    (SELECT ID FROM VEHICLE WHERE LICENSE_PLATE = 'BG456MM')
                    --);

                    -- Vehicle 4 (Bus - Bus)
                    --INSERT INTO REFUEL (FISCAL_RECEIPT_NUMBER, REFUEL_DATE, QUANTITY, TOTAL_CHARGE_AMOUNT, GAS_STATION_ID, VEHICLE_ID)
                    --VALUES (
                    --   1004,
                    --    TO_DATE('2023-06-15', 'YYYY-MM-DD'),
                    --    150.00,
                    --    390.00,
                    --    (SELECT ID FROM COMPANY WHERE COMPANY_TYPE = 3 AND NAME = 'FuelExpress Station'),
                    --    (SELECT ID FROM VEHICLE WHERE LICENSE_PLATE = 'BG111BB')
                    --);

                    -- Vehicle 5 (Van - Van)
                    --INSERT INTO REFUEL (FISCAL_RECEIPT_NUMBER, REFUEL_DATE, QUANTITY, TOTAL_CHARGE_AMOUNT, GAS_STATION_ID, VEHICLE_ID)
                    --VALUES (
                    --    1005,
                    --    TO_DATE('2023-07-20', 'YYYY-MM-DD'),
                    --    70.00,
                    --    178.50,
                    --    (SELECT ID FROM COMPANY WHERE COMPANY_TYPE = 3 AND NAME = 'GoGas Plus'),
                    --    (SELECT ID FROM VEHICLE WHERE LICENSE_PLATE = 'BG222VV')
                    --);

                    INSERT INTO REFUEL (FISCAL_RECEIPT_NUMBER, REFUEL_DATE, QUANTITY, TOTAL_CHARGE_AMOUNT, GAS_STATION_ID, VEHICLE_ID)
                    VALUES (
                        1006,
                        TO_DATE('2023-08-05','YYYY-MM-DD'),
                        55.00,
                        151.25,
                        (SELECT ID FROM COMPANY WHERE COMPANY_TYPE=3 AND NAME='PetroMax Station'),
                        (SELECT ID FROM VEHICLE WHERE LICENSE_PLATE='BG333SS')
                    );

                    INSERT INTO REFUEL (FISCAL_RECEIPT_NUMBER, REFUEL_DATE, QUANTITY, TOTAL_CHARGE_AMOUNT, GAS_STATION_ID, VEHICLE_ID)
                    VALUES (
                        1007,
                        TO_DATE('2023-08-18','YYYY-MM-DD'),
                        35.00,
                        78.40,
                        (SELECT ID FROM COMPANY WHERE COMPANY_TYPE=3 AND NAME='TurboCharge Fuel'),
                        (SELECT ID FROM VEHICLE WHERE LICENSE_PLATE='BG444PP')
                    );

                    INSERT INTO REFUEL (FISCAL_RECEIPT_NUMBER, REFUEL_DATE, QUANTITY, TOTAL_CHARGE_AMOUNT, GAS_STATION_ID, VEHICLE_ID)
                    VALUES (
                        1008,
                        TO_DATE('2023-09-02','YYYY-MM-DD'),
                        90.00,
                        234.00,
                        (SELECT ID FROM COMPANY WHERE COMPANY_TYPE=3 AND NAME='EcoFuel Stop'),
                        (SELECT ID FROM VEHICLE WHERE LICENSE_PLATE='BG555TT')
                    );

                    INSERT INTO REFUEL (FISCAL_RECEIPT_NUMBER, REFUEL_DATE, QUANTITY, TOTAL_CHARGE_AMOUNT, GAS_STATION_ID, VEHICLE_ID)
                    VALUES (
                        1009,
                        TO_DATE('2023-09-15','YYYY-MM-DD'),
                        120.00,
                        360.00,
                        (SELECT ID FROM COMPANY WHERE COMPANY_TYPE=3 AND NAME='RoadRunner Gas'),
                        (SELECT ID FROM VEHICLE WHERE LICENSE_PLATE='BG666SS')
                    );

                    INSERT INTO REFUEL (FISCAL_RECEIPT_NUMBER, REFUEL_DATE, QUANTITY, TOTAL_CHARGE_AMOUNT, GAS_STATION_ID, VEHICLE_ID)
                    VALUES (
                        1010,
                        TO_DATE('2023-10-01','YYYY-MM-DD'),
                        25.00,
                        62.50,
                        (SELECT ID FROM COMPANY WHERE COMPANY_TYPE=3 AND NAME='FuelExpress Station'),
                        (SELECT ID FROM VEHICLE WHERE LICENSE_PLATE='BG777BB')
                    );

                    INSERT INTO REFUEL (FISCAL_RECEIPT_NUMBER, REFUEL_DATE, QUANTITY, TOTAL_CHARGE_AMOUNT, GAS_STATION_ID, VEHICLE_ID)
                    VALUES (
                        1011,
                        TO_DATE('2023-10-10','YYYY-MM-DD'),
                        80.00,
                        224.00,
                        (SELECT ID FROM COMPANY WHERE COMPANY_TYPE=3 AND NAME='GoGas Plus'),
                        (SELECT ID FROM VEHICLE WHERE LICENSE_PLATE='BG888HH')
                    );

                    INSERT INTO REFUEL (FISCAL_RECEIPT_NUMBER, REFUEL_DATE, QUANTITY, TOTAL_CHARGE_AMOUNT, GAS_STATION_ID, VEHICLE_ID)
                    VALUES (
                        1012,
                        TO_DATE('2023-10-22','YYYY-MM-DD'),
                        45.00,
                        121.50,
                        (SELECT ID FROM COMPANY WHERE COMPANY_TYPE=3 AND NAME='Highway Fuel Hub'),
                        (SELECT ID FROM VEHICLE WHERE LICENSE_PLATE='BG999CC')
                     );

                    INSERT INTO REFUEL (FISCAL_RECEIPT_NUMBER, REFUEL_DATE, QUANTITY, TOTAL_CHARGE_AMOUNT, GAS_STATION_ID, VEHICLE_ID)
                    VALUES (
                        1013,
                        TO_DATE('2023-11-05','YYYY-MM-DD'),
                        100.00,
                        300.00,
                        (SELECT ID FROM COMPANY WHERE COMPANY_TYPE=3 AND NAME='PetroMax Station'),
                        (SELECT ID FROM VEHICLE WHERE LICENSE_PLATE='BG101CV')
                    );

                    INSERT INTO REFUEL (FISCAL_RECEIPT_NUMBER, REFUEL_DATE, QUANTITY, TOTAL_CHARGE_AMOUNT, GAS_STATION_ID, VEHICLE_ID)
                    VALUES (
                        1014,
                        TO_DATE('2023-11-18','YYYY-MM-DD'),
                        60.00,
                        174.00,
                        (SELECT ID FROM COMPANY WHERE COMPANY_TYPE=3 AND NAME='TurboCharge Fuel'),
                        (SELECT ID FROM VEHICLE WHERE LICENSE_PLATE='BG202WG')
                    );

                    INSERT INTO REFUEL (FISCAL_RECEIPT_NUMBER, REFUEL_DATE, QUANTITY, TOTAL_CHARGE_AMOUNT, GAS_STATION_ID, VEHICLE_ID)
                    VALUES (
                        1015,
                        TO_DATE('2023-12-01','YYYY-MM-DD'),
                        30.00,
                        81.00,
                        (SELECT ID FROM COMPANY WHERE COMPANY_TYPE=3 AND NAME='EcoFuel Stop'),
                        (SELECT ID FROM VEHICLE WHERE LICENSE_PLATE='BG303MV')
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
