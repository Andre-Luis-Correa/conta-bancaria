package com.menumaster.contabancaria.contato.telefone.ddi;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class DDI {

    @Id
    private Long numeroDDI;
}
