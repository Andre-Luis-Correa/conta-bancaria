package com.menumaster.contabancaria.transacao;

import com.menumaster.contabancaria.contabancaria.ContaBancaria;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Entity
@Getter
@Setter
public class Transacao {

    @Id
    private String codigoTransacao;

    @ManyToOne
    private ContaBancaria contaBancaria;

    private LocalDate dataTransacao;

    @ManyToOne
    private TipoTransacao tipoTransacao;

    private String observacao;
}
