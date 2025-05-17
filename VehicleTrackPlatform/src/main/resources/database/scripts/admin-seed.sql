BEGIN
    DECLARE SCRIPT_ID RAW(16) := '35461D6CA528F760E063143410AC455B';
    BEGIN
        DECLARE NOW DATE;
        BEGIN
            DECLARE EXEC_COUNT INT := 0;
            BEGIN
                SELECT COUNT(1) INTO EXEC_COUNT FROM SCRIPT_EXECUTION_HISTORY WHERE ID = SCRIPT_ID;
                IF EXEC_COUNT = 0 THEN

                    DECLARE
                        ADMIN_ROLE_ID INTEGER;
                    BEGIN
                        SELECT ID INTO ADMIN_ROLE_ID FROM nbp.nbp_role WHERE NAME = 'NBP04.ADMIN';

                        INSERT INTO nbp.nbp_user (
                            FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID
                        ) VALUES (
                            'Admin', 'User', 'nbp04.admin@nbp.com',
                            '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq',
                            'nbp04.admin', '000-000', TO_DATE('1980-01-01', 'YYYY-MM-DD'), ADMIN_ROLE_ID
                        );
                    END;

                    COMMIT;

                    SELECT CAST(SYSTIMESTAMP AT TIME ZONE 'UTC' AS DATE) INTO NOW FROM DUAL;

                    INSERT INTO SCRIPT_EXECUTION_HISTORY
                    VALUES (SCRIPT_ID, 'admin-seed.sql', NOW);
                    COMMIT;
               END IF;
            END;
        END;
    END;
END;

