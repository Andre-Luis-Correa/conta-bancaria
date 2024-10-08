package com.menumaster.contabancaria.contabancaria;


import com.menumaster.contabancaria.agencia.Agencia;
import com.menumaster.contabancaria.cliente.Cliente;
import com.menumaster.contabancaria.tipocontabancaria.TipoContaBancaria;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Entity
@Getter
@Setter
public class ContaBancaria {

    @Id
    private String numeroContaBancaria;

    @ManyToOne
    private Agencia agencia;

    @ManyToOne
    private TipoContaBancaria tipoContaBancaria;

    private LocalDate dataAberturaContaBancaria;

    @ManyToOne
    private Cliente cliente;

    private Double saldoAtuaContaBancaria;
}
