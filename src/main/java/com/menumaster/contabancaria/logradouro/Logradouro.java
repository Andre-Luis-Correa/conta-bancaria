package com.menumaster.contabancaria.logradouro;

import com.menumaster.contabancaria.tipologradouro.TipoLogradouro;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class Logradouro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeLogradouro;

    @ManyToOne
    private TipoLogradouro tipoLogradouro;
}
