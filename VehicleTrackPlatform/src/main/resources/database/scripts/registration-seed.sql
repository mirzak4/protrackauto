BEGIN
    DECLARE SCRIPT_ID RAW(16) := '32E453E48E69B120E063143410ACB658';
    BEGIN
        DECLARE NOW DATE;
        BEGIN
            DECLARE EXEC_COUNT INT := 0;
            BEGIN
                SELECT COUNT(1) INTO EXEC_COUNT FROM SCRIPT_EXECUTION_HISTORY WHERE ID = SCRIPT_ID;
                IF EXEC_COUNT = 0 THEN
                    -- Registration 1
                    INSERT INTO REGISTRATION (
                        POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                        ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                    ) VALUES (
                        'POL123456', 1, TO_DATE('2022-05-01', 'YYYY-MM-DD'), TO_DATE('2023-05-01', 'YYYY-MM-DD'), 500.00,
                        50.00, 36, 77
                    );

                    -- Registration 2
                    INSERT INTO REGISTRATION (
                        POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                        ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                    ) VALUES (
                        'POL654321', 2, TO_DATE('2021-07-01', 'YYYY-MM-DD'), TO_DATE('2022-07-01', 'YYYY-MM-DD'), 600.00,
                        30.00, 37, 76
                    );

                    -- Registration 3
                    INSERT INTO REGISTRATION (
                        POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                        ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                    ) VALUES (
                        'POL789012', 3, TO_DATE('2023-01-01', 'YYYY-MM-DD'), TO_DATE('2024-01-01', 'YYYY-MM-DD'), 200.00,
                        20.00, 38, 78
                    );

                    -- Registration 4
                    INSERT INTO REGISTRATION (
                        POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                        ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                    ) VALUES (
                        'POL345678', 1, TO_DATE('2022-03-01', 'YYYY-MM-DD'), TO_DATE('2023-03-01', 'YYYY-MM-DD'), 450.00,
                        40.00, 16, 78
                    );

                    -- Registration 5
                    INSERT INTO REGISTRATION (
                        POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                        ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                    ) VALUES (
                        'POL876543', 2, TO_DATE('2020-09-01', 'YYYY-MM-DD'), TO_DATE('2021-09-01', 'YYYY-MM-DD'), 700.00,
                        70.00, 15, 76
                    );

                    -- Registration 6
                    INSERT INTO REGISTRATION (
                        POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                        ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                    ) VALUES (
                        'POL112233', 3, TO_DATE('2023-03-01', 'YYYY-MM-DD'), TO_DATE('2024-03-01', 'YYYY-MM-DD'), 300.00,
                        30.00, 17, 77
                    );

                    -- Registration 7
                    INSERT INTO REGISTRATION (
                        POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                        ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                    ) VALUES (
                        'POL445566', 1, TO_DATE('2021-12-01', 'YYYY-MM-DD'), TO_DATE('2022-12-01', 'YYYY-MM-DD'), 550.00,
                        60.00, 20, 76
                    );

                    -- Registration 8
                    INSERT INTO REGISTRATION (
                        POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                        ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                    ) VALUES (
                        'POL223344', 2, TO_DATE('2022-08-01', 'YYYY-MM-DD'), TO_DATE('2023-08-01', 'YYYY-MM-DD'), 650.00,
                        40.00, 21, 77
                    );

                    -- Registration 9
                    INSERT INTO REGISTRATION (
                        POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                        ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                    ) VALUES (
                        'POL998877', 3, TO_DATE('2020-06-01', 'YYYY-MM-DD'), TO_DATE('2021-06-01', 'YYYY-MM-DD'), 250.00,
                        25.00, 22, 76
                    );

                    -- Registration 10
                    INSERT INTO REGISTRATION (
                        POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                        ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                    ) VALUES (
                        'POL112233', 1, TO_DATE('2023-05-01', 'YYYY-MM-DD'), TO_DATE('2024-05-01', 'YYYY-MM-DD'), 500.00,
                        50.00, 23, 76
                    );

                    COMMIT;

                    SELECT CAST(SYSTIMESTAMP AT TIME ZONE 'UTC' AS DATE) INTO NOW FROM DUAL;

                    INSERT INTO SCRIPT_EXECUTION_HISTORY
                    VALUES (SCRIPT_ID, 'registration-seed.sql', NOW);
                    COMMIT;
                END IF;
            END;
        END;
    END;
END;
