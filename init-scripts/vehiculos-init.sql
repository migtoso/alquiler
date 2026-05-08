-- Datos iniciales para vehiculos_db
-- Este script se ejecuta automáticamente cuando el contenedor de MySQL se inicia por primera vez.

USE vehiculos_db;

INSERT INTO vehiculos (marca, modelo, anio, placa, estado) VALUES
  ('Mazda',      'Mazda3',       2020, 'ABC-101', 'DISPONIBLE'),
  ('Mazda',      'CX-5',         2021, 'BCD-202', 'DISPONIBLE'),
  ('Mazda',      'CX-30',        2022, 'CEF-303', 'DISPONIBLE'),
  ('Mazda',      'Mazda6',       2019, 'DFG-404', 'DISPONIBLE'),
  ('Mazda',      'MX-5 Miata',   2023, 'EGH-505', 'NO_DISPONIBLE'),
  ('Toyota',     'Corolla',      2021, 'FHI-606', 'DISPONIBLE'),
  ('Toyota',     'Camry',        2020, 'GIJ-707', 'DISPONIBLE'),
  ('Toyota',     'RAV4',         2022, 'HJK-808', 'DISPONIBLE'),
  ('Toyota',     'Hilux',        2021, 'IJK-909', 'DISPONIBLE'),
  ('Toyota',     'Land Cruiser', 2023, 'JKL-010', 'NO_DISPONIBLE'),
  ('Subaru',     'Impreza',      2020, 'KLM-111', 'DISPONIBLE'),
  ('Subaru',     'Forester',     2021, 'LMN-222', 'DISPONIBLE'),
  ('Subaru',     'Outback',      2022, 'MNO-333', 'DISPONIBLE'),
  ('Subaru',     'XV Crosstrek', 2019, 'NOP-444', 'DISPONIBLE'),
  ('Subaru',     'WRX',          2023, 'OPQ-555', 'NO_DISPONIBLE'),
  ('Mitsubishi', 'Lancer',       2020, 'PQR-666', 'DISPONIBLE'),
  ('Mitsubishi', 'Outlander',    2021, 'QRS-777', 'DISPONIBLE'),
  ('Mitsubishi', 'Eclipse Cross',2022, 'RST-888', 'DISPONIBLE'),
  ('Mitsubishi', 'L200',         2020, 'STU-999', 'DISPONIBLE'),
  ('Mitsubishi', 'Montero',      2021, 'TUV-123', 'DISPONIBLE');
