package com.menumaster.contabancaria.contato.email;

import com.menumaster.contabancaria.client.Client;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class Email {

    @Id
    private String email;

    @ManyToOne
    private Client client;
}
