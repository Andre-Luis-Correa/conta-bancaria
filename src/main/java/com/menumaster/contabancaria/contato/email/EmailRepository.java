package com.menumaster.contabancaria.contato.email;

import com.menumaster.contabancaria.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<Email, String> {

    @Query("SELECT e FROM Email e WHERE e.cliente = :cliente")
    List<Email> findAllByCliente(@Param("cliente") Cliente cliente);
}