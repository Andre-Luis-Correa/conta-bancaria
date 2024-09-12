package com.menumaster.contabancaria.agencia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AgenciaRepository extends JpaRepository<Agencia, String> {
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Agencia a WHERE a.codigoAgencia = :codigoAgencia")
    boolean existsByCodigoAgencia(@Param("codigoAgencia") String codigoAgencia);

    @Query("SELECT a FROM Agencia a WHERE a.codigoAgencia = :codigoAgencia")
    Agencia findByCodigoAgencia(@Param("codigoAgencia") String codigoAgencia);
}
