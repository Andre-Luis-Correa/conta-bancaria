package com.menumaster.contabancaria.menu;

import com.menumaster.contabancaria.cliente.ClienteService;
import com.menumaster.contabancaria.contabancaria.ContaBancariaService;
import com.menumaster.contabancaria.transacao.TransacaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Log4j2
@Component
@RequiredArgsConstructor
public class Menu {

    private final ClienteService clienteService;
    private final ContaBancariaService contaBancariaService;
    private final TransacaoService transacaoService;

    public void mostrarOpcoesMenu() {
        System.out.println("\n\n ------------------MENU------------------");
        System.out.println("| 1 - Cadastrar cliente                  |");
        System.out.println("| 2 - Criar conta bancária               |");
        System.out.println("| 3 - Consultar transações bancárias     |");
        System.out.println("| 4 - Sair                               |");
        System.out.println(" ----------------------------------------");
    }

    public int selecionarOpcaoMenu() {
        int opcao;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite a opção desejada: ");
        opcao = Integer.parseInt(scanner.nextLine());

        return opcao;
    }

    public void inicializarMenu() {
        int opcao = 0;

        while(opcao != 4) {
            mostrarOpcoesMenu();
            opcao = selecionarOpcaoMenu();

            switch (opcao) {
                case 1:
                    System.out.println("\n1 - Realizar cadastro de cliente");
                    clienteService.cadastrarCliente();
                    break;
                case 2:
                    System.out.println("\n2 - Criar conta bancária");
                    contaBancariaService.cadastrarContaBancaria();
                    break;
                case 3:
                    System.out.println("\n3 - Consultar transações bancárias");
                    transacaoService.consultarTransacoes();
                    break;
                case 4:
                    System.out.println("Saindo do programa...\n\n");
                    return;
                default:
                    System.out.println("Opção inválida, por favor, selecione novamente");
                    break;
            }
        }
    }
}
