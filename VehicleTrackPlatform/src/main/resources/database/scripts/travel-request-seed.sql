BEGIN
    DECLARE SCRIPT_ID RAW(16) := '339AF90AD660F89BE063143410ACC0C4';
    BEGIN
        DECLARE NOW DATE;
        BEGIN
            DECLARE EXEC_COUNT INT := 0;
            BEGIN
                SELECT COUNT(1) INTO EXEC_COUNT FROM SCRIPT_EXECUTION_HISTORY WHERE ID = SCRIPT_ID;
                IF EXEC_COUNT = 0 THEN

                    DECLARE
                        V1 NUMBER; V2 NUMBER; V3 NUMBER; V4 NUMBER; V5 NUMBER;
                        V6 NUMBER; V7 NUMBER; V8 NUMBER; V9 NUMBER; V10 NUMBER;
                        D1 NUMBER; D2 NUMBER;
                    BEGIN
                        SELECT ID INTO V1  FROM VEHICLE WHERE LICENSE_PLATE = 'BG888HH';
                        SELECT ID INTO V2  FROM VEHICLE WHERE LICENSE_PLATE = 'BG333SS';
                        SELECT ID INTO V3  FROM VEHICLE WHERE LICENSE_PLATE = 'BG456MM';
                        SELECT ID INTO V4  FROM VEHICLE WHERE LICENSE_PLATE = 'BG111BB';
                        SELECT ID INTO V5  FROM VEHICLE WHERE LICENSE_PLATE = 'BG222VV';
                        SELECT ID INTO V6  FROM VEHICLE WHERE LICENSE_PLATE = 'BG444PP';
                        SELECT ID INTO V7  FROM VEHICLE WHERE LICENSE_PLATE = 'BG555TT';
                        SELECT ID INTO V8  FROM VEHICLE WHERE LICENSE_PLATE = 'BG666SS';
                        SELECT ID INTO V9  FROM VEHICLE WHERE LICENSE_PLATE = 'BG999CC';
                        SELECT ID INTO V10 FROM VEHICLE WHERE LICENSE_PLATE = 'BG303MV';

                        SELECT D.ID INTO D1 FROM DRIVER D JOIN nbp.nbp_user U ON D.USER_ID = U.ID WHERE U.USERNAME = 'anezovic';
                        SELECT D.ID INTO D2 FROM DRIVER D JOIN nbp.nbp_user U ON D.USER_ID = U.ID WHERE U.USERNAME = 'nmaric';


                        INSERT INTO TRAVEL_REQUEST
                          (APPROVED_BY, APPROVAL_DATE,
                           DEPARTURE_LOCATION, DESTINATION,
                           START_DATE, END_DATE,
                           REQUEST_STATUS,
                           VEHICLE_ID, DRIVER_ID)
                        VALUES
                          ('Mgr One', DATE '2025-03-15',
                           'Sarajevo', 'Mostar',
                           DATE '2025-04-01', DATE '2025-04-03',
                           1, V1, D1);

                        INSERT INTO TRAVEL_REQUEST
                          (APPROVED_BY, APPROVAL_DATE,
                           DEPARTURE_LOCATION, DESTINATION,
                           START_DATE, END_DATE,
                           REQUEST_STATUS,
                           VEHICLE_ID, DRIVER_ID)
                        VALUES
                          ('Mgr Two', DATE '2025-03-16',
                           'Zenica', 'Tuzla',
                           DATE '2025-04-05', DATE '2025-04-07',
                           2, V2, D2);

                        INSERT INTO TRAVEL_REQUEST
                          (APPROVED_BY, APPROVAL_DATE,
                           DEPARTURE_LOCATION, DESTINATION,
                           START_DATE, END_DATE,
                           REQUEST_STATUS,
                           VEHICLE_ID, DRIVER_ID)
                        VALUES
                          ('Mgr One', DATE '2025-03-17',
                           'Banja Luka', 'Sarajevo',
                           DATE '2025-04-10', DATE '2025-04-12',
                           3, V3, D1);

                        INSERT INTO TRAVEL_REQUEST
                          (APPROVED_BY, APPROVAL_DATE,
                           DEPARTURE_LOCATION, DESTINATION,
                           START_DATE, END_DATE,
                           REQUEST_STATUS,
                           VEHICLE_ID, DRIVER_ID)
                        VALUES
                          ('Mgr Three', DATE '2025-03-18',
                           'Mostar', 'Bihać',
                           DATE '2025-04-15', DATE '2025-04-16',
                           4, V4, D2);

                        INSERT INTO TRAVEL_REQUEST
                          (APPROVED_BY, APPROVAL_DATE,
                           DEPARTURE_LOCATION, DESTINATION,
                           START_DATE, END_DATE,
                           REQUEST_STATUS,
                           VEHICLE_ID, DRIVER_ID)
                        VALUES
                          ('Mgr Two', DATE '2025-03-19',
                           'Sarajevo', 'Mostar',
                           DATE '2025-04-20', DATE '2025-04-22',
                           5, V5, D1);

                        INSERT INTO TRAVEL_REQUEST
                          (APPROVED_BY, APPROVAL_DATE,
                           DEPARTURE_LOCATION, DESTINATION,
                           START_DATE, END_DATE,
                           REQUEST_STATUS,
                           VEHICLE_ID, DRIVER_ID)
                        VALUES
                          ('Mgr One', DATE '2025-03-20',
                           'Zenica', 'Banja Luka',
                           DATE '2025-04-25', DATE '2025-04-27',
                           6, V6, D2);

                        INSERT INTO TRAVEL_REQUEST
                          (APPROVED_BY, APPROVAL_DATE,
                           DEPARTURE_LOCATION, DESTINATION,
                           START_DATE, END_DATE,
                           REQUEST_STATUS,
                           VEHICLE_ID, DRIVER_ID)
                        VALUES
                          ('Mgr Three', DATE '2025-03-21',
                           'Tuzla', 'Mostar',
                           DATE '2025-05-01', DATE '2025-05-03',
                           1, V7, D1);

                        INSERT INTO TRAVEL_REQUEST
                          (APPROVED_BY, APPROVAL_DATE,
                           DEPARTURE_LOCATION, DESTINATION,
                           START_DATE, END_DATE,
                           REQUEST_STATUS,
                           VEHICLE_ID, DRIVER_ID)
                        VALUES
                          ('Mgr Two', DATE '2025-03-22',
                           'Bihać', 'Sarajevo',
                           DATE '2025-05-05', DATE '2025-05-06',
                           2, V8, D2);

                        INSERT INTO TRAVEL_REQUEST
                          (APPROVED_BY, APPROVAL_DATE,
                           DEPARTURE_LOCATION, DESTINATION,
                           START_DATE, END_DATE,
                           REQUEST_STATUS,
                           VEHICLE_ID, DRIVER_ID)
                        VALUES
                          ('Mgr One', DATE '2025-03-23',
                           'Mostar', 'Zenica',
                           DATE '2025-05-10', DATE '2025-05-12',
                           3, V9, D1);

                        INSERT INTO TRAVEL_REQUEST
                          (APPROVED_BY, APPROVAL_DATE,
                           DEPARTURE_LOCATION, DESTINATION,
                           START_DATE, END_DATE,
                           REQUEST_STATUS,
                           VEHICLE_ID, DRIVER_ID)
                        VALUES
                          ('Mgr Three', DATE '2025-03-24',
                           'Tuzla', 'Banja Luka',
                           DATE '2025-05-15', DATE '2025-05-17',
                           4, V10, D2);

                        COMMIT;
                    END;

                    SELECT CAST(SYSTIMESTAMP AT TIME ZONE 'UTC' AS DATE)
                      INTO NOW
                      FROM DUAL;

                    INSERT INTO SCRIPT_EXECUTION_HISTORY
                    VALUES (SCRIPT_ID, 'travel-request-seed.sql', NOW);

                    COMMIT;
                END IF;
            END;
        END;
    END;
END;
