package com.menumaster.contabancaria.unidadefederativa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class UnidadeFederativa {

    @Id
    private String siglaUF;

    private String nomeUF;
}
