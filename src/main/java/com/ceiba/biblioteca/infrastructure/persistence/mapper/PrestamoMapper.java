package com.ceiba.biblioteca.infrastructure.persistence.mapper;

import com.ceiba.biblioteca.domain.model.Prestamo;
import com.ceiba.biblioteca.infrastructure.persistence.entity.PrestamoEntity;

public class PrestamoMapper {

    private PrestamoMapper() {
    }

    public static PrestamoEntity mapToEntity(Prestamo prestamo) {
        return new PrestamoEntity(
                prestamo.getIsbn(),
                prestamo.getUserIdentification(),
                prestamo.getUserType(),
                prestamo.getMaximumReturnDate()
        );
    }

    public static Prestamo mapToDomain(PrestamoEntity prestamoEntity) {

        Prestamo prestamo = new Prestamo(
                prestamoEntity.getIsbn(),
                prestamoEntity.getIdentificacionUsuario(),
                prestamoEntity.getTipoUsuario()
        );

        prestamo.setId(prestamoEntity.getId());

        return prestamo;

    }

}

