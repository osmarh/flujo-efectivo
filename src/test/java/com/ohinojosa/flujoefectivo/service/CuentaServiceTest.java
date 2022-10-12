package com.ohinojosa.flujoefectivo.service;

import com.ohinojosa.flujoefectivo.model.Cuenta;
import com.ohinojosa.flujoefectivo.repository.CuentaRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CuentaServiceTest {
    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private CuentaRepository cuentaRepository;

    @AfterEach
    public void tearDown() {
        this.cuentaRepository.deleteAll();
    }

    @Test
    public void crearCuentaNroCuentaTest() {
        Cuenta cuenta=crearCuenta();
        Cuenta cuentaCreada=cuentaService.crearCuenta(cuenta);
        Assertions.assertEquals(cuenta.getNumeroCuenta(), cuentaCreada.getNumeroCuenta());
    }

    @Test
    public void crearCuentaClienteTest() {
        Cuenta cuenta=crearCuenta();
        Cuenta cuentaCreada=cuentaService.crearCuenta(cuenta);
        Assertions.assertEquals(cuenta.getCliente(), cuentaCreada.getCliente());
    }

    @Test
    public void crearCuentaSaldoTest() {
        Cuenta cuenta=crearCuenta();
        Cuenta cuentaCreada=cuentaService.crearCuenta(cuenta);
        Assertions.assertEquals(cuenta.getSaldo(), cuentaCreada.getSaldo());
    }

    @Test
    public void crearCuentaMonedaTest() {
        Cuenta cuenta=crearCuenta();
        Cuenta cuentaCreada=cuentaService.crearCuenta(cuenta);
        Assertions.assertEquals(cuenta.getMoneda(), cuentaCreada.getMoneda());
    }

    @Test
    public void obtenerCuentaTest() {
        Cuenta cuenta=crearCuenta();
        Cuenta cuentaCreada=cuentaService.crearCuenta(cuenta);

        String nroCuenta=cuentaCreada.getNumeroCuenta();
        Cuenta cuentaRecuperada=cuentaService.obtenerCuenta(nroCuenta);
        Assertions.assertEquals(nroCuenta, cuentaRecuperada.getNumeroCuenta());
    }

    Cuenta crearCuenta(){
        Cuenta cuenta=new Cuenta();
        cuenta.setNumeroCuenta("cta-1000-01");
        cuenta.setSaldo(200);
        cuenta.setCliente("Osmar Hinojods");
        cuenta.setMoneda("BOB");
        return cuenta;
    }
}
