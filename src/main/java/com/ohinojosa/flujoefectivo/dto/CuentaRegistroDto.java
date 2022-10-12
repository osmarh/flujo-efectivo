package com.ohinojosa.flujoefectivo.dto;

import com.ohinojosa.flujoefectivo.model.Cuenta;
import com.ohinojosa.flujoefectivo.model.EstadoCuenta;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CuentaRegistroDto {
    private String numeroCuenta;
    private String cliente;
    private double saldo;
    private String moneda;
    private LocalDateTime fechaRegistro;
}
