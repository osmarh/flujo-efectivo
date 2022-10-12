package com.ohinojosa.flujoefectivo.dto;

import com.ohinojosa.flujoefectivo.model.Cuenta;
import com.ohinojosa.flujoefectivo.model.EstadoCuenta;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CuentaDto {
    private String numeroCuenta;
    private String cliente;
    private double saldo;
    private String moneda;
    private EstadoCuenta estadoCuenta;
    private LocalDateTime fechaRegistro;

    public CuentaDto(Cuenta cuenta) {
        this.numeroCuenta = cuenta.getNumeroCuenta();
        this.cliente = cuenta.getCliente();
        this.saldo = cuenta.getSaldo();
        this.moneda = cuenta.getMoneda();
        this.estadoCuenta = cuenta.getEstadoCuenta();
        this.fechaRegistro = cuenta.getFechaRegistro();
    }
}
