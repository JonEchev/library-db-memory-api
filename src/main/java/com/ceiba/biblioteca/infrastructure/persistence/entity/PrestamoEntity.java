package com.ceiba.biblioteca.infrastructure.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "prestamo")
public class PrestamoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    @Size(max = 10, message = "El tamaño máximo del campo isbn es 10 caracteres.")
    private String isbn;

    @Column(name = "identificacion_usuario", nullable = false, length = 10)
    @Size(max = 10, message = "El tamaño máximo del campo identificacionUsuario es 10 caracteres.")
    private String identificacionUsuario;

    @Column(name = "tipo_usuario", nullable = false)
    private int tipoUsuario;

    @Column(name = "fecha_maxima_devolucion", nullable = false)
    private LocalDate fechaMaximaDevolucion;

    public PrestamoEntity() {
    }

    public PrestamoEntity(String isbn, String userIdentification, int userType, LocalDate maximumReturnDate) {
        this.isbn = isbn;
        this.identificacionUsuario = userIdentification;
        this.tipoUsuario = userType;
        this.fechaMaximaDevolucion = maximumReturnDate;
    }

}