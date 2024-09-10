package com.menumaster.contabancaria.endereco;

import com.menumaster.contabancaria.bairro.BairroService;
import com.menumaster.contabancaria.cidade.CidadeService;
import com.menumaster.contabancaria.logradouro.LogradouroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final LogradouroService logradouroService;
    private final CidadeService cidadeService;
    private final BairroService bairroService;

    public Endereco lerEndereco() {
        Scanner scanner = new Scanner(System.in);
        Endereco endereco = new Endereco();

        System.out.println("Digite o CEP:");
        endereco.setCep(scanner.nextLine());

        endereco.setLogradouro(logradouroService.lerLogradouro());

        endereco.setCidade(cidadeService.lerCidade());

        endereco.setBairro(bairroService.lerBairro());

        return enderecoRepository.save(endereco);
    }
}
