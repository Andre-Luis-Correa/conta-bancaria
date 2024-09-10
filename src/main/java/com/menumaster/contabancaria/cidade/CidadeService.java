package com.menumaster.contabancaria.cidade;

import com.menumaster.contabancaria.unidadefederativa.UnidadeFederativa;
import com.menumaster.contabancaria.unidadefederativa.UnidadeFederativaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class CidadeService {

    private final CidadeRepository cidadeRepository;
    private final UnidadeFederativaService unidadeFederativaService;


    public Cidade lerCidade() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a sigla (UF) do estado: ");
        UnidadeFederativa unidadeFederativa = unidadeFederativaService.lerUnidadeFederativa();
        imprimirCidades(unidadeFederativa);

        System.out.println("Nome da cidade: ");
        String nomeCidade = scanner.nextLine();

        while(!cidadeRepository.existsByNomeCidade(nomeCidade)) {
            System.out.println("Selecione uma cidade existente: ");
            nomeCidade = scanner.nextLine();
        }

        return cidadeRepository.findByNomeCidade(nomeCidade);

    }

    private List<Cidade> buscarTodasCidadesPorUnidadeFederativa(UnidadeFederativa unidadeFederativa) {
        return cidadeRepository.findAllByUnidadeFederativa(unidadeFederativa);
    }

    public void imprimirCidades(UnidadeFederativa unidadeFederativa) {
        List<Cidade> cidadeList = buscarTodasCidadesPorUnidadeFederativa(unidadeFederativa);

        if (cidadeList.isEmpty()) {
            System.out.println("Nenhuma cidade encontrada.");
            return;
        }

        System.out.println("Lista de Cidades:");
        for (Cidade cidade : cidadeList) {
            System.out.println(formatarCidade(cidade));
        }
    }

    private String formatarCidade(Cidade cidade) {
        return String.format("Cidade: %s, UF: %s (%s)",
                cidade.getNomeCidade(),
                cidade.getUnidadeFederativa().getNomeUF(),
                cidade.getUnidadeFederativa().getSiglaUF());
    }
}
