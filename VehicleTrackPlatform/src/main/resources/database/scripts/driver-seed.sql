BEGIN
    DECLARE SCRIPT_ID RAW(16) := '338CA767D6FD9AADE063143410AC4F0E';
    BEGIN
        DECLARE NOW DATE;
        BEGIN
            DECLARE EXEC_COUNT INT := 0;
            BEGIN
                SELECT COUNT(1) INTO EXEC_COUNT FROM SCRIPT_EXECUTION_HISTORY WHERE ID = SCRIPT_ID;
                IF EXEC_COUNT = 0 THEN
                    --Drivers
                    DECLARE DRIVER_ROLE_ID INTEGER;
                    BEGIN
                        -- Employee in Insurance Company
                        SELECT ID INTO DRIVER_ROLE_ID FROM nbp.nbp_role
                        WHERE NAME = 'NBP04.DRIVER';

                        DECLARE ANIDA_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Anida', 'Nezovic', 'anezovic@nbp.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'anezovic', '111-333', TO_DATE('2000-04-20', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO ANIDA_USER_ID;

                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (ANIDA_USER_ID, '123-456-789', TO_TIMESTAMP('2035-04-20 15:45:30', 'YYYY-MM-DD HH24:MI:SS'));
                        END;

                        DECLARE NADA_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Nada', 'Maric', 'nmaric@nbp.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'nmaric', '111-333', TO_DATE('2000-04-20', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO NADA_USER_ID;

                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (NADA_USER_ID, '5A4-4H6-8P9', TO_TIMESTAMP('2035-04-20 15:45:30', 'YYYY-MM-DD HH24:MI:SS'));
                        END;
                    END;
                    COMMIT;

                    SELECT CAST(SYSTIMESTAMP AT TIME ZONE 'UTC' AS DATE) INTO NOW FROM DUAL;

                    INSERT INTO SCRIPT_EXECUTION_HISTORY
                    VALUES (SCRIPT_ID, 'driver-seed.sql', NOW);
                    COMMIT;
                END IF;
            END;
        END;
    END;
END;