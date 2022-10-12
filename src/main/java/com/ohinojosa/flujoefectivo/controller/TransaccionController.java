package com.ohinojosa.flujoefectivo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ohinojosa.flujoefectivo.dto.TransaccionDto;
import com.ohinojosa.flujoefectivo.dto.TransaccionRegistroDto;
import com.ohinojosa.flujoefectivo.model.Cuenta;
import com.ohinojosa.flujoefectivo.model.Transaccion;
import com.ohinojosa.flujoefectivo.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transaccion")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    private static final ObjectMapper mapper = new ObjectMapper();
    /**
     * Servicio para registrar un retiro de una cuenta
     * @param transaccion
     * @return datos de la transacción realizada
     * @throws Exception
     */
    @PostMapping(value = "/retiro/{numeroCuenta}")
    public ResponseEntity<?> registrarRetiro(@RequestBody TransaccionRegistroDto transaccion,@PathVariable String numeroCuenta) throws Exception {
        Transaccion transaccionCreada = this.transaccionService.retiro(new Transaccion(transaccion,numeroCuenta));
        return new ResponseEntity(transaccionCreada, HttpStatus.CREATED);
    }

    /**
     * Serivicio para registrar un depósito enunca cuenta
     * @param transaccion
     * @param numeroCuenta
     * @return datos de la transacción realizada
     * @throws Exception
     */
    @PostMapping(value = "/deposito/{numeroCuenta}")
    public ResponseEntity<?> registrarDeposito(@RequestBody TransaccionRegistroDto transaccion,@PathVariable String numeroCuenta) throws Exception {
        Transaccion transaccionCreada = this.transaccionService.deposito(new Transaccion(transaccion,numeroCuenta));
        return new ResponseEntity(transaccionCreada, HttpStatus.CREATED);
    }

    /**
     * Serivicio para recuperar las operaciones realizadas en una cuenta
     * @param numeroCuenta
     * @return lista de operaciones
     * @throws Exception
     */
    @GetMapping(value = "/transacciones/{numeroCuenta}")
    public ResponseEntity<?> consultaTransacciones(@PathVariable String numeroCuenta) throws Exception {
        List<TransaccionDto> transaccionDtos = new ArrayList<>();
        for (Transaccion transaccion : this.transaccionService.transacciones(numeroCuenta)) {
            transaccionDtos.add(new TransaccionDto(transaccion));
        }
        return new ResponseEntity(transaccionDtos, HttpStatus.OK);
    }
}
