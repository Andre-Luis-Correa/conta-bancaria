package com.menumaster.contabancaria.transacao;

import com.menumaster.contabancaria.cliente.Cliente;
import com.menumaster.contabancaria.cliente.ClienteService;
import com.menumaster.contabancaria.contabancaria.ContaBancaria;
import com.menumaster.contabancaria.contabancaria.ContaBancariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final ClienteService clienteService;
    private final ContaBancariaService contaBancariaService;

    public void consultarTransacoes() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();

        if(clienteService.verificarSeExisteCliente(cpf)) {
            Cliente cliente = clienteService.buscarClientePorCpf(cpf);
            ContaBancaria contaBancaria = contaBancariaService.selecionarContaBancaria(cliente);
            mostrarDadosTransacao(contaBancaria);
        } else {
            System.out.println("Não existe cliente com esse cpf!");
        }
    }

    private void mostrarDadosTransacao(ContaBancaria contaBancaria) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.print("\nDigite a data de início do período (dd/MM/yyyy): ");
        LocalDate dataInicio = LocalDate.parse(scanner.nextLine(), formatter);

        System.out.print("Digite a data de fim do período (dd/MM/yyyy): ");
        LocalDate dataFim = LocalDate.parse(scanner.nextLine(), formatter);

        List<Transacao> transacoes = transacaoRepository.findTransacoesByContaBancariaAndPeriodo(
                contaBancaria.getNumeroContaBancaria(), dataInicio, dataFim);

        System.out.println("\nEXTRATO CONTA BANCÁRIA");

        clienteService.mostrarDadosCliente(contaBancaria.getCliente());
        contaBancariaService.mostrarDadosContaBancaria(contaBancaria);
        mostrarTransacoesBancarias(transacoes, dataInicio, dataFim);

    }

    public void mostrarTransacoesBancarias(List<Transacao> transacoes, LocalDate dataInicio, LocalDate dataFim) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("\nPeríodo do extrato: " + dataInicio.format(formatter) + " a " + dataFim.format(formatter));
        System.out.println(" ------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-12s | %-15s | %-10s | %-20s | %-30s | %-15s |\n", "Data", "Transação Cod", "Tipo Cod", "Nome Tipo", "Observação", "Valor");
        System.out.println(" ------------------------------------------------------------------------------------------------------------------------");

        if(transacoes.isEmpty()) {
            System.out.println("|                                         Não houveram transações nesse período                                         |");
        } else {
            for (Transacao transacao : transacoes) {
                System.out.printf("| %-12s | %-15s | %-10s | %-20s | %-30s | %-15.2f |\n",
                        transacao.getDataTransacao().format(formatter),
                        transacao.getCodigoTransacao(),
                        transacao.getTipoTransacao().getCodigoTipoTransacao(),
                        transacao.getTipoTransacao().getNomeTipoTransacao(),
                        transacao.getObservacao(),
                        transacao.getValorTransacao());
            }
        }

        System.out.println(" ------------------------------------------------------------------------------------------------------------------------");
    }
}
