BEGIN
    DECLARE SCRIPT_ID RAW(16) := '348BEDE2318C8DF2E063143410ACDA3B';
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
                        V_ID19 NUMBER; V_ID20 NUMBER; V_ID21 NUMBER;
                        V_ID22 NUMBER; V_ID23 NUMBER; V_ID24 NUMBER;

                        C_ID1 NUMBER; C_ID2 NUMBER; C_ID3 NUMBER;
                        C_ID4 NUMBER; C_ID5 NUMBER; C_ID6 NUMBER;
                        C_ID7 NUMBER; C_ID8 NUMBER; C_ID9 NUMBER;
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
                        SELECT ID INTO V_ID21 FROM VEHICLE WHERE LICENSE_PLATE = 'BG010FT';
                        SELECT ID INTO V_ID22 FROM VEHICLE WHERE LICENSE_PLATE = 'BG111TR';
                        SELECT ID INTO V_ID23 FROM VEHICLE WHERE LICENSE_PLATE = 'BG212ES';
                        SELECT ID INTO V_ID24 FROM VEHICLE WHERE LICENSE_PLATE = 'BG313MB';

                        SELECT ID INTO C_ID1 FROM COMPANY WHERE NAME = 'SecureCover Insurance' AND COMPANY_TYPE = 1;
                        SELECT ID INTO C_ID2 FROM COMPANY WHERE NAME = 'DriveSafe Insurance' AND COMPANY_TYPE = 1;
                        SELECT ID INTO C_ID3 FROM COMPANY WHERE NAME = 'AutoProtect Group' AND COMPANY_TYPE = 1;
                        SELECT ID INTO C_ID4 FROM COMPANY WHERE NAME = 'Guardian Shield Insurance' AND COMPANY_TYPE = 1;
                        SELECT ID INTO C_ID5 FROM COMPANY WHERE NAME = 'PrimeAuto Insurance' AND COMPANY_TYPE = 1;
                        SELECT ID INTO C_ID6 FROM COMPANY WHERE NAME = 'MotorGuard Assurance' AND COMPANY_TYPE = 1;
                        SELECT ID INTO C_ID7 FROM COMPANY WHERE NAME = 'ShieldWay Insurance' AND COMPANY_TYPE = 1;
                        SELECT ID INTO C_ID8 FROM COMPANY WHERE NAME = 'Zenith Auto Assurance' AND COMPANY_TYPE = 1;
                        SELECT ID INTO C_ID9 FROM COMPANY WHERE NAME = 'Velocity Car Insurance' AND COMPANY_TYPE = 1;

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
                            35.00, V_ID6, C_ID4
                        );

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100007', 3, TO_DATE('2023-10-01', 'YYYY-MM-DD'), TO_DATE('2024-10-01', 'YYYY-MM-DD'), 330.00,
                            25.00, V_ID7, C_ID5
                        );

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100008', 2, TO_DATE('2022-02-10', 'YYYY-MM-DD'), TO_DATE('2023-02-10', 'YYYY-MM-DD'), 410.00,
                            20.00, V_ID8, C_ID6
                        );

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100009', 1, TO_DATE('2020-11-01', 'YYYY-MM-DD'), TO_DATE('2021-11-01', 'YYYY-MM-DD'), 580.00,
                            60.00, V_ID9, C_ID7
                        );

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100010', 3, TO_DATE('2021-08-20', 'YYYY-MM-DD'), TO_DATE('2022-08-20', 'YYYY-MM-DD'), 595.00,
                            18.00, V_ID10, C_ID8
                        );

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100011', 1, TO_DATE('2024-09-11', 'YYYY-MM-DD'), TO_DATE('2025-09-11', 'YYYY-MM-DD'), 720.00,
                            95.00, V_ID11, C_ID9
                        );

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100012', 3, TO_DATE('2024-11-05', 'YYYY-MM-DD'), TO_DATE('2025-11-05', 'YYYY-MM-DD'), 630.00,
                            25.00, V_ID12, C_ID9
                        );

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100013', 2, TO_DATE('2024-10-10', 'YYYY-MM-DD'), TO_DATE('2025-10-10', 'YYYY-MM-DD'), 710.00,
                            20.00, V_ID13, C_ID6
                        );

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100014', 1, TO_DATE('2024-06-12', 'YYYY-MM-DD'), TO_DATE('2025-06-12', 'YYYY-MM-DD'), 780.00,
                            60.00, V_ID14, C_ID7
                        );

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100015', 3, TO_DATE('2024-08-20', 'YYYY-MM-DD'), TO_DATE('2025-08-20', 'YYYY-MM-DD'), 595.00,
                            18.00, V_ID15, C_ID8
                        );

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100016', 1, TO_DATE('2024-09-20', 'YYYY-MM-DD'), TO_DATE('2025-09-20', 'YYYY-MM-DD'), 750.00,
                            55.00, V_ID16, C_ID4
                        );

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100017', 3, TO_DATE('2024-11-25', 'YYYY-MM-DD'), TO_DATE('2025-11-25', 'YYYY-MM-DD'), 530.00,
                            105.00, V_ID17, C_ID5
                        );

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100018', 2, TO_DATE('2024-01-18', 'YYYY-MM-DD'), TO_DATE('2025-01-18', 'YYYY-MM-DD'), 710.00,
                            20.00, V_ID18, C_ID6
                        );

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100019', 1, TO_DATE('2024-04-15', 'YYYY-MM-DD'), TO_DATE('2025-04-15', 'YYYY-MM-DD'), 780.00,
                            60.00, V_ID19, C_ID1
                        );

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100020', 3, TO_DATE('2024-07-20', 'YYYY-MM-DD'), TO_DATE('2025-07-20', 'YYYY-MM-DD'), 695.00,
                            25.00, V_ID20, C_ID2
                        );

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100021', 3, TO_DATE('2024-12-05', 'YYYY-MM-DD'), TO_DATE('2025-12-05', 'YYYY-MM-DD'), 630.00,
                            105.00, V_ID21, C_ID5
                        );

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100022', 2, TO_DATE('2024-12-12', 'YYYY-MM-DD'), TO_DATE('2025-12-12', 'YYYY-MM-DD'), 710.00,
                            20.00, V_ID22, C_ID6
                        );

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100023', 1, TO_DATE('2024-03-11', 'YYYY-MM-DD'), TO_DATE('2025-03-11', 'YYYY-MM-DD'), 780.00,
                            65.00, V_ID23, C_ID1
                        );

                        INSERT INTO REGISTRATION (
                            POLICY_NUMBER, INSURANCE_TYPE, INSURED_FROM, INSURED_UNTIL, INSURANCE_COST,
                            ADDITIONAL_COSTS, VEHICLE_ID, INSURANCE_COMPANY_ID
                        ) VALUES (
                            'POL100024', 3, TO_DATE('2024-02-14', 'YYYY-MM-DD'), TO_DATE('2025-02-14', 'YYYY-MM-DD'), 695.00,
                            55.00, V_ID24, C_ID6
                        );
                    END;

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
