package com.ceiba.biblioteca.domain.port.repository;

import com.ceiba.biblioteca.domain.model.Prestamo;

public interface PrestamoRepository {

    Long savePrestamo(Prestamo prestamo);

    Prestamo getPrestamo(Long id);

    boolean existsGuestUserPrestamo(String userIdentification);

}
