package com.menumaster.contabancaria.transacao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, String> {

    @Query("SELECT t FROM Transacao t WHERE t.contaBancaria.numeroContaBancaria = :numeroConta AND t.dataTransacao BETWEEN :dataInicio AND :dataFim")
    List<Transacao> findTransacoesByContaBancariaAndPeriodo(
            @Param("numeroConta") String numeroContaBancaria,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim);
}
