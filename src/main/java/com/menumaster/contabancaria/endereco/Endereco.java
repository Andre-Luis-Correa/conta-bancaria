package com.menumaster.contabancaria.endereco;

import com.menumaster.contabancaria.endereco.bairro.Bairro;
import com.menumaster.contabancaria.endereco.cidade.Cidade;
import com.menumaster.contabancaria.endereco.logradouro.Logradouro;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;

    @ManyToOne
    private Cidade cidade;

    @ManyToOne
    private Bairro bairro;

    @ManyToOne
    private Logradouro logradouro;
}
