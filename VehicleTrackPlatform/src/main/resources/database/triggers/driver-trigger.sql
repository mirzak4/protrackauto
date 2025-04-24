CREATE OR REPLACE TRIGGER trg_driver_audit
    AFTER INSERT OR UPDATE OR DELETE ON driver
    FOR EACH ROW
DECLARE
    v_action VARCHAR2(10);
    v_user VARCHAR2(100);
BEGIN
    IF INSERTING THEN
        v_action := 'INSERT';
        v_user := COALESCE(:NEW.modified_by, USER);
    ELSIF UPDATING THEN
        v_action := 'UPDATE';
        v_user := COALESCE(:NEW.modified_by, USER);
    ELSIF DELETING THEN
        v_action := 'DELETE';
        v_user := COALESCE(:OLD.modified_by, USER);
    END IF;

    INSERT INTO nbp.nbp_log (
        action_name,
        table_name,
        date_time,
        db_user
    )
    VALUES (
               v_action,
               'DRIVER',
               SYSTIMESTAMP,
               v_user
           );
END;