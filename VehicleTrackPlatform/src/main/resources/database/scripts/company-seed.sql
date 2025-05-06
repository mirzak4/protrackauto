BEGIN
    DECLARE SCRIPT_ID RAW(16) := '3273B814DDC30F50E063143410ACD549';
    BEGIN
        DECLARE NOW DATE;
        BEGIN
            DECLARE EXEC_COUNT INT := 1;
            BEGIN
                SELECT COUNT(1) INTO EXEC_COUNT FROM SCRIPT_EXECUTION_HISTORY WHERE ID = SCRIPT_ID;
                IF EXEC_COUNT = 0 THEN
                    -- Insurance companies
                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (1, 'SecureCover Insurance', '123 Main St', '555-0101', 'contact@securecover.com', 'Alice Johnson');

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (1, 'DriveSafe Insurance', '456 Elm St', '555-0102', 'info@drivesafe.com', 'Robert Smith');

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (1, 'AutoProtect Group', '789 Oak Ave', '555-0103', 'support@autoprotect.com', 'Maria Lopez');

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (1, 'Guardian Shield Insurance', '159 Lakeview Dr', '555-0104', 'info@guardianshield.com', 'Steven Clark');

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (1, 'PrimeAuto Insurance',      '247 Sunset Blvd', '555-0105', 'claims@primeauto.com',    'Linda Nguyen');

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (1, 'MotorGuard Assurance',    '305 Cedar St',     '555-0106', 'support@motorguard.com',  'Alex Rivera');

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (1, 'ShieldWay Insurance',      '159 Harbor Dr',    '555-0107', 'info@shieldway.com',     'Eleanor Brooks');

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (1, 'Zenith Auto Assurance',    '268 Summit Ave',    '555-0108', 'support@zenithauto.com',  'Carlos Mendes');

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (1, 'Velocity Car Insurance',   '485 Speed St',      '555-0110', 'help@velocityinsurance.com', 'Thomas Becker');

                    -- Service providers
                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (2, 'Speedy Auto Services', '321 Repair Rd', '555-0201', 'service@speedyauto.com', 'John Taylor');

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (2, 'Elite Car Care', '654 Mechanic Ln', '555-0202', 'contact@elitecare.com', 'Diana Blake');

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (2, 'FixFast Garage', '987 Fixit Blvd', '555-0203', 'help@fixfast.com', 'Omar King');

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (2, 'QuickFix Auto',       '412 Garage Rd',    '555-0204', 'service@quickfix.com',    'Paula Reed');

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (2, 'ProTune Mechanics',   '528 Engine Ave',   '555-0205', 'contact@protune.com',      'Michael Chen');

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (2, 'UrbanCar Service',    '634 City Blvd',     '555-0206', 'help@urbancarservice.com', 'Sara Patel');

                    -- Gas Stations
                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (3, 'FuelExpress Station', '111 Fuel way', '555-0301', 'info@fuelexpress.com', 'Jasmine Ford');

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (3, 'GoGas Plus', '222 Petrol Pkwy', '555-0302', 'support@gogasplus.com', 'Nate Adams');

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (3, 'Highway Fuel Hub', '333 Highway Rd', '555-0303', 'hello@fuelhub.com', 'Clara Greene');

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (3, 'PetroMax Station', '741 Fuel Pkwy',   '555-0304', 'info@petromax.com',    'Ethan Brooks');

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (3, 'EcoFuel Stop',     '852 Green Rd',     '555-0305', 'support@ecofuelstop.com','Olivia Diaz');

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (3, 'TurboCharge Fuel', '963 Speedway Ln',  '555-0306', 'hello@turbocharge.com', 'Liam Murphy');

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (3, 'RoadRunner Gas',   '174 Desert Hwy',   '555-0307', 'contact@roadrunner.com','Emma Stone');


                    COMMIT;

                    SELECT CAST(SYSTIMESTAMP AT TIME ZONE 'UTC' AS DATE) INTO NOW FROM DUAL;

                    INSERT INTO SCRIPT_EXECUTION_HISTORY
                    VALUES (SCRIPT_ID, 'company-seed.sql', NOW);
                    COMMIT;
                END IF;
            END;
        END;
    END;
END;