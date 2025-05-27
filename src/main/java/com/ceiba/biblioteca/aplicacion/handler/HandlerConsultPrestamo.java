package com.ceiba.biblioteca.aplicacion.handler;

import com.ceiba.biblioteca.domain.model.Prestamo;
import com.ceiba.biblioteca.domain.port.service.PrestamoService;

public class HandlerConsultPrestamo {

    private final PrestamoService prestamoService;

    public HandlerConsultPrestamo(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    public Prestamo execute(Long id) {
        return prestamoService.getIdPrestamo(id);
    }

}