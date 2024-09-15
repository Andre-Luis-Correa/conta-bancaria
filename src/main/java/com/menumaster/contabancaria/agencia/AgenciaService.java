package com.menumaster.contabancaria.agencia;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class AgenciaService {

    private final AgenciaRepository agenciaRepository;

    public Agencia lerAgencia() {
        Scanner scanner = new Scanner(System.in);

        imprimirAgencias();

        System.out.print("Digite o código da agência: ");
        String codigoAgencia = scanner.nextLine();

        while(!agenciaRepository.existsByCodigoAgencia(codigoAgencia)) {
            System.out.print("Digite um código da agência válido: ");
            codigoAgencia = scanner.nextLine();
        }

        return agenciaRepository.findByCodigoAgencia(codigoAgencia);

    }

    public void imprimirAgencias() {
        List<Agencia> agencias = buscarTodasAgencias();
        if (agencias.isEmpty()) {
            System.out.println("Nenhuma agência encontrada.");
        } else {
            System.out.println("\n -----------------------------------------Agencias-----------------------------------------");
            for(Agencia agencia : agencias){
                System.out.println(formatarAgencias(agencia));
            }
            System.out.println(" ------------------------------------------------------------------------------------------");
        }
    }

    private String formatarAgencias(Agencia agencia) {
        return String.format("| Código: %-10s Nome: %-30s Banco: %-25s |",
                agencia.getCodigoAgencia(),
                agencia.getNomeAgencia(),
                agencia.getBanco().getNome());
    }

    private List<Agencia> buscarTodasAgencias() {
        return agenciaRepository.findAll();
    }

}
