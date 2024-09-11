package com.menumaster.contabancaria.contato.email;

import com.menumaster.contabancaria.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmailRepository extends JpaRepository<Email, String> {
    List<Email> findAllByCliente(Cliente cliente);
}
