package com.menumaster.contabancaria.tipologradouro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoLogradouroRepository extends JpaRepository<TipoLogradouro, String> {
    boolean existsBySiglaTipoLogradouro(String siglaTipoLogradouro);

    TipoLogradouro findBySiglaTipoLogradouro(String siglaTipoLogradouro);
}
