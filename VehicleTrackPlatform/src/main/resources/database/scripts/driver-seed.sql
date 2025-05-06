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
                            VALUES ('Anida', 'Nezovic', 'anezovic@nbp.com', 'pass_123', 'anezovic', '111-333', TO_DATE('2000-04-20', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO ANIDA_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (ANIDA_USER_ID, '123-456-789', TO_TIMESTAMP('2035-04-20 15:45:30', 'YYYY-MM-DD HH24:MI:SS'));
                        END;

                        DECLARE NADA_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Nada', 'Maric', 'nmaric@nbp.com', 'pass_123', 'nmaric', '111-333', TO_DATE('2000-04-20', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO NADA_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (NADA_USER_ID, '5A4-4H6-8P9', TO_TIMESTAMP('2035-04-20 15:45:30', 'YYYY-MM-DD HH24:MI:SS'));
                        END;

                        DECLARE LANA_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Lana', 'Kovac', 'lkovac@nbp.com', 'pass_123', 'lkovac', '111-335', TO_DATE('1992-11-05', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO LANA_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (LANA_USER_ID, '8H9-2J3-4L5', TO_TIMESTAMP('2036-11-05 09:30:00', 'YYYY-MM-DD HH24:MI:SS'));
                        END;

                        DECLARE MARKO_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Marko', 'Petrovic', 'mpetrovic@nbp.com', 'pass_123', 'mpetrovic', '111-336', TO_DATE('1990-02-20', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO MARKO_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (MARKO_USER_ID, '9K1-5M2-7N3', TO_TIMESTAMP('2035-02-20 14:15:00', 'YYYY-MM-DD HH24:MI:SS'));
                        END;

                        DECLARE LEJLA_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Lejla', 'Mustafic', 'lmustafic@nbp.com', 'pass_123', 'lmustafic', '111-339', TO_DATE('1993-03-10', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO LEJLA_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (LEJLA_USER_ID, '4M2-5N7-8O6', TO_TIMESTAMP('2036-03-10 12:00:00', 'YYYY-MM-DD HH24:MI:SS'));
                        END;

                        DECLARE ADNAN_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Adnan', 'Kurtovic', 'akurtovic@nbp.com', 'pass_123', 'akurtovic', '111-340', TO_DATE('1991-09-25', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO ADNAN_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (ADNAN_USER_ID, '5P1-9Q3-2R8', TO_TIMESTAMP('2035-09-25 16:30:00', 'YYYY-MM-DD HH24:MI:SS'));
                        END;

                        DECLARE MERJEM_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Merjem', 'Softic', 'msoftic@nbp.com', 'pass_123', 'msoftic', '111-341', TO_DATE('1994-07-22', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO MERJEM_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (MERJEM_USER_ID, '6S3-4T5-7U8', TO_TIMESTAMP('2036-07-22 13:15:00', 'YYYY-MM-DD HH24:MI:SS'));
                        END;

                        DECLARE NERMIN_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Nermin', 'Begovic', 'nbegovic@nbp.com', 'pass_123', 'nbegovic', '111-342', TO_DATE('1996-01-30', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO NERMIN_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (NERMIN_USER_ID, '7V6-8W9-1X2', TO_TIMESTAMP('2035-01-30 09:00:00', 'YYYY-MM-DD HH24:MI:SS'));
                        END;

                        DECLARE DINO_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Dino', 'Radic', 'dradic@nbp.com', 'pass_123', 'dradic', '111-343', TO_DATE('1993-05-14', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO DINO_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (DINO_USER_ID, '8Y2-3Z4-5A6', TO_TIMESTAMP('2037-05-14 11:45:00', 'YYYY-MM-DD HH24:MI:SS'));
                        END;

                        DECLARE INES_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Ines', 'Karacic', 'ikaracic@nbp.com', 'pass_123', 'ikaracic', '111-344', TO_DATE('1992-10-02', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO INES_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (INES_USER_ID, '9B7-0C1-2D3', TO_TIMESTAMP('2036-10-02 10:30:00', 'YYYY-MM-DD HH24:MI:SS'));
                        END;

                        DECLARE JOHN_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('John', 'Doe', 'jdoe@nbp.com', 'pass_123', 'jdoe', '111-345', TO_DATE('1985-01-15', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO JOHN_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (JOHN_USER_ID, 'J1D-2E3-4F5', TO_TIMESTAMP('2035-01-15 10:00:00', 'YYYY-MM-DD HH24:MI:SS'));
                        END;

                        DECLARE JANE_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Jane', 'Smith', 'jsmith@nbp.com', 'pass_123', 'jsmith', '111-346', TO_DATE('1990-05-22', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO JANE_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (JANE_USER_ID, 'J2S-3M4-5N6', TO_TIMESTAMP('2036-05-22 09:30:00', 'YYYY-MM-DD HH24:MI:SS'));
                        END;

                        DECLARE MICHAEL_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Michael', 'Brown', 'mbrown@nbp.com', 'pass_123', 'mbrown', '111-347', TO_DATE('1988-03-10', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO MICHAEL_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (MICHAEL_USER_ID, 'M3B-4R5-6O7', TO_TIMESTAMP('2035-03-10 11:45:00', 'YYYY-MM-DD HH24:MI:SS'));
                        END;

                        DECLARE EMILY_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Emily', 'Johnson', 'ejohnson@nbp.com', 'pass_123', 'ejohnson', '111-348', TO_DATE('1992-07-18', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO EMILY_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (EMILY_USER_ID, 'E4J-5H6-7P8', TO_TIMESTAMP('2036-07-18 08:15:00', 'YYYY-MM-DD HH24:MI:SS'));
                        END;

                        DECLARE DAVID_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('David', 'Wilson', 'dwilson@nbp.com', 'pass_123', 'dwilson', '111-349', TO_DATE('1987-11-30', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO DAVID_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (DAVID_USER_ID, 'D5W-6I7-8Q9', TO_TIMESTAMP('2035-11-30 14:00:00', 'YYYY-MM-DD HH24:MI:SS'));
                        END;

                        DECLARE LAURA_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Laura', 'Miller', 'lmiller@nbp.com', 'pass_123', 'lmiller', '111-350', TO_DATE('1991-02-25', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO LAURA_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (LAURA_USER_ID, 'L6M-7E8-9R0', TO_TIMESTAMP('2036-02-25 12:30:00', 'YYYY-MM-DD HH24:MI:SS'));
                        END;

                        DECLARE DANIEL_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Daniel', 'Anderson', 'danderson@nbp.com', 'pass_123', 'danderson', '111-351', TO_DATE('1989-09-05', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO DANIEL_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (DANIEL_USER_ID, 'D7A-8N9-0S1', TO_TIMESTAMP('2035-09-05 16:45:00', 'YYYY-MM-DD HH24:MI:SS'));
                        END;

                        DECLARE OLIVIA_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Olivia', 'Thomas', 'othomas@nbp.com', 'pass_123', 'othomas', '111-352', TO_DATE('1993-12-12', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO OLIVIA_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (OLIVIA_USER_ID, 'O8T-9H0-1U2', TO_TIMESTAMP('2036-12-12 10:15:00', 'YYYY-MM-DD HH24:MI:SS'));
                        END;

                        DECLARE JAMES_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('James', 'Taylor', 'jtaylor@nbp.com', 'pass_123', 'jtaylor', '111-353', TO_DATE('1986-04-03', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO JAMES_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (JAMES_USER_ID, 'J9T-0A1-2V3', TO_TIMESTAMP('2035-04-03 13:15:00', 'YYYY-MM-DD HH24:MI:SS'));
                        END;

                        DECLARE SOPHIA_USER_ID INTEGER;
                        BEGIN
                            INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                            VALUES ('Sophia', 'Moore', 'smoore@nbp.com', 'pass_123', 'smoore', '111-354', TO_DATE('1994-08-27', 'YYYY-MM-DD'), DRIVER_ROLE_ID)
                            RETURNING ID INTO SOPHIA_USER_ID;
                            INSERT INTO NBP04.DRIVER (USER_ID, LICENSE_NUMBER, LICENSE_EXPIRY)
                            VALUES (SOPHIA_USER_ID, 'S0M-1O2-3W4', TO_TIMESTAMP('2036-08-27 09:45:00', 'YYYY-MM-DD HH24:MI:SS'));
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