CREATE OR REPLACE VIEW NBP04.VW_WEEKLY_AVG_FUEL_PRICE AS
SELECT
    c.ID AS CompanyId,
    c.NAME AS CompanyName,
    f.ID AS FuelId,
    f.NAME AS FuelName,
    -- Extract ISO year and week from ISSUE_DATE
    TO_CHAR(fp.ISSUE_DATE, 'IYYY') AS Year,
    TO_CHAR(fp.ISSUE_DATE, 'IW') AS Week,
    ROUND(AVG(fp.PRICE), 2) AS AvgPrice
FROM
    NBP04.FUEL_PRICE fp
        INNER JOIN NBP04.COMPANY c
                   ON fp.GAS_STATION_ID = c.ID
        INNER JOIN NBP04.FUEL f
                   ON fp.FUEL_ID = f.ID
GROUP BY
    c.ID, c.NAME,
    f.ID, f.NAME,
    TO_CHAR(fp.ISSUE_DATE, 'IYYY'),
    TO_CHAR(fp.ISSUE_DATE, 'IW');
