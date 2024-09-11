package com.menumaster.contabancaria.contabancaria;

import com.menumaster.contabancaria.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, String> {
    List<ContaBancaria> findByCliente(Cliente cliente);
}
