package com.ohinojosa.flujoefectivo.model;

import com.ohinojosa.flujoefectivo.dto.TransaccionDto;
import com.ohinojosa.flujoefectivo.dto.TransaccionRegistroDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cuenta cuenta;

    private double monto;
    private String moneda;
    private Operacion operacion;
    private String descripcion;
    private LocalDateTime fechaRegistro;

    public Transaccion() {
    }

    public Transaccion(TransaccionDto transaccionDto) {
        this.monto = transaccionDto.getMonto();
        this.moneda = transaccionDto.getMoneda();
        this.operacion = transaccionDto.getOperacion();
        this.descripcion = transaccionDto.getDescripcion();
        this.getCuenta().setNumeroCuenta(transaccionDto.getNumeroCuenta());
        this.fechaRegistro=transaccionDto.getFechaRegistro();
    }
    public Transaccion(TransaccionRegistroDto transaccionDto, String numeroCuenta) {
        this.monto = transaccionDto.getMonto();
        this.moneda = transaccionDto.getMoneda();
        this.descripcion = transaccionDto.getDescripcion();
        this.cuenta=new Cuenta();
        this.cuenta.setNumeroCuenta(numeroCuenta);
    }
}
