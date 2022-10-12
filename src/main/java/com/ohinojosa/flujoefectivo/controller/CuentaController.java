package com.ohinojosa.flujoefectivo.controller;

import com.ohinojosa.flujoefectivo.dto.CuentaDto;
import com.ohinojosa.flujoefectivo.dto.CuentaRegistroDto;
import com.ohinojosa.flujoefectivo.model.Cuenta;
import com.ohinojosa.flujoefectivo.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;

    /**
     * Servicio para crear una cuenta
     * @param cuenta
     * @return mensaje de respuesta
     */
    @PostMapping(value = "/")
    public ResponseEntity<?> crearCuenta(@RequestBody CuentaRegistroDto cuenta) {
        Cuenta cuentaCreada = this.cuentaService.crearCuenta(new Cuenta(cuenta));
        return new ResponseEntity(cuentaCreada, HttpStatus.CREATED);
    }

    /**
     * Servicio para recuperar el saldo de una cuenta
     * @param numeroCuenta
     * @return Datos de la cuenta con el saldo
     * @throws Exception
     */
    @GetMapping(value = "/{numeroCuenta}")
    public ResponseEntity<?> saldoCuenta(@PathVariable String numeroCuenta)throws Exception{
        Cuenta cuentaCreada = this.cuentaService.obtenerCuenta(numeroCuenta);
        return new ResponseEntity(new CuentaDto(cuentaCreada), HttpStatus.CREATED);
    }
}
