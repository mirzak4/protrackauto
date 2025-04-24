BEGIN
    DECLARE SCRIPT_ID RAW(16) := '33880B3146637FE5E063143410AC812D';
    BEGIN
        DECLARE NOW DATE;
        BEGIN
            DECLARE EXEC_COUNT INT := 0;
            BEGIN
                SELECT COUNT(1) INTO EXEC_COUNT FROM SCRIPT_EXECUTION_HISTORY WHERE ID = SCRIPT_ID;
                IF EXEC_COUNT = 0 THEN
                    INSERT INTO FUEL (NAME) VALUES ('Petrol');
                    INSERT INTO FUEL (NAME) VALUES ('Diesel');
                    INSERT INTO FUEL (NAME) VALUES ('LPG');
                    INSERT INTO FUEL (NAME) VALUES ('CNG');
                    INSERT INTO FUEL (NAME) VALUES ('Electric');

                    COMMIT;

                    SELECT CAST(SYSTIMESTAMP AT TIME ZONE 'UTC' AS DATE) INTO NOW FROM DUAL;

                    INSERT INTO SCRIPT_EXECUTION_HISTORY
                    VALUES (SCRIPT_ID, 'fuel-seed.sql', NOW);
                    COMMIT;
                END IF;
            END;
        END;
    END;
END;
