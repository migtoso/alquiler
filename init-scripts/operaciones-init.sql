-- Datos iniciales para operaciones_db
-- Este script se ejecuta automaticamente cuando el contenedor de MySQL se inicia por primera vez.

USE operaciones_db;

INSERT INTO alquileres (usuario, vehiculo_id, fecha_inicial, fecha_final) VALUES
	('Carlos Mendoza',      1,  '2026-01-05', '2026-01-10'),
	('Lucia Ramirez',       3,  '2026-01-12', '2026-01-18'),
	('Andres Torres',       6,  '2026-02-01', '2026-02-07'),
	('Valentina Herrera',   8,  '2026-02-14', '2026-02-20'),
	('Jorge Castillo',      11, '2026-03-03', '2026-03-09'),
	('Daniela Morales',     13, '2026-03-15', '2026-03-22'),
	('Sebastian Vargas',    16, '2026-04-01', '2026-04-06'),
	('Camila Gutierrez',    18, '2026-04-10', '2026-04-17'),
	('Ricardo Fuentes',     2,  '2026-05-01', '2026-05-08'),
	('Natalia Ospina',      7,  '2026-05-10', '2026-05-16');
