package com.ohinojosa.flujoefectivo.repository;

import com.ohinojosa.flujoefectivo.model.Cuenta;
import com.ohinojosa.flujoefectivo.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
    List<Transaccion> getAllByCuentaId(Long cuentaId);
}
