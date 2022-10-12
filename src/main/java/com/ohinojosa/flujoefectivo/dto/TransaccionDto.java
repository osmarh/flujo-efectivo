package com.ohinojosa.flujoefectivo.dto;

import com.ohinojosa.flujoefectivo.model.Operacion;
import com.ohinojosa.flujoefectivo.model.Transaccion;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TransaccionDto {
    private double monto;
    private String moneda;
    private Operacion operacion;
    private String descripcion;
    private String numeroCuenta;
    private LocalDateTime fechaRegistro;

    public TransaccionDto(Transaccion transaccion) {
        this.monto = transaccion.getMonto();
        this.moneda = transaccion.getMoneda();
        this.operacion = transaccion.getOperacion();
        this.descripcion = transaccion.getDescripcion();
        this.numeroCuenta=transaccion.getCuenta().getNumeroCuenta();
        this.fechaRegistro=transaccion.getFechaRegistro();
    }
}
