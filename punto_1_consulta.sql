SELECT 
    e.descripcion, 
    s.id_solicitud, 
    s.id_cliente
FROM 
    t01_solicitud s
JOIN 
    t00_estado e ON s.estado = e.id_estado
JOIN 
    t02_cliente c ON s.id_cliente = c.id_cliente
WHERE 
    s.estado = 1
    AND c.fecha_nacimiento = '1900-01-01'
    AND s.fecha_ingreso BETWEEN '2000-01-01' AND '2000-01-31';
