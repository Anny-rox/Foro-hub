CREATE TABLE topicos (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL UNIQUE,
    mensaje TEXT NOT NULL UNIQUE,
    fecha_de_creacion TIMESTAMP NOT NULL DEFAULT NOW(),
    fecha_de_ultima_actualizacion TIMESTAMP NOT NULL DEFAULT NOW(),
    status BOOLEAN NOT NULL DEFAULT TRUE,
    autor VARCHAR(100) NOT NULL,
    curso VARCHAR(100) NOT NULL
);
