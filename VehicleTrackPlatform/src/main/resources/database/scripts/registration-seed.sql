BEGIN
    DECLARE SCRIPT_ID RAW(16) := '339E6785D9EE1514E063143410AC6F65';
    BEGIN
        DECLARE NOW DATE;
        BEGIN
            DECLARE EXEC_COUNT INT := 0;
            BEGIN
                SELECT COUNT(1) INTO EXEC_COUNT FROM SCRIPT_EXECUTION_HISTORY WHERE ID = SCRIPT_ID;
                IF EXEC_COUNT = 0 THEN
                    DECLARE
                        V_ID1 NUMBER;
                        V_ID2 NUMBER;
                        V_ID3 NUMBER;
                        V_ID4 NUMBER;
                        V_ID5 NUMBER;
                        C_ID1 NUMBER;
                        C_ID2 NUMBER;
                        C_ID3 NUMBER;
                    BEGIN
                        SELECT ID INTO V_ID1 FROM VEHICLE WHERE LICENSE_PLATE = 'BG123AA';
                        SELECT ID INTO V_ID2 FROM VEHICLE WHERE LICENSE_PLATE = 'BG789TT';
                        SELECT ID INTO V_ID3 FROM VEHICLE WHERE LICENSE_PLATE = 'BG456MM';
                        SELECT ID INTO V_ID4 FROM VEHICLE WHERE LICENSE_PLATE = 'BG111BB';
                        SELECT ID INTO V_ID5 FROM VEHICLE WHERE LICENSE_PLATE = 'BG222VV';

                        SELECT ID INTO C_ID1 FROM COMPANY WHERE NAME = 'SecureCover Insurance';
                        SELECT ID INTO C_ID2 FROM COMPANY WHERE NAME = 'DriveSafe Insurance';
                        SELECT ID INTO C_ID3 FROM COMPANY WHERE NAME = 'AutoProtect Group';

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100001', 1, TO_DATE('2022-05-01', 'YYYY-MM-DD'), TO_DATE('2023-05-01', 'YYYY-MM-DD'), 500.00,
                            50.00, V_ID1, C_ID1
                        );

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100002', 2, TO_DATE('2021-07-01', 'YYYY-MM-DD'), TO_DATE('2022-07-01', 'YYYY-MM-DD'), 600.00,
                            30.00, V_ID2, C_ID2
                        );

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100003', 3, TO_DATE('2023-01-01', 'YYYY-MM-DD'), TO_DATE('2024-01-01', 'YYYY-MM-DD'), 200.00,
                            20.00, V_ID3, C_ID3
                        );

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100004', 1, TO_DATE('2022-03-01', 'YYYY-MM-DD'), TO_DATE('2023-03-01', 'YYYY-MM-DD'), 450.00,
                            40.00, V_ID4, C_ID3
                        );

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100005', 2, TO_DATE('2020-09-01', 'YYYY-MM-DD'), TO_DATE('2021-09-01', 'YYYY-MM-DD'), 700.00,
                            70.00, V_ID5, C_ID2
                        );

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100006', 1, TO_DATE('2021-06-15', 'YYYY-MM-DD'), TO_DATE('2022-06-15', 'YYYY-MM-DD'), 520.00,
                            35.00, V_ID1, C_ID2
                        );

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100007', 3, TO_DATE('2023-10-01', 'YYYY-MM-DD'), TO_DATE('2024-10-01', 'YYYY-MM-DD'), 330.00,
                            25.00, V_ID2, C_ID1
                        );

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100008', 2, TO_DATE('2022-02-10', 'YYYY-MM-DD'), TO_DATE('2023-02-10', 'YYYY-MM-DD'), 410.00,
                            20.00, V_ID3, C_ID2
                        );

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100009', 1, TO_DATE('2020-11-01', 'YYYY-MM-DD'), TO_DATE('2021-11-01', 'YYYY-MM-DD'), 580.00,
                            60.00, V_ID4, C_ID1
                        );

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100010', 3, TO_DATE('2021-08-20', 'YYYY-MM-DD'), TO_DATE('2022-08-20', 'YYYY-MM-DD'), 295.00,
                            18.00, V_ID5, C_ID3
                        );
                        COMMIT;

                        SELECT CAST(SYSTIMESTAMP AT TIME ZONE 'UTC' AS DATE) INTO NOW FROM DUAL;

                        INSERT INTO SCRIPT_EXECUTION_HISTORY
                        VALUES (SCRIPT_ID, 'registration-seed.sql', NOW);

                        COMMIT;
                    END;
                END IF;
            END;
        END;
    END;
END;
