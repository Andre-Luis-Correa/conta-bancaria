package com.menumaster.contabancaria.transacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TransacaoDTO {

    private String dataTransacao;
    private String codigoTransacao;
    private String codigoTipoTransacao;
    private String nomeTipoTransacao;
    private String valorTransacao;
}
