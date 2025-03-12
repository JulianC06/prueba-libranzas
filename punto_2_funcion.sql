CREATE OR REPLACE FUNCTION obtener_numero_solicitudes(p_id_cliente IN INT) 
RETURN NUMBER 
IS
    v_numero_solicitudes NUMBER;
BEGIN
    SELECT COUNT(*) 
    INTO v_numero_solicitudes
    FROM t01_solicitud
    WHERE id_cliente = p_id_cliente;

    RETURN v_numero_solicitudes;
END obtener_numero_solicitudes;
/
