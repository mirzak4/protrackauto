BEGIN
    DECLARE SCRIPT_ID RAW(16) := '34DE0DC470D77152E063143410ACB914';
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
                        V11 NUMBER; V12 NUMBER; V13 NUMBER; V14 NUMBER; V15 NUMBER;
                        V16 NUMBER; V17 NUMBER; V18 NUMBER; V19 NUMBER; V20 NUMBER;

                        D1 NUMBER; D2 NUMBER; D3 NUMBER; D4 NUMBER; D5 NUMBER; D6 NUMBER;
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
                        SELECT ID INTO V11 FROM VEHICLE WHERE LICENSE_PLATE = 'BG404JP';
                        SELECT ID INTO V12 FROM VEHICLE WHERE LICENSE_PLATE = 'BG505LM';
                        SELECT ID INTO V13 FROM VEHICLE WHERE LICENSE_PLATE = 'BG606CM';
                        SELECT ID INTO V14 FROM VEHICLE WHERE LICENSE_PLATE = 'BG707RD';
                        SELECT ID INTO V15 FROM VEHICLE WHERE LICENSE_PLATE = 'BG909HY';
                        SELECT ID INTO V16 FROM VEHICLE WHERE LICENSE_PLATE = 'BG010FT';
                        SELECT ID INTO V17 FROM VEHICLE WHERE LICENSE_PLATE = 'BG111TR';
                        SELECT ID INTO V18 FROM VEHICLE WHERE LICENSE_PLATE = 'BG212ES';
                        SELECT ID INTO V19 FROM VEHICLE WHERE LICENSE_PLATE = 'BG313MB';
                        SELECT ID INTO V20 FROM VEHICLE WHERE LICENSE_PLATE = 'BG202WG';

                        SELECT D.ID INTO D1 FROM DRIVER D JOIN nbp.nbp_user U ON D.USER_ID = U.ID WHERE U.USERNAME = 'anezovic';
                        SELECT D.ID INTO D2 FROM DRIVER D JOIN nbp.nbp_user U ON D.USER_ID = U.ID WHERE U.USERNAME = 'nmaric';
                        SELECT D.ID INTO D3 FROM DRIVER D JOIN nbp.nbp_user U ON D.USER_ID = U.ID WHERE U.USERNAME = 'lkovac';
                        SELECT D.ID INTO D4 FROM DRIVER D JOIN nbp.nbp_user U ON D.USER_ID = U.ID WHERE U.USERNAME = 'mpetrovic';
                        SELECT D.ID INTO D5 FROM DRIVER D JOIN nbp.nbp_user U ON D.USER_ID = U.ID WHERE U.USERNAME = 'nbegovic';
                        SELECT D.ID INTO D6 FROM DRIVER D JOIN nbp.nbp_user U ON D.USER_ID = U.ID WHERE U.USERNAME = 'msoftic';



                        --INSERT INTO TRAVEL_REQUEST
                        --  (APPROVED_BY, APPROVAL_DATE, DEPARTURE_LOCATION, DESTINATION, START_DATE, END_DATE, REQUEST_STATUS, VEHICLE_ID, DRIVER_ID)
                        --VALUES
                        --  ('Mgr One', DATE '2025-03-15', 'Sarajevo', 'Mostar', DATE '2025-04-01', DATE '2025-04-01', 1, V1, D1);

                        --INSERT INTO TRAVEL_REQUEST
                        --  (APPROVED_BY, APPROVAL_DATE, DEPARTURE_LOCATION, DESTINATION, START_DATE, END_DATE, REQUEST_STATUS, VEHICLE_ID, DRIVER_ID)
                        --VALUES
                        --  ('Mgr Two', DATE '2025-03-16', 'Zenica', 'Tuzla', DATE '2025-04-05', DATE '2025-04-05', 2, V2, D2);

                        --INSERT INTO TRAVEL_REQUEST
                        --  (APPROVED_BY, APPROVAL_DATE, DEPARTURE_LOCATION, DESTINATION, START_DATE, END_DATE, REQUEST_STATUS, VEHICLE_ID, DRIVER_ID)
                        --VALUES
                        --  ('Mgr One', DATE '2025-03-17', 'Banja Luka', 'Sarajevo', DATE '2025-04-10', DATE '2025-04-10', 3, V3, D1);

                        --INSERT INTO TRAVEL_REQUEST
                        --  (APPROVED_BY, APPROVAL_DATE, DEPARTURE_LOCATION, DESTINATION, START_DATE, END_DATE, REQUEST_STATUS, VEHICLE_ID, DRIVER_ID)
                        --VALUES
                        --  ('Mgr Three', DATE '2025-03-18', 'Mostar', 'Bihać', DATE '2025-04-15', DATE '2025-04-16', 4, V4, D2);

                        --INSERT INTO TRAVEL_REQUEST
                        --  (APPROVED_BY, APPROVAL_DATE, DEPARTURE_LOCATION, DESTINATION, START_DATE, END_DATE, REQUEST_STATUS, VEHICLE_ID, DRIVER_ID)
                        --VALUES
                        --  ('Mgr Two', DATE '2025-03-19', 'Sarajevo', 'Mostar', DATE '2025-04-20', DATE '2025-04-20', 5, V5, D1);

                        --INSERT INTO TRAVEL_REQUEST
                        --  (APPROVED_BY, APPROVAL_DATE, DEPARTURE_LOCATION, DESTINATION, START_DATE, END_DATE, REQUEST_STATUS, VEHICLE_ID, DRIVER_ID)
                        --VALUES
                        --  ('Mgr One', DATE '2025-03-20', 'Zenica', 'Banja Luka', DATE '2025-04-25', DATE '2025-04-25', 6, V6, D2);

                        --INSERT INTO TRAVEL_REQUEST
                        --  (APPROVED_BY, APPROVAL_DATE, DEPARTURE_LOCATION, DESTINATION, START_DATE, END_DATE, REQUEST_STATUS, VEHICLE_ID, DRIVER_ID)
                       --VALUES
                        --  ('Mgr Three', DATE '2025-03-21', 'Tuzla', 'Mostar', DATE '2025-05-01', DATE '2025-05-01', 1, V7, D1);

                        --INSERT INTO TRAVEL_REQUEST
                        --  (APPROVED_BY, APPROVAL_DATE, DEPARTURE_LOCATION, DESTINATION, START_DATE, END_DATE, REQUEST_STATUS, VEHICLE_ID, DRIVER_ID)
                        --VALUES
                        --  ('Mgr Two', DATE '2025-03-22', 'Bihać', 'Sarajevo', DATE '2025-05-05', DATE '2025-05-06', 2, V8, D2);

                        --INSERT INTO TRAVEL_REQUEST
                        --  (APPROVED_BY, APPROVAL_DATE, DEPARTURE_LOCATION, DESTINATION, START_DATE, END_DATE, REQUEST_STATUS, VEHICLE_ID, DRIVER_ID)
                        --VALUES
                        --  ('Mgr One', DATE '2025-03-23', 'Mostar', 'Zenica', DATE '2025-05-10', DATE '2025-05-10', 3, V9, D1);

                        --INSERT INTO TRAVEL_REQUEST
                        --  (APPROVED_BY, APPROVAL_DATE, DEPARTURE_LOCATION, DESTINATION, START_DATE, END_DATE, REQUEST_STATUS, VEHICLE_ID, DRIVER_ID)
                        --VALUES
                        --  ('Mgr Three', DATE '2025-03-24', 'Tuzla', 'Banja Luka', DATE '2025-05-15', DATE '2025-05-15', 4, V10, D2);


                        INSERT INTO TRAVEL_REQUEST
                          (APPROVED_BY, APPROVAL_DATE, DEPARTURE_LOCATION, DESTINATION, START_DATE, END_DATE, REQUEST_STATUS, VEHICLE_ID, DRIVER_ID)
                        VALUES
                          ('Mgr One', DATE '2025-03-25', 'Sarajevo', 'Banja Luka', DATE '2025-05-15', DATE '2025-05-15', 5, V11, D3);

                        INSERT INTO TRAVEL_REQUEST
                          (APPROVED_BY, APPROVAL_DATE, DEPARTURE_LOCATION, DESTINATION, START_DATE, END_DATE, REQUEST_STATUS, VEHICLE_ID, DRIVER_ID)
                        VALUES
                          ('Mgr Two', DATE '2025-03-26', 'Mostar', 'Trebinje', DATE '2025-06-05', DATE '2025-06-05', 6, V12, D5);

                        INSERT INTO TRAVEL_REQUEST
                          (APPROVED_BY, APPROVAL_DATE, DEPARTURE_LOCATION, DESTINATION, START_DATE, END_DATE, REQUEST_STATUS, VEHICLE_ID, DRIVER_ID)
                        VALUES
                          ('Mgr One', DATE '2025-03-25', 'Zenica', 'Tuzla', DATE '2025-05-18', DATE '2025-05-18', 5, V13, D3);

                        INSERT INTO TRAVEL_REQUEST
                          (APPROVED_BY, APPROVAL_DATE, DEPARTURE_LOCATION, DESTINATION, START_DATE, END_DATE, REQUEST_STATUS, VEHICLE_ID, DRIVER_ID)
                        VALUES
                          ('Mgr Two', DATE '2025-03-26', 'Bihać', 'Sarajevo', DATE '2025-06-10', DATE '2025-06-10', 6, V14, D6);

                        INSERT INTO TRAVEL_REQUEST
                          (APPROVED_BY, APPROVAL_DATE, DEPARTURE_LOCATION, DESTINATION, START_DATE, END_DATE, REQUEST_STATUS, VEHICLE_ID, DRIVER_ID)
                        VALUES
                          ('Mgr Two', DATE '2025-03-26', 'Doboj', 'Brčko', DATE '2025-06-08', DATE '2025-06-08', 6, V15, D4);

                        INSERT INTO TRAVEL_REQUEST
                          (APPROVED_BY, APPROVAL_DATE, DEPARTURE_LOCATION, DESTINATION, START_DATE, END_DATE, REQUEST_STATUS, VEHICLE_ID, DRIVER_ID)
                        VALUES
                          ('Mgr One', DATE '2025-03-25', 'Tuzla', 'Zvornik', DATE '2025-05-22', DATE '2025-05-22', 5, V16, D3);

                        INSERT INTO TRAVEL_REQUEST
                          (APPROVED_BY, APPROVAL_DATE, DEPARTURE_LOCATION, DESTINATION, START_DATE, END_DATE, REQUEST_STATUS, VEHICLE_ID, DRIVER_ID)
                        VALUES
                          ('Mgr Two', DATE '2025-03-26', 'Mostar', 'Konjic', DATE '2025-06-15', DATE '2025-06-15', 6, V17, D4);

                        INSERT INTO TRAVEL_REQUEST
                          (APPROVED_BY, APPROVAL_DATE, DEPARTURE_LOCATION, DESTINATION, START_DATE, END_DATE, REQUEST_STATUS, VEHICLE_ID, DRIVER_ID)
                        VALUES
                          ('Mgr One', DATE '2025-03-25', 'Sarajevo', 'Jajce', DATE '2025-05-25', DATE '2025-05-25', 5, V18, D5);

                        INSERT INTO TRAVEL_REQUEST
                          (APPROVED_BY, APPROVAL_DATE, DEPARTURE_LOCATION, DESTINATION, START_DATE, END_DATE, REQUEST_STATUS, VEHICLE_ID, DRIVER_ID)
                        VALUES
                          ('Mgr Two', DATE '2025-03-26', 'Banja Luka', 'Prijedor', DATE '2025-06-18', DATE '2025-06-18', 6, V19, D6);

                        INSERT INTO TRAVEL_REQUEST
                          (APPROVED_BY, APPROVAL_DATE, DEPARTURE_LOCATION, DESTINATION, START_DATE, END_DATE, REQUEST_STATUS, VEHICLE_ID, DRIVER_ID)
                        VALUES
                          ('Mgr Two', DATE '2025-03-26', 'Trebinje', 'Neum', DATE '2025-06-20', DATE '2025-06-20', 6, V20, D5);
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
