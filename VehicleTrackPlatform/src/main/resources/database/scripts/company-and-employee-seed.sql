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
                    DECLARE SECURE_COVER_INSURANCE_ID INTEGER;
                    BEGIN
                        INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                            (1, 'SecureCover Insurance', '123 Main St', '555-0101', 'contact@securecover.com', 'Alice Johnson')
                        RETURNING ID INTO SECURE_COVER_INSURANCE_ID;

                        DECLARE CLAIMS_ADJUSTER_ROLE_ID INTEGER;
                        BEGIN
                            -- Employee in Insurance Company
                            SELECT ID INTO CLAIMS_ADJUSTER_ROLE_ID FROM nbp.nbp_role
                            WHERE NAME = 'NBP04.CLAIMS_ADJUSTER';

                            DECLARE ADNA_USER_ID INTEGER;
                            BEGIN
                                INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                                VALUES ('Adna', 'Mehanovic', 'amehanovic@nbp.com', 'pass_123', 'amehanovic', '111-333', TO_DATE('2000-04-20', 'YYYY-MM-DD'), CLAIMS_ADJUSTER_ROLE_ID)
                                RETURNING ID INTO ADNA_USER_ID;

                                INSERT INTO NBP04.employee (COMPANY_ID, USER_ID) VALUES (SECURE_COVER_INSURANCE_ID, ADNA_USER_ID);
                            END;
                        END;
                    END;

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (1, 'DriveSafe Insurance', '456 Elm St', '555-0102', 'info@drivesafe.com', 'Robert Smith');

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (1, 'AutoProtect Group', '789 Oak Ave', '555-0103', 'support@autoprotect.com', 'Maria Lopez');

                    -- Service providers
                    DECLARE SPEEDY_AUTO_SERVICES_ID INTEGER;
                    BEGIN
                        INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                            (2, 'Speedy Auto Services', '321 Repair Rd', '555-0201', 'service@speedyauto.com', 'John Taylor')
                        RETURNING ID INTO SPEEDY_AUTO_SERVICES_ID;

                        DECLARE FIELD_TECHNICIAN_ROLE_ID INTEGER;
                        BEGIN
                            -- Employee in Service Provider Company
                            SELECT ID INTO FIELD_TECHNICIAN_ROLE_ID FROM nbp.nbp_role
                            WHERE NAME = 'NBP04.FIELD_TECHNICIAN';

                            DECLARE MIRZA_USER_ID INTEGER;
                            BEGIN
                                INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                                VALUES ('Mirza', 'Kadric', 'mkadric@nbp.com', 'pass_123', 'mkadric', '111-333', TO_DATE('2000-04-20', 'YYYY-MM-DD'), FIELD_TECHNICIAN_ROLE_ID)
                                RETURNING ID INTO MIRZA_USER_ID;

                                INSERT INTO NBP04.employee (COMPANY_ID, USER_ID) VALUES (SPEEDY_AUTO_SERVICES_ID, MIRZA_USER_ID);
                            END;
                        END;
                    END;

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (2, 'Elite Car Care', '654 Mechanic Ln', '555-0202', 'contact@elitecare.com', 'Diana Blake');

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (2, 'FixFast Garage', '987 Fixit Blvd', '555-0203', 'help@fixfast.com', 'Omar King');

                    -- Gas stations
                    DECLARE FUEL_EXPRESS_STATION_ID INTEGER;
                    BEGIN
                        -- Employee in Gas Station Company
                        INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                            (3, 'FuelExpress Station', '111 Fuel way', '555-0301', 'info@fuelexpress.com', 'Jasmine Ford')
                        RETURNING ID INTO FUEL_EXPRESS_STATION_ID;

                        DECLARE STATION_MANAGER_ROLE_ID INTEGER;
                        BEGIN
                            -- Employee in Service Provider Company
                            SELECT ID INTO STATION_MANAGER_ROLE_ID FROM nbp.nbp_role
                            WHERE NAME = 'NBP04.STATION_MANAGER';

                            DECLARE AMINAP_USER_ID INTEGER;
                            BEGIN
                                INSERT INTO nbp.nbp_user (FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, USERNAME, PHONE_NUMBER, BIRTH_DATE, ROLE_ID)
                                VALUES ('Amina', 'Pandzic', 'apandzic@nbp.com', 'pass_123', 'apandzic', '111-333', TO_DATE('2000-04-20', 'YYYY-MM-DD'), STATION_MANAGER_ROLE_ID)
                                RETURNING ID INTO AMINAP_USER_ID;

                                INSERT INTO NBP04.employee (COMPANY_ID, USER_ID) VALUES (FUEL_EXPRESS_STATION_ID, AMINAP_USER_ID);
                            END;
                        END;
                    END;

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (3, 'GoGas Plus', '222 Petrol Pkwy', '555-0302', 'support@gogasplus.com', 'Nate Adams');

                    INSERT INTO company (COMPANY_TYPE, NAME, ADDRESS, PHONE_NUMBER, EMAIL, CONTACT_PERSON) VALUES
                        (3, 'Highway Fuel Hub', '333 Highway Rd', '555-0303', 'hello@fuelhub.com', 'Clara Greene');
                    COMMIT;

                    SELECT CAST(SYSTIMESTAMP AT TIME ZONE 'UTC' AS DATE) INTO NOW FROM DUAL;

                    INSERT INTO SCRIPT_EXECUTION_HISTORY
                    VALUES (SCRIPT_ID, 'company-and-employee-seed.sql', NOW);
                    COMMIT;
                END IF;
            END;
        END;
    END;
END;