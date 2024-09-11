package com.menumaster.contabancaria.contabancaria;

import com.menumaster.contabancaria.agencia.Agencia;
import com.menumaster.contabancaria.agencia.AgenciaService;
import com.menumaster.contabancaria.cliente.Cliente;
import com.menumaster.contabancaria.cliente.ClienteService;
import com.menumaster.contabancaria.tipocontabancaria.TipoContaBancariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class ContaBancariaService {

    private final ContaBancariaRepository contaBancariaRepository;
    private final ClienteService clienteService;
    private final AgenciaService agenciaService;
    private final TipoContaBancariaService tipoContaBancariaService;

    public void cadastrarContaBancaria() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite seu cpf: ");
        String cpf = scanner.nextLine();

        if(!clienteService.verificarSeExisteCliente(cpf)) {
            System.out.println("É necessário cadastrar-se antes de criar uma conta bancária!");
            return;
        }

        ContaBancaria contaBancaria = lerDadosContaBancaria(cpf);

        if(contaBancaria != null) {
            contaBancariaRepository.save(contaBancaria);
            System.out.println("Conta bancária registrada com sucesso!");
        } else {
            System.out.println("Não foi possível concluir o cadastro da conta!");
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

        System.out.println("Contas bancárias disponíveis:");
        for (ContaBancaria conta : contasBancarias) {
            System.out.println("Número: " + conta.getNumeroContaBancaria() + ", Tipo: " + conta.getTipoContaBancaria().getNomeTipoContaBancaria());
        }
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

            System.out.println("Digite o número da conta bancária:");
            String numeroConta = scanner.nextLine();

            for (ContaBancaria conta : contasBancarias) {
                if (conta.getNumeroContaBancaria().equals(numeroConta)) {
                    contaSelecionada = conta;
                    break;
                }
            }

            if (contaSelecionada == null) {
                System.out.println("Número de conta inválido. Tente novamente.");
            }
        }

        return contaSelecionada;
    }

}
