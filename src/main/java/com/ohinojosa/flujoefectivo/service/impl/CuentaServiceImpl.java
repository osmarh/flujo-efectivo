package com.ohinojosa.flujoefectivo.service.impl;

import com.ohinojosa.flujoefectivo.exception.NoDataFoundException;
import com.ohinojosa.flujoefectivo.exception.TransactionException;
import com.ohinojosa.flujoefectivo.exception.ValidateException;
import com.ohinojosa.flujoefectivo.model.Cuenta;
import com.ohinojosa.flujoefectivo.model.EstadoCuenta;
import com.ohinojosa.flujoefectivo.repository.CuentaRepository;
import com.ohinojosa.flujoefectivo.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private  CuentaRepository cuentaRepository;



    @Override
    public Cuenta crearCuenta(Cuenta nuevaCuenta) {
        Cuenta cuenta=cuentaRepository.findByNumeroCuenta(nuevaCuenta.getNumeroCuenta());
        if(cuenta!=null)
            throw new ValidateException("La Cuenta " + nuevaCuenta.getNumeroCuenta() + "  Ya existe.");

        nuevaCuenta.setEstadoCuenta(EstadoCuenta.ACTIVE);
        Cuenta cuentaRegistrada = null;
        try {
            cuentaRegistrada = cuentaRepository.save(nuevaCuenta);
        } catch (Exception e) {
            throw new TransactionException("Ocurrio un error al registrar la cuenta " + nuevaCuenta.getNumeroCuenta() , e.getCause());
        }
        return cuentaRegistrada;
    }

    public Cuenta obtenerCuenta (String numeroCuenta){
        Cuenta cuenta=cuentaRepository.findByNumeroCuenta(numeroCuenta);
        if(cuenta==null)
            throw new NoDataFoundException("La Cuenta " + numeroCuenta + "  No existe.");
        return cuenta;

    }
    @Override
    public Cuenta actualizarCuenta(Cuenta cuenta) {
        cuenta.setEstadoCuenta((cuenta.getSaldo()<0)?EstadoCuenta.HOLD:EstadoCuenta.ACTIVE);
        Cuenta cuentaRegistrada = null;
        try {
            cuentaRegistrada = cuentaRepository.save(cuenta);
        } catch (Exception e) {
            throw new TransactionException("Ocurrio un error al actualizar la cuenta " + cuenta.getNumeroCuenta(), e.getCause());
        }
        return cuentaRegistrada;
    }
}
