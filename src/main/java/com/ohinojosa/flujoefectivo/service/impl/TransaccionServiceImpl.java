package com.ohinojosa.flujoefectivo.service.impl;

import com.ohinojosa.flujoefectivo.exception.NoDataFoundException;
import com.ohinojosa.flujoefectivo.exception.TransactionException;
import com.ohinojosa.flujoefectivo.exception.ValidateException;
import com.ohinojosa.flujoefectivo.model.Cuenta;
import com.ohinojosa.flujoefectivo.model.EstadoCuenta;
import com.ohinojosa.flujoefectivo.model.Operacion;
import com.ohinojosa.flujoefectivo.model.Transaccion;
import com.ohinojosa.flujoefectivo.repository.CuentaRepository;
import com.ohinojosa.flujoefectivo.repository.TransaccionRepository;
import com.ohinojosa.flujoefectivo.service.CuentaService;
import com.ohinojosa.flujoefectivo.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class TransaccionServiceImpl implements TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;
    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private CuentaService cuentaService;


    @Override
    public Transaccion retiro(Transaccion nuevaTransaccion){
        nuevaTransaccion.setOperacion(Operacion.RETIRO);
        Transaccion cuentaRegistrada = procesarTransaccion(nuevaTransaccion);
        return cuentaRegistrada;
    }

    @Override
    public Transaccion deposito(Transaccion nuevaTransaccion){
        nuevaTransaccion.setOperacion(Operacion.DEPOSITO);
        Transaccion cuentaRegistrada = procesarTransaccion(nuevaTransaccion);
        return cuentaRegistrada;
    }

    @Override
    public List<Transaccion> transacciones(String numeroCuenta){
        Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta);
        if (cuenta == null)
            throw new NoDataFoundException("La Cuenta " + numeroCuenta + "  No existe.");
        List<Transaccion> transaccionList = transaccionRepository.getAllByCuentaId(cuenta.getId());
        return transaccionList;
    }

    public Transaccion procesarTransaccion(Transaccion nuevaTransaccion){
        Cuenta cuenta = cuentaService.obtenerCuenta(nuevaTransaccion.getCuenta().getNumeroCuenta());
        if (!cuenta.getMoneda().equals(nuevaTransaccion.getMoneda()))
            throw new ValidateException("Solo se puede realizar operaciones en la misma moneda");

        if (nuevaTransaccion.getOperacion().equals(Operacion.RETIRO) && cuenta.getEstadoCuenta().equals(EstadoCuenta.HOLD))
            throw new ValidateException("La Cuenta " + cuenta.getNumeroCuenta() + "  está en estado HOLD. No puede realizar un retiro");
        cuenta.setSaldo((nuevaTransaccion.getOperacion().equals(Operacion.RETIRO)) ? cuenta.getSaldo() - nuevaTransaccion.getMonto() : cuenta.getSaldo() + nuevaTransaccion.getMonto());

        cuentaService.actualizarCuenta(cuenta);
        nuevaTransaccion.setCuenta(cuenta);
        nuevaTransaccion.setFechaRegistro(LocalDateTime.now());
        Transaccion cuentaRegistrada = null;
        try {
            cuentaRegistrada = transaccionRepository.save(nuevaTransaccion);
        } catch (Exception e) {
            throw new TransactionException("Ocurrio un error al registrar la transacción ", e.getCause());
        }
        return cuentaRegistrada;
    }
}
