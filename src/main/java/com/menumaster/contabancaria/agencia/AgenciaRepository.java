package com.menumaster.contabancaria.agencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, String> {
    boolean existsByCodigoAgencia(String codigoAgencia);

    Agencia findByCodigoAgencia(String codigoAgencia);
}
