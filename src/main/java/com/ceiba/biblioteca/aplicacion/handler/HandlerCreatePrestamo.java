package com.ceiba.biblioteca.aplicacion.handler;

import com.ceiba.biblioteca.aplicacion.command.PrestamoRequest;
import com.ceiba.biblioteca.domain.model.Prestamo;
import com.ceiba.biblioteca.domain.port.service.PrestamoService;

public class HandlerCreatePrestamo {

    private final PrestamoService prestamoService;

    public HandlerCreatePrestamo(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    public Long execute(PrestamoRequest prestamoRequest) {

        Prestamo prestamo = new Prestamo(
                prestamoRequest.getIsbn(),
                prestamoRequest.getIdentificacionUsuario(),
                prestamoRequest.getTipoUsuario()
        );

        return prestamoService.saveUserPrestamo(prestamo);

    }

}