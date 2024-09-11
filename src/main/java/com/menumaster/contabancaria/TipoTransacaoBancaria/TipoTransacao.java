package com.menumaster.contabancaria.TipoTransacaoBancaria;

import com.menumaster.contabancaria.enums.DescricaoPadraoTransacao;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Enumerated(EnumType.STRING)
    private DescricaoPadraoTransacao descricaoPadraoTransacao;
}
