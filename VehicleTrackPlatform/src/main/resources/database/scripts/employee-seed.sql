BEGIN
    DECLARE SCRIPT_ID RAW(16) := '338CA767D6FC9AADE063143410AC4F0E';
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
                        SELECT ID INTO CLAIMS_ADJUSTER_ID FROM nbp.nbp_role
                        WHERE NAME = 'NBP04.CLAIMS_ADJUSTER';

                        INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Adna', 'Mehanovic', 'amehanovic@nbp.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'amehanovic', '111-333', TO_DATE('2000-04-20', 'YYYY-MM-DD'), CLAIMS_ADJUSTER_ID);
                    END;

                    DECLARE FIELD_TECHNICIAN_ID INTEGER;
                    BEGIN
                        -- Employee in Service Provider Company
                        SELECT ID INTO FIELD_TECHNICIAN_ID FROM nbp.nbp_role
                        WHERE NAME = 'NBP04.FIELD_TECHNICIAN';

                        INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                        VALUES ('Mirza', 'Kadric', 'mkadric@nbp.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'mkadric', '111-333', TO_DATE('2000-04-20', 'YYYY-MM-DD'), FIELD_TECHNICIAN_ID);
                    END;

                    DECLARE STATION_MANAGER_ID INTEGER;
                    BEGIN
                        -- Employee in Gas Station Company
                        SELECT ID INTO STATION_MANAGER_ID FROM nbp.nbp_role
                        WHERE NAME = 'NBP04.STATION_MANAGER';

                        INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                        VALUES ('Amina', 'Pandzic', 'apandzic@nbp.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'apandzic', '111-333', TO_DATE('2000-04-20', 'YYYY-MM-DD'), STATION_MANAGER_ID);
                    END;

                    SELECT CAST(SYSTIMESTAMP AT TIME ZONE 'UTC' AS DATE) INTO NOW FROM DUAL;

                    INSERT INTO SCRIPT_EXECUTION_HISTORY
                    VALUES (SCRIPT_ID, 'employee-seed.sql', NOW);
                    COMMIT;
                END IF;
            END;
        END;
    END;
END;