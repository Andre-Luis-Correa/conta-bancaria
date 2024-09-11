package com.menumaster.contabancaria.transacao;

import com.menumaster.contabancaria.cliente.Cliente;
import com.menumaster.contabancaria.cliente.ClienteService;
import com.menumaster.contabancaria.contabancaria.ContaBancaria;
import com.menumaster.contabancaria.contabancaria.ContaBancariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final ClienteService clienteService;
    private final ContaBancariaService contaBancariaService;

    public void consultarTransacoes() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o cpf do cliente: ");
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

        System.out.println("Extrato Conta Bancária");

        clienteService.mostrarDadosCliente(contaBancaria.getCliente());

    }
}
