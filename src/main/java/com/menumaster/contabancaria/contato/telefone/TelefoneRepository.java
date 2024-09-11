package com.menumaster.contabancaria.contato.telefone;

import com.menumaster.contabancaria.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, String> {

    List<Telefone> findAllByCliente(Cliente cliente);
}
