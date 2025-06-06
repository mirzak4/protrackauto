begin
declare test int := 0;
begin
    select 1 INTO test from dual;
end;
end;
-- BEGIN
--     FOR fuel IN (SELECT COLUMN_VALUE AS fuel_id FROM TABLE(SYS.ODCINUMBERLIST(41, 42, 43, 44, 50, 55))) LOOP
--             FOR d IN (
--                 SELECT TO_DATE('2025-01-01', 'YYYY-MM-DD') + 7 * (LEVEL - 1) AS issue_date
--                 FROM dual
--                 CONNECT BY LEVEL <= FLOOR((TO_DATE('2025-07-31', 'YYYY-MM-DD') - TO_DATE('2025-01-01', 'YYYY-MM-DD')) / 7) + 1
--                 ) LOOP
--                     INSERT INTO FUEL_PRICE (
--                         PRICE,
--                         ISSUE_DATE,
--                         FUEL_ID,
--                         GAS_STATION_ID
--                     ) VALUES (
--                                  ROUND(DBMS_RANDOM.VALUE(1.20, 2.20), 2),  -- Simulated price
--                                  d.issue_date,
--                                  fuel.fuel_id,
--                                  156
--                              );
--                 END LOOP;
--         END LOOP;
--     COMMIT;
-- END;