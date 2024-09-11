package com.menumaster.contabancaria.tipocontabancaria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoContaBancariaRepository extends JpaRepository<TipoContaBancaria, String> {
    boolean existsBySiglaTipoContaBancaria(String siglaTipoContaBancaria);

    TipoContaBancaria findBySiglaTipoContaBancaria(String siglaTipoContaBancaria);
}
