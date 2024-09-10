package com.menumaster.contabancaria.tipologradouro;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class TipoLogradouroService {

    private final TipoLogradouroRepository tipoLogradouroRepository;

    public TipoLogradouro lerTipoLogradouro() {
        Scanner scanner = new Scanner(System.in);

        imprimirTiposLogradouro();

        System.out.println("Selecione o tipo de logradouro (informe a sigla)");
        String siglaTipoLogradouro = scanner.nextLine();

        while(!tipoLogradouroRepository.existsBySiglaTipoLogradouro(siglaTipoLogradouro)) {
            System.out.println("Selecione um tipo de logradouro existente (informe a sigla)");
            siglaTipoLogradouro = scanner.nextLine();
        }

        return tipoLogradouroRepository.findBySiglaTipoLogradouro(siglaTipoLogradouro);
    }

    public void imprimirTiposLogradouro() {
        List<TipoLogradouro> tiposLogradouro = buscarTodosTiposLogradouro();

        if (tiposLogradouro.isEmpty()) {
            System.out.println("Nenhum tipo de logradouro encontrado.");
            return;
        }

        System.out.println("Lista de Tipos de Logradouro:");
        for (TipoLogradouro tipo : tiposLogradouro) {
            System.out.println(formatarTipoLogradouro(tipo));
        }
    }

    // Função para buscar todos os tipos de logradouro
    public List<TipoLogradouro> buscarTodosTiposLogradouro() {
        return tipoLogradouroRepository.findAll();
    }

    // Função para formatar a saída dos tipos de logradouro
    private String formatarTipoLogradouro(TipoLogradouro tipoLogradouro) {
        return String.format("Sigla: %s, Nome: %s",
                tipoLogradouro.getSiglaTipoLogradouro(),
                tipoLogradouro.getNomeTipoLogradouro());
    }
}
