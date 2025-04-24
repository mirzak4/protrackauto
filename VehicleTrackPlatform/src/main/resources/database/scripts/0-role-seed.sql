BEGIN
    DECLARE SCRIPT_ID RAW(16) := '338CA767D6F69AADE063143410AC4F0E';
    BEGIN
        DECLARE NOW DATE;
        BEGIN
            DECLARE EXEC_COUNT INT := 0;
            BEGIN
                SELECT COUNT(1) INTO EXEC_COUNT FROM SCRIPT_EXECUTION_HISTORY WHERE ID = SCRIPT_ID;
                IF EXEC_COUNT = 0 THEN

                    DECLARE CLAIMS_ADJUSTER_ID INTEGER;
                    BEGIN
                        -- Employee in Insurance Company
                        INSERT INTO nbp.nbp_role (NAME) VALUES
                            ('NBP04.CLAIMS_ADJUSTER') RETURNING id INTO CLAIMS_ADJUSTER_ID;
                    END;

                    DECLARE FIELD_TECHNICIAN_ID INTEGER;
                    BEGIN
                        -- Employee in Service Provider Company
                        INSERT INTO nbp.nbp_role (NAME) VALUES
                            ('NBP04.FIELD_TECHNICIAN') RETURNING id INTO FIELD_TECHNICIAN_ID;
                    END;

                    DECLARE STATION_MANAGER_ID INTEGER;
                    BEGIN
                        -- Employee in Gas Station Company
                        INSERT INTO nbp.nbp_role (NAME) VALUES
                            ('NBP04.STATION_MANAGER') RETURNING id INTO STATION_MANAGER_ID;
                    END;

                    DECLARE DRIVER_ID INTEGER;
                    BEGIN
                        -- Employee in Gas Station Company
                        INSERT INTO nbp.nbp_role (NAME) VALUES
                            ('NBP04.DRIVER') RETURNING id INTO DRIVER_ID;

--                         INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
--                         VALUES ('Amina', 'Pandzic', 'apandzic@nbp.com', 'pass_123', 'apandzic', '111-333', TO_DATE('2000-04-20', 'YYYY-MM-DD'), STATION_MANAGER_ID);
                    END;

                    SELECT CAST(SYSTIMESTAMP AT TIME ZONE 'UTC' AS DATE) INTO NOW FROM DUAL;

                    INSERT INTO SCRIPT_EXECUTION_HISTORY
                    VALUES (SCRIPT_ID, '0-role-seed.sql', NOW);
                    COMMIT;
                END IF;
            END;
        END;
    END;
END;