package com.menumaster.contabancaria.agencia;

import com.menumaster.contabancaria.banco.Banco;
import com.menumaster.contabancaria.endereco.Endereco;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class Agencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeAgencia;

    @OneToOne
    private Endereco endereco;

    private String numeroEndereco;

    private String complementoEndereco;

    @ManyToOne
    private Banco banco;
}
