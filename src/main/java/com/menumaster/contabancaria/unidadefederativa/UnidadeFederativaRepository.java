package com.menumaster.contabancaria.unidadefederativa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeFederativaRepository extends JpaRepository<UnidadeFederativa, String> {
    boolean existsBySiglaUF(String siglaUF);

    UnidadeFederativa findBySiglaUF(String siglaUF);
}
