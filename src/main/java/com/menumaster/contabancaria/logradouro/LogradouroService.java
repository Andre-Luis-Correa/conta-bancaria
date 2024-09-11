package com.menumaster.contabancaria.logradouro;

import com.menumaster.contabancaria.tipologradouro.TipoLogradouroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class LogradouroService {

    private final LogradouroRepository logradouroRepository;
    private final TipoLogradouroService tipoLogradouroService;

    public Logradouro lerLogradouro() {
        Scanner scanner = new Scanner(System.in);
        Logradouro logradouro = new Logradouro();

        System.out.print("Logradouro: ");
        logradouro.setNomeLogradouro(scanner.nextLine());

        logradouro.setTipoLogradouro(tipoLogradouroService.lerTipoLogradouro());

        return logradouroRepository.save(logradouro);
    }
}
