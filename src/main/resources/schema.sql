CREATE TABLE prestamo
(
    id                      BIGINT AUTO_INCREMENT PRIMARY KEY,
    isbn                    VARCHAR(10) NOT NULL,
    identificacion_usuario  VARCHAR(10) NOT NULL,
    tipo_usuario            INT         NOT NULL,
    fecha_maxima_devolucion DATE        NOT NULL
);