package com.ceiba.biblioteca.infrastructure.controller;

import com.ceiba.biblioteca.aplicacion.command.PrestamoRequest;
import com.ceiba.biblioteca.aplicacion.handler.HandlerConsultPrestamo;
import com.ceiba.biblioteca.aplicacion.handler.HandlerCreatePrestamo;
import com.ceiba.biblioteca.domain.model.Prestamo;
import com.ceiba.biblioteca.utilities.Utilities;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/prestamo")
public class PrestamoController {

    private final HandlerCreatePrestamo handlerCreatePrestamo;
    private final HandlerConsultPrestamo handlerConsultPrestamo;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public PrestamoController(HandlerCreatePrestamo handlerCreatePrestamo,
                              HandlerConsultPrestamo handlerConsultPrestamo) {
        this.handlerCreatePrestamo = handlerCreatePrestamo;
        this.handlerConsultPrestamo = handlerConsultPrestamo;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createBD(@RequestBody @Valid PrestamoRequest prestamoRequest) {

        Long id = handlerCreatePrestamo.execute(prestamoRequest);
        Prestamo prestamo = handlerConsultPrestamo.execute(id);

        Map<String, Object> response = new HashMap<>();
        response.put("id", id);
        response.put("fechaMaximaDevolucion", formatter.format(prestamo.getMaximumReturnDate()));

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("{id-prestamo}")
    public ResponseEntity<Map<String, Object>> getId(@PathVariable("id-prestamo") Long idPrestamo) {

        Prestamo prestamo = handlerConsultPrestamo.execute(idPrestamo);
        Map<String, Object> response = new HashMap<>();

        if (prestamo != null) {

            response.put("id", prestamo.getId());
            response.put("isbn", prestamo.getIsbn());
            response.put("identificacionUsuario", prestamo.getUserIdentification());
            response.put("tipoUsuario", prestamo.getUserType());
            response.put("fechaMaximaDevolucion", formatter.format(prestamo.getMaximumReturnDate()));

            return new ResponseEntity<>(response, HttpStatus.OK);

        } else {
            response.put("mensaje", Utilities.getValueMessage("msg_data_not_found"));
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

    }

}