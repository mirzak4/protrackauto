BEGIN
    DECLARE SCRIPT_ID RAW(16) := '32AF3D04E4FB74D0E063143410AC9634';
    BEGIN
        DECLARE NOW DATE;
        BEGIN
            DECLARE EXEC_COUNT INT := 0;
            BEGIN
                SELECT COUNT(1) INTO EXEC_COUNT FROM SCRIPT_EXECUTION_HISTORY WHERE ID = SCRIPT_ID;
                IF EXEC_COUNT = 0 THEN
                -- Vehicle 1 (Travel Car - Sedan)
                    INSERT INTO VEHICLE (
                        LICENSE_PLATE, FIRST_REGISTRATION_DATE, FIRST_REGISTRATION_PLACE,
                        FIRST_LICENSE_PLATE, REGISTRATION_ISSUE_DATE, REGISTRATION_ISSUE_PLACE,
                        FUEL_ID, VEHICLE_CATEGORY, VEHICLE_BODY_TYPE, COLOR, VEHICLE_BRAND_TYPE,
                        REGISTRATION_NUMBER, COMMERCIAL_DESCRIPTION, CHASSIS_NUMBER,
                        PRODUCTION_YEAR, MAX_WEIGHT, PAYLOAD, VEHICLE_WEIGHT,
                        POWER_WEIGHT_RATIO, SEAT_COUNT, ENGINE_DISPLACEMENT, MAX_POWER,
                        ECO_CHARACTERISTICS, CATALYST, ENGINE_NUMBER
                    ) VALUES (
                        'BG123AA', TO_DATE('2022-05-10', 'YYYY-MM-DD'), 'Sarajevo',
                        'BG123AA', TO_DATE('2022-05-15', 'YYYY-MM-DD'), 'Sarajevo',
                        1, 1, 1, 'Black', 'Volkswagen Passat',
                        'REG001001', 'VW Passat 2.0 TDI', 'WVWZZZ3CZ6E123456',
                        2022, 1850.00, 500.00, 1350.00,
                        0.12, 5, 2.0, 150.0,
                        'Euro 6', 'Yes', 'ENGVW123456001'
                    );

                    -- Vehicle 2 (Truck - Truck)
                    INSERT INTO VEHICLE (
                        LICENSE_PLATE, FIRST_REGISTRATION_DATE, FIRST_REGISTRATION_PLACE,
                        FIRST_LICENSE_PLATE, REGISTRATION_ISSUE_DATE, REGISTRATION_ISSUE_PLACE,
                        FUEL_ID, VEHICLE_CATEGORY, VEHICLE_BODY_TYPE, COLOR, VEHICLE_BRAND_TYPE,
                        REGISTRATION_NUMBER, COMMERCIAL_DESCRIPTION, CHASSIS_NUMBER,
                        PRODUCTION_YEAR, MAX_WEIGHT, PAYLOAD, VEHICLE_WEIGHT,
                        POWER_WEIGHT_RATIO, SEAT_COUNT, ENGINE_DISPLACEMENT, MAX_POWER,
                        ECO_CHARACTERISTICS, CATALYST, ENGINE_NUMBER
                    ) VALUES (
                        'BG789TT', TO_DATE('2018-03-15', 'YYYY-MM-DD'), 'Banja Luka',
                        'BL456TT', TO_DATE('2020-07-20', 'YYYY-MM-DD'), 'Banja Luka',
                        3, 2, 13, 'White', 'MAN TGS',
                        'REG002002', 'MAN TGS 26.440', 'WMABVZZZ8K7123456',
                        2018, 25000.00, 15000.00, 10000.00,
                        0.05, 2, 12.4, 440.0,
                        'Euro 5', 'Yes', 'ENGMAN123456002'
                    );

                    -- Vehicle 3 (Motorcycle - Motorcycle)
                    INSERT INTO VEHICLE (
                        LICENSE_PLATE, FIRST_REGISTRATION_DATE, FIRST_REGISTRATION_PLACE,
                        FIRST_LICENSE_PLATE, REGISTRATION_ISSUE_DATE, REGISTRATION_ISSUE_PLACE,
                        FUEL_ID, VEHICLE_CATEGORY, VEHICLE_BODY_TYPE, COLOR, VEHICLE_BRAND_TYPE,
                        REGISTRATION_NUMBER, COMMERCIAL_DESCRIPTION, CHASSIS_NUMBER,
                        PRODUCTION_YEAR, MAX_WEIGHT, PAYLOAD, VEHICLE_WEIGHT,
                        POWER_WEIGHT_RATIO, SEAT_COUNT, ENGINE_DISPLACEMENT, MAX_POWER,
                        ECO_CHARACTERISTICS, CATALYST, ENGINE_NUMBER
                    ) VALUES (
                        'BG456MM', TO_DATE('2021-07-22', 'YYYY-MM-DD'), 'Mostar',
                        'BG456MM', TO_DATE('2021-07-25', 'YYYY-MM-DD'), 'Mostar',
                        2, 3, 15, 'Red', 'Honda CBR600RR',
                        'REG003003', 'Honda CBR600RR', 'MLHPC4600B5123456',
                        2021, 200.00, 100.00, 100.00,
                        0.60, 2, 0.599, 120.0,
                        'Euro 5', 'Yes', 'ENGHD123456003'
                    );

                    -- Vehicle 4 (Bus - Bus)
                    INSERT INTO VEHICLE (
                        LICENSE_PLATE, FIRST_REGISTRATION_DATE, FIRST_REGISTRATION_PLACE,
                        FIRST_LICENSE_PLATE, REGISTRATION_ISSUE_DATE, REGISTRATION_ISSUE_PLACE,
                        FUEL_ID, VEHICLE_CATEGORY, VEHICLE_BODY_TYPE, COLOR, VEHICLE_BRAND_TYPE,
                        REGISTRATION_NUMBER, COMMERCIAL_DESCRIPTION, CHASSIS_NUMBER,
                        PRODUCTION_YEAR, MAX_WEIGHT, PAYLOAD, VEHICLE_WEIGHT,
                        POWER_WEIGHT_RATIO, SEAT_COUNT, ENGINE_DISPLACEMENT, MAX_POWER,
                        ECO_CHARACTERISTICS, CATALYST, ENGINE_NUMBER
                    ) VALUES (
                        'BG111BB', TO_DATE('2019-11-05', 'YYYY-MM-DD'), 'Tuzla',
                        'BG111BB', TO_DATE('2019-11-10', 'YYYY-MM-DD'), 'Tuzla',
                        3, 4, 14, 'Blue', 'Mercedes-Benz Tourismo',
                        'REG004004', 'Mercedes Tourismo M', 'WMABVZZZ8K7123456',
                        2019, 18000.00, 8000.00, 10000.00,
                        0.08, 52, 10.7, 428.0,
                        'Euro 6', 'Yes', 'ENGMBC123456004'
                    );

                    -- Vehicle 5 (Van - Van)
                    INSERT INTO VEHICLE (
                        LICENSE_PLATE, FIRST_REGISTRATION_DATE, FIRST_REGISTRATION_PLACE,
                        FIRST_LICENSE_PLATE, REGISTRATION_ISSUE_DATE, REGISTRATION_ISSUE_PLACE,
                        FUEL_ID, VEHICLE_CATEGORY, VEHICLE_BODY_TYPE, COLOR, VEHICLE_BRAND_TYPE,
                        REGISTRATION_NUMBER, COMMERCIAL_DESCRIPTION, CHASSIS_NUMBER,
                        PRODUCTION_YEAR, MAX_WEIGHT, PAYLOAD, VEHICLE_WEIGHT,
                        POWER_WEIGHT_RATIO, SEAT_COUNT, ENGINE_DISPLACEMENT, MAX_POWER,
                        ECO_CHARACTERISTICS, CATALYST, ENGINE_NUMBER
                    ) VALUES (
                        'BG222VV', TO_DATE('2020-09-18', 'YYYY-MM-DD'), 'Zenica',
                        'BG222VV', TO_DATE('2020-09-20', 'YYYY-MM-DD'), 'Zenica',
                        2, 5, 8, 'White', 'Ford Transit',
                        'REG005005', 'Ford Transit Custom', 'WF0VXXTTVHV123456',
                        2020, 2800.00, 1200.00, 1600.00,
                        0.10, 3, 2.0, 130.0,
                        'Euro 6', 'Yes', 'ENGFD123456005'
                    );

                    -- Vehicle 6 (SUV - SUV)
                    INSERT INTO VEHICLE (
                        LICENSE_PLATE, FIRST_REGISTRATION_DATE, FIRST_REGISTRATION_PLACE,
                        FIRST_LICENSE_PLATE, REGISTRATION_ISSUE_DATE, REGISTRATION_ISSUE_PLACE,
                        FUEL_ID, VEHICLE_CATEGORY, VEHICLE_BODY_TYPE, COLOR, VEHICLE_BRAND_TYPE,
                        REGISTRATION_NUMBER, COMMERCIAL_DESCRIPTION, CHASSIS_NUMBER,
                        PRODUCTION_YEAR, MAX_WEIGHT, PAYLOAD, VEHICLE_WEIGHT,
                        POWER_WEIGHT_RATIO, SEAT_COUNT, ENGINE_DISPLACEMENT, MAX_POWER,
                        ECO_CHARACTERISTICS, CATALYST, ENGINE_NUMBER
                    ) VALUES (
                        'BG333SS', TO_DATE('2021-04-12', 'YYYY-MM-DD'), 'Sarajevo',
                        'BG333SS', TO_DATE('2021-04-15', 'YYYY-MM-DD'), 'Sarajevo',
                        1, 6, 3, 'Gray', 'Audi Q7',
                        'REG006006', 'Audi Q7 45 TDI', 'WAUZZZ4MXN1234567',
                        2021, 2500.00, 700.00, 1800.00,
                        0.15, 7, 3.0, 231.0,
                        'Euro 6', 'Yes', 'ENGAU123456006'
                    );

                    -- Vehicle 7 (Pickup - Pickup Truck)
                    INSERT INTO VEHICLE (
                        LICENSE_PLATE, FIRST_REGISTRATION_DATE, FIRST_REGISTRATION_PLACE,
                        FIRST_LICENSE_PLATE, REGISTRATION_ISSUE_DATE, REGISTRATION_ISSUE_PLACE,
                        FUEL_ID, VEHICLE_CATEGORY, VEHICLE_BODY_TYPE, COLOR, VEHICLE_BRAND_TYPE,
                        REGISTRATION_NUMBER, COMMERCIAL_DESCRIPTION, CHASSIS_NUMBER,
                        PRODUCTION_YEAR, MAX_WEIGHT, PAYLOAD, VEHICLE_WEIGHT,
                        POWER_WEIGHT_RATIO, SEAT_COUNT, ENGINE_DISPLACEMENT, MAX_POWER,
                        ECO_CHARACTERISTICS, CATALYST, ENGINE_NUMBER
                    ) VALUES (
                        'BG444PP', TO_DATE('2017-08-25', 'YYYY-MM-DD'), 'Bihać',
                        'BG444PP', TO_DATE('2017-08-30', 'YYYY-MM-DD'), 'Bihać',
                        3, 7, 7, 'Black', 'Toyota Hilux',
                        'REG007007', 'Toyota Hilux 2.4 D-4D', 'MR0FZ3GZ0J0123456',
                        2017, 3000.00, 1000.00, 2000.00,
                        0.09, 5, 2.4, 150.0,
                        'Euro 5', 'Yes', 'ENGTO123456007'
                    );

                    -- Vehicle 8 (Tractor - Truck)
                    INSERT INTO VEHICLE (
                        LICENSE_PLATE, FIRST_REGISTRATION_DATE, FIRST_REGISTRATION_PLACE,
                        FIRST_LICENSE_PLATE, REGISTRATION_ISSUE_DATE, REGISTRATION_ISSUE_PLACE,
                        FUEL_ID, VEHICLE_CATEGORY, VEHICLE_BODY_TYPE, COLOR, VEHICLE_BRAND_TYPE,
                        REGISTRATION_NUMBER, COMMERCIAL_DESCRIPTION, CHASSIS_NUMBER,
                        PRODUCTION_YEAR, MAX_WEIGHT, PAYLOAD, VEHICLE_WEIGHT,
                        POWER_WEIGHT_RATIO, SEAT_COUNT, ENGINE_DISPLACEMENT, MAX_POWER,
                        ECO_CHARACTERISTICS, CATALYST, ENGINE_NUMBER
                    ) VALUES (
                        'BG555TT', TO_DATE('2015-06-30', 'YYYY-MM-DD'), 'Brčko',
                        'BG555TT', TO_DATE('2015-07-05', 'YYYY-MM-DD'), 'Brčko',
                        3, 8, 13, 'Green', 'John Deere 6130M',
                        'REG008008', 'John Deere 6130M', 'JD6130M123456789',
                        2015, 6500.00, 3000.00, 3500.00,
                        0.05, 2, 4.5, 130.0,
                        'Euro 3', 'Yes', 'ENGJD123456008'
                    );

                    -- Vehicle 9 (Scooter - Motorcycle)
                    INSERT INTO VEHICLE (
                        LICENSE_PLATE, FIRST_REGISTRATION_DATE, FIRST_REGISTRATION_PLACE,
                        FIRST_LICENSE_PLATE, REGISTRATION_ISSUE_DATE, REGISTRATION_ISSUE_PLACE,
                        FUEL_ID, VEHICLE_CATEGORY, VEHICLE_BODY_TYPE, COLOR, VEHICLE_BRAND_TYPE,
                        REGISTRATION_NUMBER, COMMERCIAL_DESCRIPTION, CHASSIS_NUMBER,
                        PRODUCTION_YEAR, MAX_WEIGHT, PAYLOAD, VEHICLE_WEIGHT,
                        POWER_WEIGHT_RATIO, SEAT_COUNT, ENGINE_DISPLACEMENT, MAX_POWER,
                        ECO_CHARACTERISTICS, CATALYST, ENGINE_NUMBER
                    ) VALUES (
                        'BG666SS', TO_DATE('2022-03-08', 'YYYY-MM-DD'), 'Mostar',
                        'BG666SS', TO_DATE('2022-03-10', 'YYYY-MM-DD'), 'Mostar',
                        2, 9, 15, 'Yellow', 'Vespa Primavera',
                        'REG009009', 'Vespa Primavera 125', 'ZAPM123456789012',
                        2022, 120.00, 80.00, 40.00,
                        0.25, 2, 0.125, 10.0,
                        'Euro 5', 'Yes', 'ENGVP123456009'
                    );

                    -- Vehicle 10 (Bicycle - Motorcycle) - Special case
                    INSERT INTO VEHICLE (
                        LICENSE_PLATE, FIRST_REGISTRATION_DATE, FIRST_REGISTRATION_PLACE,
                        FIRST_LICENSE_PLATE, REGISTRATION_ISSUE_DATE, REGISTRATION_ISSUE_PLACE,
                        FUEL_ID, VEHICLE_CATEGORY, VEHICLE_BODY_TYPE, COLOR, VEHICLE_BRAND_TYPE,
                        REGISTRATION_NUMBER, COMMERCIAL_DESCRIPTION, CHASSIS_NUMBER,
                        PRODUCTION_YEAR, MAX_WEIGHT, PAYLOAD, VEHICLE_WEIGHT,
                        POWER_WEIGHT_RATIO, SEAT_COUNT, ENGINE_DISPLACEMENT, MAX_POWER,
                        ECO_CHARACTERISTICS, CATALYST, ENGINE_NUMBER
                    ) VALUES (
                        'BG777BB', TO_DATE('2023-01-05', 'YYYY-MM-DD'), 'Sarajevo',
                        'BG777BB', TO_DATE('2023-01-10', 'YYYY-MM-DD'), 'Sarajevo',
                        4, 10, 15, 'Red', 'Specialized Turbo Vado',
                        'REG010010', 'E-bike Turbo Vado 4.0', 'SPC123456789',
                        2023, 25.00, 15.00, 10.00,
                        0.40, 1, 0.0, 0.0,
                        'Electric', 'No', 'MOTOREB001'
                    );

                    -- Vehicle 11 (Travel Car - Hatchback)
                    INSERT INTO VEHICLE (
                        LICENSE_PLATE, FIRST_REGISTRATION_DATE, FIRST_REGISTRATION_PLACE,
                        FIRST_LICENSE_PLATE, REGISTRATION_ISSUE_DATE, REGISTRATION_ISSUE_PLACE,
                        FUEL_ID, VEHICLE_CATEGORY, VEHICLE_BODY_TYPE, COLOR, VEHICLE_BRAND_TYPE,
                        REGISTRATION_NUMBER, COMMERCIAL_DESCRIPTION, CHASSIS_NUMBER,
                        PRODUCTION_YEAR, MAX_WEIGHT, PAYLOAD, VEHICLE_WEIGHT,
                        POWER_WEIGHT_RATIO, SEAT_COUNT, ENGINE_DISPLACEMENT, MAX_POWER,
                        ECO_CHARACTERISTICS, CATALYST, ENGINE_NUMBER
                    ) VALUES (
                        'BG888HH', TO_DATE('2020-07-15', 'YYYY-MM-DD'), 'Tuzla',
                        'BG888HH', TO_DATE('2020-07-20', 'YYYY-MM-DD'), 'Tuzla',
                        1, 1, 2, 'Blue', 'Ford Focus',
                        'REG011011', 'Ford Focus 1.5 EcoBoost', 'WF0FXXGBBF1234567',
                        2020, 1600.00, 450.00, 1150.00,
                        0.13, 5, 1.5, 150.0,
                        'Euro 6', 'Yes', 'ENGFOC123456'
                    );

                    -- Vehicle 12 (Travel Car - Coupe)
                    INSERT INTO VEHICLE (
                        LICENSE_PLATE, FIRST_REGISTRATION_DATE, FIRST_REGISTRATION_PLACE,
                        FIRST_LICENSE_PLATE, REGISTRATION_ISSUE_DATE, REGISTRATION_ISSUE_PLACE,
                        FUEL_ID, VEHICLE_CATEGORY, VEHICLE_BODY_TYPE, COLOR, VEHICLE_BRAND_TYPE,
                        REGISTRATION_NUMBER, COMMERCIAL_DESCRIPTION, CHASSIS_NUMBER,
                        PRODUCTION_YEAR, MAX_WEIGHT, PAYLOAD, VEHICLE_WEIGHT,
                        POWER_WEIGHT_RATIO, SEAT_COUNT, ENGINE_DISPLACEMENT, MAX_POWER,
                        ECO_CHARACTERISTICS, CATALYST, ENGINE_NUMBER
                    ) VALUES (
                        'BG999CC', TO_DATE('2019-05-20', 'YYYY-MM-DD'), 'Banja Luka',
                        'BG999CC', TO_DATE('2019-05-25', 'YYYY-MM-DD'), 'Banja Luka',
                        1, 1, 4, 'Silver', 'BMW 420d',
                        'REG012012', 'BMW 420d Coupe', 'WBA4Y3C50JG123456',
                        2019, 1700.00, 400.00, 1300.00,
                        0.14, 4, 2.0, 190.0,
                        'Euro 6', 'Yes', 'ENGBMW123456'
                    );

                    -- Vehicle 13
                    INSERT INTO VEHICLE (
                        LICENSE_PLATE, FIRST_REGISTRATION_DATE, FIRST_REGISTRATION_PLACE,
                        FIRST_LICENSE_PLATE, REGISTRATION_ISSUE_DATE, REGISTRATION_ISSUE_PLACE,
                        FUEL_ID, VEHICLE_CATEGORY, VEHICLE_BODY_TYPE, COLOR, VEHICLE_BRAND_TYPE,
                        REGISTRATION_NUMBER, COMMERCIAL_DESCRIPTION, CHASSIS_NUMBER,
                        PRODUCTION_YEAR, MAX_WEIGHT, PAYLOAD, VEHICLE_WEIGHT,
                        POWER_WEIGHT_RATIO, SEAT_COUNT, ENGINE_DISPLACEMENT, MAX_POWER,
                        ECO_CHARACTERISTICS, CATALYST, ENGINE_NUMBER
                    ) VALUES (
                        'BG101CV', TO_DATE('2018-06-12', 'YYYY-MM-DD'), 'Mostar',
                        'BG101CV', TO_DATE('2018-06-15', 'YYYY-MM-DD'), 'Mostar',
                        1, 1, 5, 'Red', 'Audi A5 Cabrio',
                        'REG013013', 'Audi A5 2.0 TFSI', 'WAUZZZF49KN123456',
                        2018, 1850.00, 400.00, 1450.00,
                        0.14, 4, 2.0, 190.0,
                        'Euro 6', 'Yes', 'ENGAUDI013'
                    );

                    -- Vehicle 14 (Travel Car - Wagon)
                    INSERT INTO VEHICLE (
                        LICENSE_PLATE, FIRST_REGISTRATION_DATE, FIRST_REGISTRATION_PLACE,
                        FIRST_LICENSE_PLATE, REGISTRATION_ISSUE_DATE, REGISTRATION_ISSUE_PLACE,
                        FUEL_ID, VEHICLE_CATEGORY, VEHICLE_BODY_TYPE, COLOR, VEHICLE_BRAND_TYPE,
                        REGISTRATION_NUMBER, COMMERCIAL_DESCRIPTION, CHASSIS_NUMBER,
                        PRODUCTION_YEAR, MAX_WEIGHT, PAYLOAD, VEHICLE_WEIGHT,
                        POWER_WEIGHT_RATIO, SEAT_COUNT, ENGINE_DISPLACEMENT, MAX_POWER,
                        ECO_CHARACTERISTICS, CATALYST, ENGINE_NUMBER
                    ) VALUES (
                        'BG202WG', TO_DATE('2019-09-18', 'YYYY-MM-DD'), 'Zenica',
                        'BG202WG', TO_DATE('2019-09-20', 'YYYY-MM-DD'), 'Zenica',
                        1, 1, 6, 'Gray', 'Skoda Octavia Combi',
                        'REG014014', 'Skoda Octavia 2.0 TDI', 'TMBJJ7NE5K0123456',
                        2019, 1700.00, 550.00, 1150.00,
                        0.13, 5, 2.0, 150.0,
                        'Euro 6', 'Yes', 'ENGSKO014'
                    );

                    -- Vehicle 15 (Van - Minivan)
                    INSERT INTO VEHICLE (
                        LICENSE_PLATE, FIRST_REGISTRATION_DATE, FIRST_REGISTRATION_PLACE,
                        FIRST_LICENSE_PLATE, REGISTRATION_ISSUE_DATE, REGISTRATION_ISSUE_PLACE,
                        FUEL_ID, VEHICLE_CATEGORY, VEHICLE_BODY_TYPE, COLOR, VEHICLE_BRAND_TYPE,
                        REGISTRATION_NUMBER, COMMERCIAL_DESCRIPTION, CHASSIS_NUMBER,
                        PRODUCTION_YEAR, MAX_WEIGHT, PAYLOAD, VEHICLE_WEIGHT,
                        POWER_WEIGHT_RATIO, SEAT_COUNT, ENGINE_DISPLACEMENT, MAX_POWER,
                        ECO_CHARACTERISTICS, CATALYST, ENGINE_NUMBER
                    ) VALUES (
                        'BG303MV', TO_DATE('2020-11-05', 'YYYY-MM-DD'), 'Sarajevo',
                        'BG303MV', TO_DATE('2020-11-10', 'YYYY-MM-DD'), 'Sarajevo',
                        1, 5, 9, 'Black', 'Volkswagen Sharan',
                        'REG015015', 'VW Sharan 2.0 TDI', 'WVWZZZ7NZEY123456',
                        2020, 1950.00, 600.00, 1350.00,
                        0.11, 7, 2.0, 150.0,
                        'Euro 6', 'Yes', 'ENGVW015'
                    );

                    -- Vehicle 16 (SUV - Jeep)
                    INSERT INTO VEHICLE (
                        LICENSE_PLATE, FIRST_REGISTRATION_DATE, FIRST_REGISTRATION_PLACE,
                        FIRST_LICENSE_PLATE, REGISTRATION_ISSUE_DATE, REGISTRATION_ISSUE_PLACE,
                        FUEL_ID, VEHICLE_CATEGORY, VEHICLE_BODY_TYPE, COLOR, VEHICLE_BRAND_TYPE,
                        REGISTRATION_NUMBER, COMMERCIAL_DESCRIPTION, CHASSIS_NUMBER,
                        PRODUCTION_YEAR, MAX_WEIGHT, PAYLOAD, VEHICLE_WEIGHT,
                        POWER_WEIGHT_RATIO, SEAT_COUNT, ENGINE_DISPLACEMENT, MAX_POWER,
                        ECO_CHARACTERISTICS, CATALYST, ENGINE_NUMBER
                    ) VALUES (
                        'BG404JP', TO_DATE('2017-04-22', 'YYYY-MM-DD'), 'Banja Luka',
                        'BG404JP', TO_DATE('2017-04-25', 'YYYY-MM-DD'), 'Banja Luka',
                        3, 6, 11, 'Green', 'Jeep Wrangler',
                        'REG016016', 'Jeep Wrangler 2.8 CRD', '1J4FA49S97P123456',
                        2017, 2300.00, 800.00, 1500.00,
                        0.10, 5, 2.8, 200.0,
                        'Euro 5', 'Yes', 'ENGJEEP016'
                    );

                    -- Vehicle 17 (Travel Car - Limousine)
                    INSERT INTO VEHICLE (
                        LICENSE_PLATE, FIRST_REGISTRATION_DATE, FIRST_REGISTRATION_PLACE,
                        FIRST_LICENSE_PLATE, REGISTRATION_ISSUE_DATE, REGISTRATION_ISSUE_PLACE,
                        FUEL_ID, VEHICLE_CATEGORY, VEHICLE_BODY_TYPE, COLOR, VEHICLE_BRAND_TYPE,
                        REGISTRATION_NUMBER, COMMERCIAL_DESCRIPTION, CHASSIS_NUMBER,
                        PRODUCTION_YEAR, MAX_WEIGHT, PAYLOAD, VEHICLE_WEIGHT,
                        POWER_WEIGHT_RATIO, SEAT_COUNT, ENGINE_DISPLACEMENT, MAX_POWER,
                        ECO_CHARACTERISTICS, CATALYST, ENGINE_NUMBER
                    ) VALUES (
                        'BG505LM', TO_DATE('2016-08-30', 'YYYY-MM-DD'), 'Tuzla',
                        'BG505LM', TO_DATE('2016-09-05', 'YYYY-MM-DD'), 'Tuzla',
                        1, 1, 12, 'Black', 'Mercedes-Benz S500',
                        'REG017017', 'Mercedes S500 L', 'WDD222176A123456',
                        2016, 2300.00, 500.00, 1800.00,
                        0.20, 5, 4.7, 455.0,
                        'Euro 6', 'Yes', 'ENGMERC017'
                    );

                    -- Vehicle 18 (Truck - Truck) - Cement mixer
                    INSERT INTO VEHICLE (
                        LICENSE_PLATE, FIRST_REGISTRATION_DATE, FIRST_REGISTRATION_PLACE,
                        FIRST_LICENSE_PLATE, REGISTRATION_ISSUE_DATE, REGISTRATION_ISSUE_PLACE,
                        FUEL_ID, VEHICLE_CATEGORY, VEHICLE_BODY_TYPE, COLOR, VEHICLE_BRAND_TYPE,
                        REGISTRATION_NUMBER, COMMERCIAL_DESCRIPTION, CHASSIS_NUMBER,
                        PRODUCTION_YEAR, MAX_WEIGHT, PAYLOAD, VEHICLE_WEIGHT,
                        POWER_WEIGHT_RATIO, SEAT_COUNT, ENGINE_DISPLACEMENT, MAX_POWER,
                        ECO_CHARACTERISTICS, CATALYST, ENGINE_NUMBER
                    ) VALUES (
                        'BG606CM', TO_DATE('2015-12-10', 'YYYY-MM-DD'), 'Brčko',
                        'BG606CM', TO_DATE('2015-12-15', 'YYYY-MM-DD'), 'Brčko',
                        3, 2, 13, 'White', 'Iveco Eurocargo',
                        'REG018018', 'Iveco Mixer 180E28', 'ZCF638000R123456',
                        2015, 18000.00, 10000.00, 8000.00,
                        0.06, 2, 5.9, 280.0,
                        'Euro 5', 'Yes', 'ENGIVC018'
                    );

                    -- Vehicle 19 (Motorcycle - Roadster)
                    INSERT INTO VEHICLE (
                        LICENSE_PLATE, FIRST_REGISTRATION_DATE, FIRST_REGISTRATION_PLACE,
                        FIRST_LICENSE_PLATE, REGISTRATION_ISSUE_DATE, REGISTRATION_ISSUE_PLACE,
                        FUEL_ID, VEHICLE_CATEGORY, VEHICLE_BODY_TYPE, COLOR, VEHICLE_BRAND_TYPE,
                        REGISTRATION_NUMBER, COMMERCIAL_DESCRIPTION, CHASSIS_NUMBER,
                        PRODUCTION_YEAR, MAX_WEIGHT, PAYLOAD, VEHICLE_WEIGHT,
                        POWER_WEIGHT_RATIO, SEAT_COUNT, ENGINE_DISPLACEMENT, MAX_POWER,
                        ECO_CHARACTERISTICS, CATALYST, ENGINE_NUMBER
                    ) VALUES (
                        'BG707RD', TO_DATE('2021-05-15', 'YYYY-MM-DD'), 'Mostar',
                        'BG707RD', TO_DATE('2021-05-20', 'YYYY-MM-DD'), 'Mostar',
                        2, 3, 10, 'Orange', 'Triumph Street Triple',
                        'REG019019', 'Triumph Street Triple RS', 'SMTTRIUMPH123456',
                        2021, 190.00, 90.00, 100.00,
                        0.55, 2, 0.765, 123.0,
                        'Euro 5', 'Yes', 'ENGTRI019'
                    );

                    -- Vehicle 20 (SUV - SUV) - Hybrid
                    INSERT INTO VEHICLE (
                        LICENSE_PLATE, FIRST_REGISTRATION_DATE, FIRST_REGISTRATION_PLACE,
                        FIRST_LICENSE_PLATE, REGISTRATION_ISSUE_DATE, REGISTRATION_ISSUE_PLACE,
                        FUEL_ID, VEHICLE_CATEGORY, VEHICLE_BODY_TYPE, COLOR, VEHICLE_BRAND_TYPE,
                        REGISTRATION_NUMBER, COMMERCIAL_DESCRIPTION, CHASSIS_NUMBER,
                        PRODUCTION_YEAR, MAX_WEIGHT, PAYLOAD, VEHICLE_WEIGHT,
                        POWER_WEIGHT_RATIO, SEAT_COUNT, ENGINE_DISPLACEMENT, MAX_POWER,
                        ECO_CHARACTERISTICS, CATALYST, ENGINE_NUMBER
                    ) VALUES (
                        'BG909HY', TO_DATE('2022-02-18', 'YYYY-MM-DD'), 'Zenica',
                        'BG909HY', TO_DATE('2022-02-20', 'YYYY-MM-DD'), 'Zenica',
                        4, 6, 3, 'Blue', 'Toyota RAV4 Hybrid',
                        'REG021021', 'Toyota RAV4 2.5 Hybrid', 'JTMFB3FV7MD123456',
                        2022, 1850.00, 500.00, 1350.00,
                        0.16, 5, 2.5, 219.0,
                        'Euro 6', 'Yes', 'ENGTOY021'
                    );

                    -- Vehicle 21 (Pickup - Pickup Truck) - American style
                    INSERT INTO VEHICLE (
                        LICENSE_PLATE, FIRST_REGISTRATION_DATE, FIRST_REGISTRATION_PLACE,
                        FIRST_LICENSE_PLATE, REGISTRATION_ISSUE_DATE, REGISTRATION_ISSUE_PLACE,
                        FUEL_ID, VEHICLE_CATEGORY, VEHICLE_BODY_TYPE, COLOR, VEHICLE_BRAND_TYPE,
                        REGISTRATION_NUMBER, COMMERCIAL_DESCRIPTION, CHASSIS_NUMBER,
                        PRODUCTION_YEAR, MAX_WEIGHT, PAYLOAD, VEHICLE_WEIGHT,
                        POWER_WEIGHT_RATIO, SEAT_COUNT, ENGINE_DISPLACEMENT, MAX_POWER,
                        ECO_CHARACTERISTICS, CATALYST, ENGINE_NUMBER
                    ) VALUES (
                        'BG010FT', TO_DATE('2019-07-25', 'YYYY-MM-DD'), 'Bihać',
                        'BG010FT', TO_DATE('2019-07-30', 'YYYY-MM-DD'), 'Bihać',
                        3, 7, 7, 'Black', 'Ford F-150',
                        'REG022022', 'Ford F-150 Raptor', '1FTFW1RG0KFA12345',
                        2019, 3200.00, 1200.00, 2000.00,
                        0.18, 5, 3.5, 450.0,
                        'Euro 5', 'Yes', 'ENGFOR022'
                    );

                    -- Vehicle 22 (Tractor - Truck) - Agricultural
                    INSERT INTO VEHICLE (
                        LICENSE_PLATE, FIRST_REGISTRATION_DATE, FIRST_REGISTRATION_PLACE,
                        FIRST_LICENSE_PLATE, REGISTRATION_ISSUE_DATE, REGISTRATION_ISSUE_PLACE,
                        FUEL_ID, VEHICLE_CATEGORY, VEHICLE_BODY_TYPE, COLOR, VEHICLE_BRAND_TYPE,
                        REGISTRATION_NUMBER, COMMERCIAL_DESCRIPTION, CHASSIS_NUMBER,
                        PRODUCTION_YEAR, MAX_WEIGHT, PAYLOAD, VEHICLE_WEIGHT,
                        POWER_WEIGHT_RATIO, SEAT_COUNT, ENGINE_DISPLACEMENT, MAX_POWER,
                        ECO_CHARACTERISTICS, CATALYST, ENGINE_NUMBER
                    ) VALUES (
                        'BG111TR', TO_DATE('2016-04-30', 'YYYY-MM-DD'), 'Brčko',
                        'BG111TR', TO_DATE('2016-05-05', 'YYYY-MM-DD'), 'Brčko',
                        3, 8, 13, 'Red', 'Fendt 724',
                        'REG023023', 'Fendt 724 Vario', 'WEF7240000123456',
                        2016, 8500.00, 4000.00, 4500.00,
                        0.04, 2, 7.2, 240.0,
                        'Euro 4', 'Yes', 'ENGFEN023'
                    );

                    -- Vehicle 23 (Scooter - Motorcycle) - Electric
                    INSERT INTO VEHICLE (
                        LICENSE_PLATE, FIRST_REGISTRATION_DATE, FIRST_REGISTRATION_PLACE,
                        FIRST_LICENSE_PLATE, REGISTRATION_ISSUE_DATE, REGISTRATION_ISSUE_PLACE,
                        FUEL_ID, VEHICLE_CATEGORY, VEHICLE_BODY_TYPE, COLOR, VEHICLE_BRAND_TYPE,
                        REGISTRATION_NUMBER, COMMERCIAL_DESCRIPTION, CHASSIS_NUMBER,
                        PRODUCTION_YEAR, MAX_WEIGHT, PAYLOAD, VEHICLE_WEIGHT,
                        POWER_WEIGHT_RATIO, SEAT_COUNT, ENGINE_DISPLACEMENT, MAX_POWER,
                        ECO_CHARACTERISTICS, CATALYST, ENGINE_NUMBER
                    ) VALUES (
                        'BG212ES', TO_DATE('2023-01-08', 'YYYY-MM-DD'), 'Mostar',
                        'BG212ES', TO_DATE('2023-01-10', 'YYYY-MM-DD'), 'Mostar',
                        4, 9, 15, 'White', 'Niu NQi GT',
                        'REG024024', 'Niu NQi GT Pro', 'NIU123456789012',
                        2023, 110.00, 70.00, 40.00,
                        0.30, 2, 0.0, 7.0,
                        'Electric', 'No', 'ENGNIU024'
                    );

                    -- Vehicle 24 (Bicycle - Motorcycle) - Mountain e-bike
                    INSERT INTO VEHICLE (
                        LICENSE_PLATE, FIRST_REGISTRATION_DATE, FIRST_REGISTRATION_PLACE,
                        FIRST_LICENSE_PLATE, REGISTRATION_ISSUE_DATE, REGISTRATION_ISSUE_PLACE,
                        FUEL_ID, VEHICLE_CATEGORY, VEHICLE_BODY_TYPE, COLOR, VEHICLE_BRAND_TYPE,
                        REGISTRATION_NUMBER, COMMERCIAL_DESCRIPTION, CHASSIS_NUMBER,
                        PRODUCTION_YEAR, MAX_WEIGHT, PAYLOAD, VEHICLE_WEIGHT,
                        POWER_WEIGHT_RATIO, SEAT_COUNT, ENGINE_DISPLACEMENT, MAX_POWER,
                        ECO_CHARACTERISTICS, CATALYST, ENGINE_NUMBER
                    ) VALUES (
                        'BG313MB', TO_DATE('2022-11-15', 'YYYY-MM-DD'), 'Sarajevo',
                        'BG313MB', TO_DATE('2022-11-20', 'YYYY-MM-DD'), 'Sarajevo',
                        4, 10, 15, 'Green', 'Haibike XDURO Nduro',
                        'REG025025', 'Haibike eMTB 6.0', 'HAI123456789',
                        2022, 28.00, 18.00, 10.00,
                        0.35, 1, 0.0, 0.0,
                        'Electric', 'No', 'MOTORHB025'
                    );

                    COMMIT;

                    SELECT CAST(SYSTIMESTAMP AT TIME ZONE 'UTC' AS DATE) INTO NOW FROM DUAL;

                    INSERT INTO SCRIPT_EXECUTION_HISTORY
                    VALUES (SCRIPT_ID, 'vehicle-seed.sql', NOW);
                    COMMIT;
                END IF;
            END;
        END;
    END;
END;