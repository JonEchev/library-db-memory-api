package com.ceiba.biblioteca.domain.port.service;

import com.ceiba.biblioteca.domain.exception.PrestamoGuestUserException;
import com.ceiba.biblioteca.domain.exception.PrestamoUserTypeNotAllowedException;
import com.ceiba.biblioteca.domain.model.Prestamo;
import com.ceiba.biblioteca.domain.port.repository.PrestamoRepository;
import com.ceiba.biblioteca.utilities.Utilities;

public class PrestamoService {

    private final PrestamoRepository prestamoRepository;

    public PrestamoService(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    public Long saveUserPrestamo(Prestamo prestamo) {

        if (prestamo.getUserType() < 1 || prestamo.getUserType() > 3) {
            throw new PrestamoUserTypeNotAllowedException(Utilities.getValueMessage("msg_user_type_not_allowed"));
        }

        if (prestamo.getUserType() == 3
                && prestamoRepository.existsGuestUserPrestamo(prestamo.getUserIdentification())) {
            throw new PrestamoGuestUserException(
                    Utilities.getValueMessage("msg_exists_user_prestamo_1") + prestamo.getUserIdentification() +
                            Utilities.getValueMessage("msg_exists_user_prestamo_2"));
        }

        return prestamoRepository.savePrestamo(prestamo);

    }

    public Prestamo getIdPrestamo(Long id) {
        return prestamoRepository.getPrestamo(id);
    }

}
