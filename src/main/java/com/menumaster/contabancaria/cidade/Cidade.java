package com.menumaster.contabancaria.cidade;

import com.menumaster.contabancaria.unidadefederativa.UnidadeFederativa;
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
    private Long idCidade;

    private String nomeCidade;

    @ManyToOne
    private UnidadeFederativa unidadeFederativa;

}
