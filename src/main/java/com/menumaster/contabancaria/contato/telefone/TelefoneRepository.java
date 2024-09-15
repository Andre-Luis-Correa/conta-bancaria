package com.menumaster.contabancaria.contato.telefone;

import com.menumaster.contabancaria.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, String> {

    @Query("SELECT t FROM Telefone t WHERE t.cliente = :cliente")
    List<Telefone> findAllByCliente(@Param("cliente") Cliente cliente);
}