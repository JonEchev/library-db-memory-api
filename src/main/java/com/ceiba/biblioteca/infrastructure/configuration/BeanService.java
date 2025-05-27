package com.ceiba.biblioteca.infrastructure.configuration;

import com.ceiba.biblioteca.aplicacion.handler.HandlerConsultPrestamo;
import com.ceiba.biblioteca.aplicacion.handler.HandlerCreatePrestamo;
import com.ceiba.biblioteca.domain.port.repository.PrestamoRepository;
import com.ceiba.biblioteca.domain.port.service.PrestamoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanService {

    @Bean
    public PrestamoService prestamoService(PrestamoRepository prestamoRepository) {
        return new PrestamoService(prestamoRepository);
    }

    @Bean
    public HandlerCreatePrestamo handlerCreatePrestamo(PrestamoService prestamoService) {
        return new HandlerCreatePrestamo(prestamoService);
    }

    @Bean
    public HandlerConsultPrestamo handlerConsultPrestamo(PrestamoService prestamoService) {
        return new HandlerConsultPrestamo(prestamoService);
    }

}