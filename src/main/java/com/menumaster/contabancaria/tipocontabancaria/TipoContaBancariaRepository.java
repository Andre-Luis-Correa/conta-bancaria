package com.menumaster.contabancaria.tipocontabancaria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoContaBancariaRepository extends JpaRepository<TipoContaBancaria, String> {

    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM TipoContaBancaria t WHERE t.siglaTipoContaBancaria = :siglaTipoContaBancaria")
    boolean existsBySiglaTipoContaBancaria(@Param("siglaTipoContaBancaria") String siglaTipoContaBancaria);

    @Query("SELECT t FROM TipoContaBancaria t WHERE t.siglaTipoContaBancaria = :siglaTipoContaBancaria")
    TipoContaBancaria findBySiglaTipoContaBancaria(@Param("siglaTipoContaBancaria") String siglaTipoContaBancaria);
}