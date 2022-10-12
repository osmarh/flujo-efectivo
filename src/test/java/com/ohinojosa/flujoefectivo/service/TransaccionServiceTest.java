package com.ohinojosa.flujoefectivo.service;

import com.ohinojosa.flujoefectivo.model.Cuenta;
import com.ohinojosa.flujoefectivo.model.EstadoCuenta;
import com.ohinojosa.flujoefectivo.model.Operacion;
import com.ohinojosa.flujoefectivo.model.Transaccion;
import com.ohinojosa.flujoefectivo.repository.CuentaRepository;
import com.ohinojosa.flujoefectivo.repository.TransaccionRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransaccionServiceTest {
    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private TransaccionService transaccionService;

    @Test
    public void retiroTest() {
        Cuenta cuenta=crearCuentaRetiro();
        cuenta=cuentaService.crearCuenta(cuenta);
        Transaccion retiro=transaccion(cuenta);
        retiro.setOperacion(Operacion.RETIRO);
        Transaccion retiroRealizado=transaccionService.retiro(retiro);
        Assertions.assertEquals(retiroRealizado.getOperacion(), retiro.getOperacion());

    }

    @Test
    public void depositoTest() {

        Cuenta cuenta=crearCuentaDeposito();
        cuenta=cuentaService.crearCuenta(cuenta);

        Transaccion retiro=transaccion(cuenta);
        retiro.setOperacion(Operacion.DEPOSITO);
        Transaccion retiroRealizado=transaccionService.retiro(retiro);
        Assertions.assertEquals(retiroRealizado.getOperacion(), retiro.getOperacion());
    }

    Cuenta crearCuentaRetiro(){
        Cuenta cuenta=new Cuenta();
        cuenta.setNumeroCuenta("cta-1000-01");
        cuenta.setSaldo(200);
        cuenta.setCliente("Osmar Hinojods");
        cuenta.setMoneda("BOB");
        return cuenta;
    }
    Cuenta crearCuentaDeposito(){
        Cuenta cuenta=new Cuenta();
        cuenta.setNumeroCuenta("cta-1000-02");
        cuenta.setSaldo(200);
        cuenta.setCliente("Osmar Hinojods");
        cuenta.setMoneda("BOB");
        return cuenta;
    }
    Transaccion transaccion(Cuenta cuenta){
        Transaccion transaccion=new Transaccion();
        transaccion.setCuenta(cuenta);
        transaccion.setDescripcion("Retiro de efectivo");
        transaccion.setMoneda("BOB");
        transaccion.setMonto(50);
        return transaccion;
    }
}
