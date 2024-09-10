package com.menumaster.contabancaria.tipologradouro;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class TipoLogradouro {

    @Id
    private String siglaTipoLogradouro;

    private String nomeTipoLogradouro;
}
