package com.ceiba.biblioteca.infrastructure.persistence.repository;

import com.ceiba.biblioteca.infrastructure.persistence.entity.PrestamoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestamoRepositoryJpa extends JpaRepository<PrestamoEntity, Long> {

    @Query("SELECT COUNT(p) > 0 FROM PrestamoEntity p WHERE p.identificacionUsuario = :identificacionUsuario AND p.tipoUsuario = 3")
    boolean existePrestamoPorUsuarioInvitado(@Param("identificacionUsuario") String identificacionUsuario);

}
