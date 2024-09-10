package com.menumaster.contabancaria.cidade;

import com.menumaster.contabancaria.unidadefederativa.UnidadeFederativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    List<Cidade> findAllByUnidadeFederativa(UnidadeFederativa unidadeFederativa);

    Cidade findByNomeCidade(String nomeCidade);

    boolean existsByNomeCidade(String nomeCidade);
}
