package com.ohinojosa.flujoefectivo.service;

import com.ohinojosa.flujoefectivo.model.Cuenta;

public interface CuentaService {
    Cuenta crearCuenta (Cuenta cuenta);

    Cuenta obtenerCuenta (String numeroCuenta);

    Cuenta actualizarCuenta (Cuenta cuenta);
}
