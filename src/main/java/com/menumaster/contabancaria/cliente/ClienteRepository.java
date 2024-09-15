package com.menumaster.contabancaria.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Cliente c WHERE c.cpfCliente = :cpfCliente")
    boolean existsByCpfCliente(@Param("cpfCliente") String cpf);

    @Query("SELECT c FROM Cliente c WHERE c.cpfCliente = :cpfCliente")
    Cliente findByCpfCliente(@Param("cpfCliente") String cpf);
}