package com.ohinojosa.flujoefectivo.model;


import com.ohinojosa.flujoefectivo.dto.CuentaDto;
import com.ohinojosa.flujoefectivo.dto.CuentaRegistroDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String numeroCuenta;
    private String cliente;
    private double saldo;
    private String moneda;
    private EstadoCuenta estadoCuenta;
    private LocalDateTime fechaRegistro;

    public Cuenta() {
    }

    public Cuenta(CuentaDto cuentaDto) {
        this.numeroCuenta = cuentaDto.getNumeroCuenta();
        this.cliente = cuentaDto.getCliente();
        this.saldo = cuentaDto.getSaldo();
        this.moneda = cuentaDto.getMoneda();
        this.estadoCuenta = cuentaDto.getEstadoCuenta();
        this.fechaRegistro = cuentaDto.getFechaRegistro();
    }
    public Cuenta(CuentaRegistroDto cuentaDto) {
        this.numeroCuenta = cuentaDto.getNumeroCuenta();
        this.cliente = cuentaDto.getCliente();
        this.saldo = cuentaDto.getSaldo();
        this.moneda = cuentaDto.getMoneda();
        this.fechaRegistro = cuentaDto.getFechaRegistro();
    }
}
