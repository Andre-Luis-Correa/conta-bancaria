package com.menumaster.contabancaria.contato.telefone;

import com.menumaster.contabancaria.cliente.Cliente;
import com.menumaster.contabancaria.contato.telefone.ddd.DDD;
import com.menumaster.contabancaria.contato.telefone.ddi.DDI;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class Telefone {

    @Id
    private String numeroTelefone;

    @ManyToOne
    private DDD numeroDDD;

    @ManyToOne
    private DDI numeroDDI;

    @ManyToOne
    private Cliente cliente;

}
