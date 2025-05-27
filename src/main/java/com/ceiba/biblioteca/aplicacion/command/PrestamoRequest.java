package com.ceiba.biblioteca.aplicacion.command;

import lombok.Data;

@Data
public class PrestamoRequest {

    private String isbn;
    private String identificacionUsuario;
    private int tipoUsuario;

    public PrestamoRequest() {
    }

    public PrestamoRequest(String isbn, String identificacionUsuario, int tipoUsuario) {
        this.isbn = isbn;
        this.identificacionUsuario = identificacionUsuario;
        this.tipoUsuario = tipoUsuario;
    }

}