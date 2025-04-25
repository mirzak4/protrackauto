BEGIN
    DECLARE SCRIPT_ID RAW(16) := '3273B814DDC30F50E063143410ACD549';
    BEGIN
        DECLARE NOW DATE;
        BEGIN
            DECLARE EXEC_COUNT INT := 0;
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

                    -- Service providers
                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (2, 'Speedy Auto Services', '321 Repair Rd', '555-0201', 'service@speedyauto.com', 'John Taylor');

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (2, 'Elite Car Care', '654 Mechanic Ln', '555-0202', 'contact@elitecare.com', 'Diana Blake');

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (2, 'FixFast Garage', '987 Fixit Blvd', '555-0203', 'help@fixfast.com', 'Omar King');

                    -- Gas Stations
                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (3, 'FuelExpress Station', '111 Fuel way', '555-0301', 'info@fuelexpress.com', 'Jasmine Ford');

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (3, 'GoGas Plus', '222 Petrol Pkwy', '555-0302', 'support@gogasplus.com', 'Nate Adams');

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (3, 'Highway Fuel Hub', '333 Highway Rd', '555-0303', 'hello@fuelhub.com', 'Clara Greene');
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