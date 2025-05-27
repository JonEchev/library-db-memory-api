package com.ceiba.biblioteca.infrastructure.persistence.repository;

import com.ceiba.biblioteca.domain.model.Prestamo;
import com.ceiba.biblioteca.domain.port.repository.PrestamoRepository;
import com.ceiba.biblioteca.infrastructure.persistence.entity.PrestamoEntity;
import com.ceiba.biblioteca.infrastructure.persistence.mapper.PrestamoMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PrestamoRepositoryImpl implements PrestamoRepository {

    private final PrestamoRepositoryJpa prestamoRepositoryJpa;

    public PrestamoRepositoryImpl(PrestamoRepositoryJpa prestamoRepositoryJpa) {
        this.prestamoRepositoryJpa = prestamoRepositoryJpa;
    }

    @Override
    public Long savePrestamo(Prestamo prestamo) {
        PrestamoEntity prestamoEntity = PrestamoMapper.mapToEntity(prestamo);
        prestamoEntity = prestamoRepositoryJpa.save(prestamoEntity);
        return prestamoEntity.getId();
    }

    @Override
    public Prestamo getPrestamo(Long id) {
        return prestamoRepositoryJpa.findById(id)
                .map(PrestamoMapper::mapToDomain)
                .orElse(null);
    }

    @Override
    public boolean existsGuestUserPrestamo(String identificacionUsuario) {
        return prestamoRepositoryJpa.existePrestamoPorUsuarioInvitado(identificacionUsuario);
    }

}
