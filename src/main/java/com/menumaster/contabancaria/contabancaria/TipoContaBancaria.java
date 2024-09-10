package com.menumaster.contabancaria.contabancaria;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class TipoContaBancaria {

    @Id
    private String siglaTipoContaBancaria;

    private String nomeTipoContaBancaria;
}
