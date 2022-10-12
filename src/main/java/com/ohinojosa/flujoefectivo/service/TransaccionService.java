package com.ohinojosa.flujoefectivo.service;

import com.ohinojosa.flujoefectivo.model.Cuenta;
import com.ohinojosa.flujoefectivo.model.Transaccion;

import java.util.List;

public interface TransaccionService {
    Transaccion retiro(Transaccion transaccion);
    Transaccion deposito(Transaccion transaccion);
    List<Transaccion> transacciones(String numeroCuenta);
}
