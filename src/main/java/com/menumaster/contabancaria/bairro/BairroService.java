package com.menumaster.contabancaria.bairro;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class BairroService {

    private final BairroRepository bairroRepository;

    public Bairro lerBairro() {
        Scanner scanner = new Scanner(System.in);
        Bairro bairro = new Bairro();

        System.out.print("\nBairro: ");
        bairro.setNomeBairro(scanner.nextLine());

        return bairroRepository.save(bairro);
    }
}
