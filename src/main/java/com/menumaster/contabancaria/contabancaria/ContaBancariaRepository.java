package com.menumaster.contabancaria.contabancaria;

import com.menumaster.contabancaria.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, String> {

    @Query("SELECT cb FROM ContaBancaria cb WHERE cb.cliente = :cliente")
    List<ContaBancaria> findByCliente(@Param("cliente") Cliente cliente);
}