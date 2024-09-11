package com.menumaster.contabancaria.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    boolean existsByCpfCliente(String cpf);

    Cliente findByCpfCliente(String cpf);
}
