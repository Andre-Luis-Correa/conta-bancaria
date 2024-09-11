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

        System.out.println("Digite o número da agência: ");
        String codigoAgencia = scanner.nextLine();

        while(!agenciaRepository.existsByCodigoAgencia(codigoAgencia)) {
            System.out.println("Digite um número da agência válido: ");
            codigoAgencia = scanner.nextLine();
        }

        return agenciaRepository.findByCodigoAgencia(codigoAgencia);

    }

    public void imprimirAgencias() {
        List<Agencia> agencias = buscarTodasAgencias();
        if (agencias.isEmpty()) {
            System.out.println("Nenhuma agência encontrada.");
        } else {
            System.out.println(formatarAgencias(agencias));
        }
    }

    private List<Agencia> buscarTodasAgencias() {
        return agenciaRepository.findAll();
    }

    private String formatarAgencias(List<Agencia> agencias) {
        StringBuilder builder = new StringBuilder("Lista de Agências com Bancos:\n");
        for (Agencia agencia : agencias) {
            builder.append("Agência: ")
                    .append(agencia.getNomeAgencia())
                    .append(" | Código: ")
                    .append(agencia.getCodigoAgencia())
                    .append(" | Banco: ")
                    .append(agencia.getBanco().getNome())
                    .append("\n");
        }
        return builder.toString();
    }
}
