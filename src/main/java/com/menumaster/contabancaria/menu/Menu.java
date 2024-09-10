package com.menumaster.contabancaria.menu;

import lombok.extern.log4j.Log4j2;

import java.util.Scanner;

@Log4j2
public class Menu {

    public void mostrarOpcoesMenu() {
        System.out.println("MENU");
        System.out.println("1 - Cadastrar cliente");
        System.out.println("2 - Criar conta bancária");
        System.out.println("3 - Consultar transações bancárias");
        System.out.println("4 - Sair");
    }

    public int selecionarOpcaoMenu() {
        int opcao;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a opção desejada: ");
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
                    System.out.println("1 - Realizar cadastro de cliente");
                    break;
                case 2:
                    System.out.println("1 - Criar conta bancária");
                    break;
                case 3:
                    System.out.println("1 - Consultar transações bancárias");
                    break;
                case 4:
                    System.out.println("Saindo do programa...");
                    return;
                default:
                    System.out.println("Opção inválida, por favor, selecione novamente");
                    break;
            }
        }
    }
}
