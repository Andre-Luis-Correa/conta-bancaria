package com.menumaster.contabancaria.contabancaria;

import com.menumaster.contabancaria.agencia.Agencia;
import com.menumaster.contabancaria.agencia.AgenciaService;
import com.menumaster.contabancaria.cliente.Cliente;
import com.menumaster.contabancaria.cliente.ClienteService;
import com.menumaster.contabancaria.tipocontabancaria.TipoContaBancariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class ContaBancariaService {

    private final ContaBancariaRepository contaBancariaRepository;
    private final ClienteService clienteService;
    private final AgenciaService agenciaService;
    private final TipoContaBancariaService tipoContaBancariaService;
    public static final String VERDE = "\u001B[32m";
    public static final String RESET = "\u001B[0m";

    public void cadastrarContaBancaria() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite seu cpf: ");
        String cpf = scanner.nextLine();

        if(!clienteService.verificarSeExisteCliente(cpf)) {
            System.out.println("\nÉ necessário cadastrar-se antes de criar uma conta bancária!\n");
            return;
        }

        ContaBancaria contaBancaria = lerDadosContaBancaria(cpf);

        if(contaBancaria != null) {
            contaBancariaRepository.save(contaBancaria);
            System.out.println("\nConta bancária registrada com sucesso!\n");
        } else {
            System.out.println("\nNão foi possível concluir o cadastro da conta!\n");
        }
    }

    private ContaBancaria lerDadosContaBancaria(String cpf) {
        ContaBancaria contaBancaria = new ContaBancaria();
        Agencia agencia = agenciaService.lerAgencia();

        contaBancaria.setAgencia(agencia);
        contaBancaria.setTipoContaBancaria(tipoContaBancariaService.lerTipoContaBancaria());
        contaBancaria.setDataAberturaContaBancaria(LocalDate.now());
        contaBancaria.setCliente(clienteService.buscarClientePorCpf(cpf));
        contaBancaria.setNumeroContaBancaria(gerarCodigoContaBancaria(cpf, agencia.getCodigoAgencia()));

        return contaBancaria;
    }

    private String gerarCodigoContaBancaria(String cpf, String codigoAgencia) {
        StringBuilder stringBuilder = new StringBuilder(cpf);
        return stringBuilder.append(codigoAgencia).toString();
    }

    public void listarContasDisponiveis(Cliente cliente) {
        List<ContaBancaria> contasBancarias = contaBancariaRepository.findByCliente(cliente);

        if (contasBancarias.isEmpty()) {
            System.out.println("O cliente não possui contas bancárias.");
            return;
        }

        System.out.println("\n ------------------------Contas bancárias disponíveis-----------------------");
        for (ContaBancaria conta : contasBancarias) {
            System.out.printf("| Número: %-10s  Tipo: %-20s Banco: %-20s|%n", conta.getNumeroContaBancaria(), conta.getTipoContaBancaria().getNomeTipoContaBancaria(), conta.getAgencia().getBanco().getNome());
        }
        System.out.println(" ---------------------------------------------------------------------------");
    }

    public ContaBancaria selecionarContaBancaria(Cliente cliente) {
        List<ContaBancaria> contasBancarias = contaBancariaRepository.findByCliente(cliente);

        if (contasBancarias.isEmpty()) {
            System.out.println("O cliente não possui contas bancárias.");
            return null;
        }

        listarContasDisponiveis(cliente);

        Scanner scanner = new Scanner(System.in);
        ContaBancaria contaSelecionada = null;

        while (contaSelecionada == null) {

            System.out.print("Digite o número da conta bancária: ");
            String numeroConta = scanner.nextLine();

            for (ContaBancaria conta : contasBancarias) {
                if (conta.getNumeroContaBancaria().equals(numeroConta)) {
                    contaSelecionada = conta;
                    break;
                }
            }

            if (contaSelecionada == null) {
                System.out.print("Seleciona uma conta bancária válida. ");
            }
        }

        return contaSelecionada;
    }

    public void mostrarDadosContaBancaria(ContaBancaria contaBancaria) {
        System.out.println("\nDADOS DA CONTA BANCÁRIA:");
        System.out.println("Nro. Conta: " + contaBancaria.getNumeroContaBancaria());
        System.out.println("Banco: " + contaBancaria.getAgencia().getBanco().getNome());
        System.out.println("Agencia: " + contaBancaria.getAgencia().getCodigoAgencia());
        System.out.println("Tipo conta bancária: " + contaBancaria.getTipoContaBancaria().getNomeTipoContaBancaria());
        System.out.println("Data abertura Conta: " + contaBancaria.getDataAberturaContaBancaria());
        System.out.println("Saldo Atual da Conta: " + VERDE + "R$ " + String.format("%.2f", contaBancaria.getSaldoAtuaContaBancaria())+ RESET);
    }

}
