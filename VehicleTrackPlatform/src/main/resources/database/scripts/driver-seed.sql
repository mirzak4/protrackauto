BEGIN
    DECLARE SCRIPT_ID RAW(16) := '348AE3C086E08A71E063143410AC9AA3';
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

                            INSERT INTO ACCOUNT_INFO (ACTIVE, LAST_VERIFICATION_CODE, USER_ID) VALUES (1, 'A1B2C', ANIDA_USER_ID);
                        END;

                        DECLARE NADA_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Nada', 'Maric', 'nmaric@nbp.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'nmaric', '111-333', TO_DATE('2000-04-20', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO NADA_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (NADA_USER_ID, '5A4-4H6-8P9', TO_TIMESTAMP('2035-04-20 15:45:30', 'YYYY-MM-DD HH24:MI:SS'));

                            INSERT INTO ACCOUNT_INFO (ACTIVE, LAST_VERIFICATION_CODE, USER_ID) VALUES (1, 'D3E4F', NADA_USER_ID);
                        END;

                        DECLARE LANA_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Lana', 'Kovac', 'lkovac@nbp.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'lkovac', '111-335', TO_DATE('1992-11-05', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO LANA_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (LANA_USER_ID, '8H9-2J3-4L5', TO_TIMESTAMP('2036-11-05 09:30:00', 'YYYY-MM-DD HH24:MI:SS'));

                            INSERT INTO ACCOUNT_INFO (ACTIVE, LAST_VERIFICATION_CODE, USER_ID) VALUES (1, 'G5H6I', LANA_USER_ID);
                        END;

                        DECLARE MARKO_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Marko', 'Petrovic', 'mpetrovic@nbp.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'mpetrovic', '111-336', TO_DATE('1990-02-20', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO MARKO_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (MARKO_USER_ID, '9K1-5M2-7N3', TO_TIMESTAMP('2035-02-20 14:15:00', 'YYYY-MM-DD HH24:MI:SS'));

                            INSERT INTO ACCOUNT_INFO (ACTIVE, LAST_VERIFICATION_CODE, USER_ID) VALUES (1, 'J7K8L', MARKO_USER_ID);
                        END;

                        DECLARE LEJLA_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Lejla', 'Mustafic', 'lmustafic@nbp.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'lmustafic', '111-339', TO_DATE('1993-03-10', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO LEJLA_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (LEJLA_USER_ID, '4M2-5N7-8O6', TO_TIMESTAMP('2036-03-10 12:00:00', 'YYYY-MM-DD HH24:MI:SS'));

                            INSERT INTO ACCOUNT_INFO (ACTIVE, LAST_VERIFICATION_CODE, USER_ID) VALUES (1, 'M9N0P', LEJLA_USER_ID);
                        END;

                        DECLARE ADNAN_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Adnan', 'Kurtovic', 'akurtovic@nbp.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'akurtovic', '111-340', TO_DATE('1991-09-25', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO ADNAN_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (ADNAN_USER_ID, '5P1-9Q3-2R8', TO_TIMESTAMP('2035-09-25 16:30:00', 'YYYY-MM-DD HH24:MI:SS'));

                            INSERT INTO ACCOUNT_INFO (ACTIVE, LAST_VERIFICATION_CODE, USER_ID) VALUES (1, 'Q1R2S', ADNAN_USER_ID);
                        END;

                        DECLARE MERJEM_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Merjem', 'Softic', 'msoftic@nbp.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'msoftic', '111-341', TO_DATE('1994-07-22', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO MERJEM_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (MERJEM_USER_ID, '6S3-4T5-7U8', TO_TIMESTAMP('2036-07-22 13:15:00', 'YYYY-MM-DD HH24:MI:SS'));

                            INSERT INTO ACCOUNT_INFO (ACTIVE, LAST_VERIFICATION_CODE, USER_ID) VALUES (1, 'T3U4V', MERJEM_USER_ID);
                        END;

                        DECLARE NERMIN_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Nermin', 'Begovic', 'nbegovic@nbp.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'nbegovic', '111-342', TO_DATE('1996-01-30', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO NERMIN_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (NERMIN_USER_ID, '7V6-8W9-1X2', TO_TIMESTAMP('2035-01-30 09:00:00', 'YYYY-MM-DD HH24:MI:SS'));

                            INSERT INTO ACCOUNT_INFO (ACTIVE, LAST_VERIFICATION_CODE, USER_ID) VALUES (1, 'W5X6Y', NERMIN_USER_ID);
                        END;

                        DECLARE DINO_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Dino', 'Radic', 'dradic@nbp.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'dradic', '111-343', TO_DATE('1993-05-14', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO DINO_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (DINO_USER_ID, '8Y2-3Z4-5A6', TO_TIMESTAMP('2037-05-14 11:45:00', 'YYYY-MM-DD HH24:MI:SS'));

                            INSERT INTO ACCOUNT_INFO (ACTIVE, LAST_VERIFICATION_CODE, USER_ID) VALUES (1, 'Z7A8B', DINO_USER_ID);
                        END;

                        DECLARE INES_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Ines', 'Karacic', 'ikaracic@nbp.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'ikaracic', '111-344', TO_DATE('1992-10-02', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO INES_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (INES_USER_ID, '9B7-0C1-2D3', TO_TIMESTAMP('2036-10-02 10:30:00', 'YYYY-MM-DD HH24:MI:SS'));

                            INSERT INTO ACCOUNT_INFO (ACTIVE, LAST_VERIFICATION_CODE, USER_ID) VALUES (1, 'C9D0E', INES_USER_ID);
                        END;

                        DECLARE JANE_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Jane', 'Smith', 'jsmith@nbp.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'jsmith', '111-346', TO_DATE('1990-05-22', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO JANE_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (JANE_USER_ID, 'J2S-3M4-5N6', TO_TIMESTAMP('2036-05-22 09:30:00', 'YYYY-MM-DD HH24:MI:SS'));

                            INSERT INTO ACCOUNT_INFO (ACTIVE, LAST_VERIFICATION_CODE, USER_ID) VALUES (1, 'F1G2H', JANE_USER_ID);
                        END;

                        DECLARE MICHAEL_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Michael', 'Brown', 'mbrown@nbp.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'mbrown', '111-347', TO_DATE('1988-03-10', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO MICHAEL_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (MICHAEL_USER_ID, 'M3B-4R5-6O7', TO_TIMESTAMP('2035-03-10 11:45:00', 'YYYY-MM-DD HH24:MI:SS'));

                            INSERT INTO ACCOUNT_INFO (ACTIVE, LAST_VERIFICATION_CODE, USER_ID) VALUES (1, 'I3J4K', MICHAEL_USER_ID);
                        END;

                        DECLARE EMILY_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Emily', 'Johnson', 'ejohnson@nbp.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'ejohnson', '111-348', TO_DATE('1992-07-18', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO EMILY_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (EMILY_USER_ID, 'E4J-5H6-7P8', TO_TIMESTAMP('2036-07-18 08:15:00', 'YYYY-MM-DD HH24:MI:SS'));

                            INSERT INTO ACCOUNT_INFO (ACTIVE, LAST_VERIFICATION_CODE, USER_ID) VALUES (1, 'L5M6N', EMILY_USER_ID);
                        END;

                        DECLARE DAVID_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('David', 'Wilson', 'dwilson@nbp.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'dwilson', '111-349', TO_DATE('1987-11-30', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO DAVID_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (DAVID_USER_ID, 'D5W-6I7-8Q9', TO_TIMESTAMP('2035-11-30 14:00:00', 'YYYY-MM-DD HH24:MI:SS'));

                            INSERT INTO ACCOUNT_INFO (ACTIVE, LAST_VERIFICATION_CODE, USER_ID) VALUES (1, 'O7P8Q', DAVID_USER_ID);
                        END;

                        DECLARE LAURA_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Laura', 'Miller', 'lmiller@nbp.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'lmiller', '111-350', TO_DATE('1991-02-25', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO LAURA_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (LAURA_USER_ID, 'L6M-7E8-9R0', TO_TIMESTAMP('2036-02-25 12:30:00', 'YYYY-MM-DD HH24:MI:SS'));

                            INSERT INTO ACCOUNT_INFO (ACTIVE, LAST_VERIFICATION_CODE, USER_ID) VALUES (1, 'R9S0T', LAURA_USER_ID);
                        END;

                        DECLARE DANIEL_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Daniel', 'Anderson', 'danderson@nbp.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'danderson', '111-351', TO_DATE('1989-09-05', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO DANIEL_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (DANIEL_USER_ID, 'D7A-8N9-0S1', TO_TIMESTAMP('2035-09-05 16:45:00', 'YYYY-MM-DD HH24:MI:SS'));

                            INSERT INTO ACCOUNT_INFO (ACTIVE, LAST_VERIFICATION_CODE, USER_ID) VALUES (1, 'U1V2W', DANIEL_USER_ID);
                        END;

                        DECLARE OLIVIA_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Olivia', 'Thomas', 'othomas@nbp.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'othomas', '111-352', TO_DATE('1993-12-12', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO OLIVIA_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (OLIVIA_USER_ID, 'O8T-9H0-1U2', TO_TIMESTAMP('2036-12-12 10:15:00', 'YYYY-MM-DD HH24:MI:SS'));

                            INSERT INTO ACCOUNT_INFO (ACTIVE, LAST_VERIFICATION_CODE, USER_ID) VALUES (1, 'X3Y4Z', OLIVIA_USER_ID);
                        END;

                        DECLARE JAMES_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('James', 'Taylor', 'jtaylor@nbp.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'jtaylor', '111-353', TO_DATE('1986-04-03', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO JAMES_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (JAMES_USER_ID, 'J9T-0A1-2V3', TO_TIMESTAMP('2035-04-03 13:15:00', 'YYYY-MM-DD HH24:MI:SS'));

                            INSERT INTO ACCOUNT_INFO (ACTIVE, LAST_VERIFICATION_CODE, USER_ID) VALUES (1, 'A5B6C', JAMES_USER_ID);
                        END;

                        DECLARE SOPHIA_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Sophia', 'Moore', 'smoore@nbp.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'smoore', '111-354', TO_DATE('1994-08-27', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO SOPHIA_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (SOPHIA_USER_ID, 'S0M-1O2-3W4', TO_TIMESTAMP('2036-08-27 09:45:00', 'YYYY-MM-DD HH24:MI:SS'));

                            INSERT INTO ACCOUNT_INFO (ACTIVE, LAST_VERIFICATION_CODE, USER_ID) VALUES (1, 'D7E8F', SOPHIA_USER_ID);
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