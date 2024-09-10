package com.menumaster.contabancaria.endereco.cidade;

import com.menumaster.contabancaria.endereco.unidadefederativa.UnidadeFederativa;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCidade;

    @ManyToOne
    private UnidadeFederativa unidadeFederativa;

}
