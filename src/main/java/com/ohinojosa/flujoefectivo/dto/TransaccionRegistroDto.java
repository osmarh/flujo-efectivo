package com.ohinojosa.flujoefectivo.dto;

import com.ohinojosa.flujoefectivo.model.Transaccion;
import lombok.Getter;

@Getter
public class TransaccionRegistroDto {
    private double monto;
    private String moneda;
    private String descripcion;
}
