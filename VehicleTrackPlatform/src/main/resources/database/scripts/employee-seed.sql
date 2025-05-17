BEGIN
    DECLARE SCRIPT_ID RAW(16) := '338CA767D6FC9AADE063143410AC4F0E';
    BEGIN
        DECLARE NOW DATE;
        BEGIN
            DECLARE EXEC_COUNT INT := 0;
            BEGIN
                SELECT COUNT(1) INTO EXEC_COUNT FROM SCRIPT_EXECUTION_HISTORY WHERE ID = SCRIPT_ID;
                IF EXEC_COUNT = 0 THEN

                    DECLARE
                        CLAIMS_ADJUSTER_ID INTEGER;
                        FIELD_TECHNICIAN_ID INTEGER;
                        STATION_MANAGER_ID INTEGER;
                        USER_ID INTEGER;
                        COMPANY_ID INTEGER;
                    BEGIN
                        SELECT ID INTO CLAIMS_ADJUSTER_ID FROM nbp.nbp_role WHERE NAME = 'NBP04.CLAIMS_ADJUSTER';
                        SELECT ID INTO FIELD_TECHNICIAN_ID FROM nbp.nbp_role WHERE NAME = 'NBP04.FIELD_TECHNICIAN';
                        SELECT ID INTO STATION_MANAGER_ID FROM nbp.nbp_role WHERE NAME = 'NBP04.STATION_MANAGER';

                        -- Employee in Insurance Company
                        -- SecureCover Insurance
                        SELECT ID INTO COMPANY_ID FROM COMPANY WHERE NAME = 'SecureCover Insurance' AND COMPANY_TYPE = 1;
                        INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                        VALUES ('Adna', 'Mehanovic', 'amehanovic@securecover.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'amehanovic', '111-333', TO_DATE('2000-04-20', 'YYYY-MM-DD'), CLAIMS_ADJUSTER_ID)
                        RETURNING ID INTO USER_ID;
                        INSERT INTO EMPLOYEE (COMPANY_ID, USER_ID) VALUES (COMPANY_ID, USER_ID);

                        -- DriveSafe Insurance
                        SELECT ID INTO COMPANY_ID FROM COMPANY WHERE NAME = 'DriveSafe Insurance' AND COMPANY_TYPE = 1;
                        INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                        VALUES ('Dženan', 'Kovačević', 'dkovacevic@drivesafe.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'dkovacevic', '555-0201', TO_DATE('1979-05-14', 'YYYY-MM-DD'), CLAIMS_ADJUSTER_ID)
                        RETURNING ID INTO USER_ID;
                        INSERT INTO EMPLOYEE (COMPANY_ID, USER_ID) VALUES (COMPANY_ID, USER_ID);

                        -- AutoProtect Group
                        SELECT ID INTO COMPANY_ID FROM COMPANY WHERE NAME = 'AutoProtect Group' AND COMPANY_TYPE = 1;
                        INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                        VALUES ('Amra', 'Zukić', 'azukic@autoprotect.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'azukic', '555-0301', TO_DATE('1980-01-15', 'YYYY-MM-DD'), CLAIMS_ADJUSTER_ID)
                        RETURNING ID INTO USER_ID;
                        INSERT INTO EMPLOYEE (COMPANY_ID, USER_ID) VALUES (COMPANY_ID, USER_ID);

                        -- Guardian Shield Insurance
                        SELECT ID INTO COMPANY_ID FROM COMPANY WHERE NAME = 'Guardian Shield Insurance' AND COMPANY_TYPE = 1;
                        INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                        VALUES ('Emir', 'Hodžić', 'ehodzic@guardianshield.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'ehodzic', '555-0104', TO_DATE('1982-03-10', 'YYYY-MM-DD'), CLAIMS_ADJUSTER_ID)
                        RETURNING ID INTO USER_ID;
                        INSERT INTO EMPLOYEE (COMPANY_ID, USER_ID) VALUES (COMPANY_ID, USER_ID);

                        -- PrimeAuto Insurance
                        SELECT ID INTO COMPANY_ID FROM COMPANY WHERE NAME = 'PrimeAuto Insurance' AND COMPANY_TYPE = 1;
                        INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                        VALUES ('Lejla', 'Kovačević', 'lkovacevic@primeauto.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'lkovacevic', '555-0105', TO_DATE('1990-09-05', 'YYYY-MM-DD'), CLAIMS_ADJUSTER_ID)
                        RETURNING ID INTO USER_ID;
                        INSERT INTO EMPLOYEE (COMPANY_ID, USER_ID) VALUES (COMPANY_ID, USER_ID);

                        -- MotorGuard Assurance
                        SELECT ID INTO COMPANY_ID FROM COMPANY WHERE NAME = 'MotorGuard Assurance' AND COMPANY_TYPE = 1;
                        INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                        VALUES ('Admir', 'Softić', 'asoftic@motorguard.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'asoftic', '555-0106', TO_DATE('1987-12-18', 'YYYY-MM-DD'), CLAIMS_ADJUSTER_ID)
                        RETURNING ID INTO USER_ID;
                        INSERT INTO EMPLOYEE (COMPANY_ID, USER_ID) VALUES (COMPANY_ID, USER_ID);

                        -- ShieldWay Insurance
                        SELECT ID INTO COMPANY_ID FROM COMPANY WHERE NAME = 'ShieldWay Insurance' AND COMPANY_TYPE = 1;
                        INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                        VALUES ('Emina', 'Zukić', 'ezukic@shieldway.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'ezukic', '555-0107', TO_DATE('1975-06-30', 'YYYY-MM-DD'), CLAIMS_ADJUSTER_ID)
                        RETURNING ID INTO USER_ID;
                        INSERT INTO EMPLOYEE (COMPANY_ID, USER_ID) VALUES (COMPANY_ID, USER_ID);

                        -- Zenith Auto Assurance
                        SELECT ID INTO COMPANY_ID FROM COMPANY WHERE NAME = 'Zenith Auto Assurance' AND COMPANY_TYPE = 1;
                        INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                        VALUES ('Kenan', 'Muratović', 'kmuratovic@zenithauto.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'kmuratovic', '555-0108', TO_DATE('1983-04-25', 'YYYY-MM-DD'), CLAIMS_ADJUSTER_ID)
                        RETURNING ID INTO USER_ID;
                        INSERT INTO EMPLOYEE (COMPANY_ID, USER_ID) VALUES (COMPANY_ID, USER_ID);

                        -- Velocity Car Insurance
                        SELECT ID INTO COMPANY_ID FROM COMPANY WHERE NAME = 'Velocity Car Insurance' AND COMPANY_TYPE = 1;
                        INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                        VALUES ('Dženita', 'Hasanović', 'dhasanovic@velocityinsurance.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'dhasanovic', '555-0110', TO_DATE('1988-08-12', 'YYYY-MM-DD'), CLAIMS_ADJUSTER_ID)
                        RETURNING ID INTO USER_ID;
                        INSERT INTO EMPLOYEE (COMPANY_ID, USER_ID) VALUES (COMPANY_ID, USER_ID);

                        -- Service Providers Employees
                        -- Speedy Auto Services
                        SELECT ID INTO COMPANY_ID FROM COMPANY WHERE NAME = 'Speedy Auto Services' AND COMPANY_TYPE = 2;
                        INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                        VALUES ('Mirza', 'Kadrić', 'mkadric@speedyauto.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'mkadric', '555-0102', TO_DATE('1985-07-15', 'YYYY-MM-DD'), FIELD_TECHNICIAN_ID)
                        RETURNING ID INTO USER_ID;
                        INSERT INTO EMPLOYEE (COMPANY_ID, USER_ID) VALUES (COMPANY_ID, USER_ID);

                        -- Elite Car Care
                        SELECT ID INTO COMPANY_ID FROM COMPANY WHERE NAME = 'Elite Car Care' AND COMPANY_TYPE = 2;
                        INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                        VALUES ('Adnan', 'Hadžić', 'ahadzic@elitecare.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'ahadzic', '555-0202', TO_DATE('1984-02-28', 'YYYY-MM-DD'), FIELD_TECHNICIAN_ID)
                        RETURNING ID INTO USER_ID;
                        INSERT INTO EMPLOYEE (COMPANY_ID, USER_ID) VALUES (COMPANY_ID, USER_ID);

                        -- FixFast Garage
                        SELECT ID INTO COMPANY_ID FROM COMPANY WHERE NAME = 'FixFast Garage' AND COMPANY_TYPE = 2;
                        INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                        VALUES ('Nermin', 'Hodžić', 'nhodzic@fixfast.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'nhodzic', '555-0203', TO_DATE('1981-07-19', 'YYYY-MM-DD'), FIELD_TECHNICIAN_ID)
                        RETURNING ID INTO USER_ID;
                        INSERT INTO EMPLOYEE (COMPANY_ID, USER_ID) VALUES (COMPANY_ID, USER_ID);

                        -- QuickFix Auto
                        SELECT ID INTO COMPANY_ID FROM COMPANY WHERE NAME = 'QuickFix Auto' AND COMPANY_TYPE = 2;
                        INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                        VALUES ('Amar', 'Zulić', 'azulic@quickfix.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'azulic', '555-0204', TO_DATE('1986-11-08', 'YYYY-MM-DD'), FIELD_TECHNICIAN_ID)
                        RETURNING ID INTO USER_ID;
                        INSERT INTO EMPLOYEE (COMPANY_ID, USER_ID) VALUES (COMPANY_ID, USER_ID);

                        -- ProTune Mechanics
                        SELECT ID INTO COMPANY_ID FROM COMPANY WHERE NAME = 'ProTune Mechanics' AND COMPANY_TYPE = 2;
                        INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                        VALUES ('Haris', 'Muratović', 'hmuratovic@protune.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'hmuratovic', '555-0205', TO_DATE('1983-09-17', 'YYYY-MM-DD'), FIELD_TECHNICIAN_ID)
                        RETURNING ID INTO USER_ID;
                        INSERT INTO EMPLOYEE (COMPANY_ID, USER_ID) VALUES (COMPANY_ID, USER_ID);

                        -- UrbanCar Service
                        SELECT ID INTO COMPANY_ID FROM COMPANY WHERE NAME = 'UrbanCar Service' AND COMPANY_TYPE = 2;
                        INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                        VALUES ('Edin', 'Hasanović', 'ehasanovic@urbancarservice.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'ehasanovic', '555-0206', TO_DATE('1989-04-03', 'YYYY-MM-DD'), FIELD_TECHNICIAN_ID)
                        RETURNING ID INTO USER_ID;
                        INSERT INTO EMPLOYEE (COMPANY_ID, USER_ID) VALUES (COMPANY_ID, USER_ID);

                        -- Gas Stations Employees
                        -- FuelExpress Station
                        SELECT ID INTO COMPANY_ID FROM COMPANY WHERE NAME = 'FuelExpress Station' AND COMPANY_TYPE = 3;
                        INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                        VALUES ('Amina', 'Pandžić', 'apandzic@fuelexpress.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'apandzic', '555-0103', TO_DATE('1988-11-22', 'YYYY-MM-DD'), STATION_MANAGER_ID)
                        RETURNING ID INTO USER_ID;
                        INSERT INTO EMPLOYEE (COMPANY_ID, USER_ID) VALUES (COMPANY_ID, USER_ID);

                        -- GoGas Plus
                        SELECT ID INTO COMPANY_ID FROM COMPANY WHERE NAME = 'GoGas Plus' AND COMPANY_TYPE = 3;
                        INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                        VALUES ('Sead', 'Muratović', 'smuratovic@gogasplus.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'smuratovic', '555-0302', TO_DATE('1985-10-22', 'YYYY-MM-DD'), STATION_MANAGER_ID)
                        RETURNING ID INTO USER_ID;
                        INSERT INTO EMPLOYEE (COMPANY_ID, USER_ID) VALUES (COMPANY_ID, USER_ID);

                        -- Highway Fuel Hub
                        SELECT ID INTO COMPANY_ID FROM COMPANY WHERE NAME = 'Highway Fuel Hub' AND COMPANY_TYPE = 3;
                        INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                        VALUES ('Selma', 'Zukić', 'szukic@fuelhub.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'szukic', '555-0303', TO_DATE('1977-08-09', 'YYYY-MM-DD'), STATION_MANAGER_ID)
                        RETURNING ID INTO USER_ID;
                        INSERT INTO EMPLOYEE (COMPANY_ID, USER_ID) VALUES (COMPANY_ID, USER_ID);

                        -- PetroMax Station
                        SELECT ID INTO COMPANY_ID FROM COMPANY WHERE NAME = 'PetroMax Station' AND COMPANY_TYPE = 3;
                        INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                        VALUES ('Davor', 'Kolak', 'dkolak@petromax.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'dkolak', '555-0304', TO_DATE('1982-12-14', 'YYYY-MM-DD'), STATION_MANAGER_ID)
                        RETURNING ID INTO USER_ID;
                        INSERT INTO EMPLOYEE (COMPANY_ID, USER_ID) VALUES (COMPANY_ID, USER_ID);

                        -- EcoFuel Stop
                        SELECT ID INTO COMPANY_ID FROM COMPANY WHERE NAME = 'EcoFuel Stop' AND COMPANY_TYPE = 3;
                        INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                        VALUES ('Lamija', 'Hadžić', 'lhadzic@ecofuelstop.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'lhadzic', '555-0305', TO_DATE('1987-06-27', 'YYYY-MM-DD'), STATION_MANAGER_ID)
                        RETURNING ID INTO USER_ID;
                        INSERT INTO EMPLOYEE (COMPANY_ID, USER_ID) VALUES (COMPANY_ID, USER_ID);

                        -- TurboCharge Fuel
                        SELECT ID INTO COMPANY_ID FROM COMPANY WHERE NAME = 'TurboCharge Fuel' AND COMPANY_TYPE = 3;
                        INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                        VALUES ('Alen', 'Karišik', 'akarisik@turbocharge.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'akarisik', '555-0306', TO_DATE('1984-03-18', 'YYYY-MM-DD'), STATION_MANAGER_ID)
                        RETURNING ID INTO USER_ID;
                        INSERT INTO EMPLOYEE (COMPANY_ID, USER_ID) VALUES (COMPANY_ID, USER_ID);

                        -- RoadRunner Gas
                        SELECT ID INTO COMPANY_ID FROM COMPANY WHERE NAME = 'RoadRunner Gas' AND COMPANY_TYPE = 3;
                        INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                        VALUES ('Dijana', 'Tomić', 'dtomic@roadrunner.com', '$2a$12$JhweS6xXjWMgmTcVcUDcpuPA4K8jKWCq651hrQvfxsS6/MuzJOaVq', 'dtomic', '555-0307', TO_DATE('1989-07-23', 'YYYY-MM-DD'), STATION_MANAGER_ID)
                        RETURNING ID INTO USER_ID;
                        INSERT INTO EMPLOYEE (COMPANY_ID, USER_ID) VALUES (COMPANY_ID, USER_ID);
                    END;

                    COMMIT;

                    SELECT CAST(SYSTIMESTAMP AT TIME ZONE 'UTC' AS DATE) INTO NOW FROM DUAL;

                    INSERT INTO SCRIPT_EXECUTION_HISTORY
                    VALUES (SCRIPT_ID, 'employee-seed.sql', NOW);
                    COMMIT;
                END IF;
            END;
        END;
    END;
END;