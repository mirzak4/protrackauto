BEGIN
    DECLARE SCRIPT_ID RAW(16) := '356B23E971EED41BE063143410ACF6FC';
    BEGIN
        DECLARE NOW DATE;
        BEGIN
            DECLARE EXEC_COUNT INT := 0;
            BEGIN
                SELECT COUNT(1) INTO EXEC_COUNT FROM SCRIPT_EXECUTION_HISTORY WHERE ID = SCRIPT_ID;
                IF EXEC_COUNT = 0 THEN

                    DECLARE
                        ADMIN_ROLE_ID INTEGER;
                        ADMIN_USER_ID INTEGER;
                    BEGIN
                        SELECT ID INTO ADMIN_ROLE_ID FROM nbp.nbp_role WHERE NAME = 'NBP04.ADMIN';

                        --INSERT INTO nbp.nbp_user (
                        --    FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID
                        --) VALUES (
                        --    'Admin', 'User', 'nbp04.admin@nbp.com',
                        --    '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq',
                        --    'nbp04.admin', '000-000', TO_DATE('1980-01-01', 'YYYY-MM-DD'), ADMIN_ROLE_ID
                        --);

                        INSERT INTO nbp.nbp_user (
                            FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID
                        ) VALUES (
                            'Admin nbp04', 'Admin nbp04', 'admin.nbp04@nbp.com',
                            '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq',
                            'admin.nbp04', '000-000', TO_DATE('1980-01-01', 'YYYY-MM-DD'), ADMIN_ROLE_ID
                        )
                        RETURNING ID INTO ADMIN_USER_ID;

                        INSERT INTO ACCOUNT_INFO (
                            ACTIVE, LAST_VERIFICATION_CODE, USER_ID
                        ) VALUES (
                            1, 'M9N0P', ADMIN_USER_ID
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

