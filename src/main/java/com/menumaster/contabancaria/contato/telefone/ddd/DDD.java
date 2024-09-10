package com.menumaster.contabancaria.contato.telefone.ddd;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class DDD {

    @Id
    private Long numeroDDD;
}
