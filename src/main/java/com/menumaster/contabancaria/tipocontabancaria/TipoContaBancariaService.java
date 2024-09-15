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

        System.out.print("Digite a sigla do tipo de conta bancaria que deseja abrir: ");
        String siglaTipoContaBancaria = scanner.nextLine();

        while(!tipoContaBancariaRepository.existsBySiglaTipoContaBancaria(siglaTipoContaBancaria)){
            System.out.print("Digite uma sigla para o tipo de conta bancária válida: ");
            siglaTipoContaBancaria = scanner.nextLine();
        }

        return tipoContaBancariaRepository.findBySiglaTipoContaBancaria(siglaTipoContaBancaria);
    }

    public void imprimirTiposContaBancaria() {
        List<TipoContaBancaria> tipos = buscarTodosTiposContaBancaria();
        if (tipos.isEmpty()) {
            System.out.println("Nenhum tipo de conta bancária encontrado.");
        } else {
            System.out.println("\n -------------Tipo conta bancária--------------");
            for(TipoContaBancaria tipoContaBancaria : tipos) {
                System.out.println(formatarTiposContaBancaria(tipoContaBancaria));
            }
            System.out.println(" ------------------------------------------------");
        }
    }

    private String formatarTiposContaBancaria(TipoContaBancaria tipoContaBancaria) {
        return String.format("| Tipo: %-25s  Sigla: %-4s |", tipoContaBancaria.getNomeTipoContaBancaria(), tipoContaBancaria.getSiglaTipoContaBancaria());
    }

    private List<TipoContaBancaria> buscarTodosTiposContaBancaria() {
        return tipoContaBancariaRepository.findAll();
    }
}
