package com.menumaster.contabancaria.transacao;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class TipoTransacao {

    @Id
    private String codigoTipoTransacao;

    private String nomeTipoTransacao;
}
