CREATE DATABASE SACProjeto_DB;

USE SACProjeto_DB;

CREATE TABLE feriado (
    dia DATE NOT NULL,
    descricao VARCHAR(50) NOT NULL,
    PRIMARY KEY (dia)
);

INSERT INTO feriado (dia, descricao) VALUES
('2025-01-01', 'Confraternização Universal'),
('2025-04-18', 'Sexta-feira Santa'),
('2025-04-21', 'Tiradentes'),
('2025-05-01', 'Dia do Trabalhador'),
('2025-09-07', 'Independência do Brasil'),
('2025-10-12', 'Nossa Senhora Aparecida'),
('2025-11-02', 'Finados'),
('2025-11-15', 'Proclamação da República'),
('2025-11-20', 'Dia Nacional de Zumbi e da Consciência Negra'),
('2025-12-25', 'Natal');

SELECT * FROM feriado;