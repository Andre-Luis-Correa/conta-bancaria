package com.menumaster.contabancaria.client;

import com.menumaster.contabancaria.endereco.Endereco;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCliente;

    private String cpfCliente;

    @ManyToOne
    private Endereco endereco;

    private String numeroEndereco;

    private String complementoEndereco;
}
