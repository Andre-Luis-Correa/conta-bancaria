package com.menumaster.contabancaria.tipocontabancaria;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class TipoContaBancariaService {

    private final TipoContaBancariaRepository tipoContaBancariaRepository;

    public TipoContaBancaria lerTipoContaBancaria() {
        Scanner scanner = new Scanner(System.in);

        imprimirTiposContaBancaria();

        System.out.println("Digite a sigla do tipo de conta bancaria que deseja abrir:");
        String siglaTipoContaBancaria = scanner.nextLine();

        while(!tipoContaBancariaRepository.existsBySiglaTipoContaBancaria(siglaTipoContaBancaria)){
            System.out.println("Digite uma sigla para o tipo de conta bancária válida:");
            siglaTipoContaBancaria = scanner.nextLine();
        }

        return tipoContaBancariaRepository.findBySiglaTipoContaBancaria(siglaTipoContaBancaria);
    }

    public void imprimirTiposContaBancaria() {
        List<TipoContaBancaria> tipos = buscarTodosTiposContaBancaria();
        if (tipos.isEmpty()) {
            System.out.println("Nenhum tipo de conta bancária encontrado.");
        } else {
            System.out.println(formatarTiposContaBancaria(tipos));
        }
    }

    // Função para buscar todos os tipos de conta bancária
    private List<TipoContaBancaria> buscarTodosTiposContaBancaria() {
        return tipoContaBancariaRepository.findAll();
    }

    // Função para formatar a saída dos tipos de conta bancária
    private String formatarTiposContaBancaria(List<TipoContaBancaria> tipos) {
        StringBuilder builder = new StringBuilder("Lista de Tipos de Conta Bancária:\n");
        for (TipoContaBancaria tipo : tipos) {
            builder.append("Tipo de Conta: ")
                    .append(tipo.getNomeTipoContaBancaria())
                    .append(" | Sigla: ")
                    .append(tipo.getSiglaTipoContaBancaria())
                    .append("\n");
        }
        return builder.toString();
    }
}
