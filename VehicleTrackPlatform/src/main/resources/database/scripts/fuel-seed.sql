BEGIN
    DECLARE SCRIPT_ID RAW(16) := '348A4BC588A48141E063143410ACF2D7';
    BEGIN
        DECLARE NOW DATE;
        BEGIN
            DECLARE EXEC_COUNT INT := 0;
            BEGIN
                SELECT COUNT(1) INTO EXEC_COUNT FROM SCRIPT_EXECUTION_HISTORY WHERE ID = SCRIPT_ID;
                IF EXEC_COUNT = 0 THEN
                    --INSERT INTO FUEL (NAME) VALUES ('Petrol');
                    --INSERT INTO FUEL (NAME) VALUES ('Diesel');
                    --INSERT INTO FUEL (NAME) VALUES ('LPG');
                    --INSERT INTO FUEL (NAME) VALUES ('CNG');
                    --INSERT INTO FUEL (NAME) VALUES ('Electric');
                    INSERT INTO FUEL (NAME) VALUES ('Hydrogen');
                    INSERT INTO FUEL (NAME) VALUES ('Biodiesel');
                    INSERT INTO FUEL (NAME) VALUES ('Ethanol');
                    INSERT INTO FUEL (NAME) VALUES ('Methanol');
                    INSERT INTO FUEL (NAME) VALUES ('Propane');
                    INSERT INTO FUEL (NAME) VALUES ('Butane');
                    INSERT INTO FUEL (NAME) VALUES ('Kerosene');
                    INSERT INTO FUEL (NAME) VALUES ('NaturalGas');
                    INSERT INTO FUEL (NAME) VALUES ('E85');
                    INSERT INTO FUEL (NAME) VALUES ('Bioethanol');

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
