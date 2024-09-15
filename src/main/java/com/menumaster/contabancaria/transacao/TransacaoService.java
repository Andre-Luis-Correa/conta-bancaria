package com.menumaster.contabancaria.transacao;

import com.menumaster.contabancaria.cliente.Cliente;
import com.menumaster.contabancaria.cliente.ClienteService;
import com.menumaster.contabancaria.contabancaria.ContaBancaria;
import com.menumaster.contabancaria.contabancaria.ContaBancariaService;
import com.menumaster.contabancaria.contato.email.Email;
import com.menumaster.contabancaria.contato.email.EmailDTO;
import com.menumaster.contabancaria.contato.telefone.Telefone;
import com.menumaster.contabancaria.contato.telefone.TelefoneDTO;
import com.menumaster.contabancaria.endereco.Endereco;
import com.menumaster.contabancaria.report.JasperService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;
    private final ClienteService clienteService;
    private final ContaBancariaService contaBancariaService;
    private final JasperService jasperService;

    public void consultarTransacoes() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("CPF do cliente: ");
        String cpf = scanner.nextLine();

        if(clienteService.verificarSeExisteCliente(cpf)) {
            Cliente cliente = clienteService.buscarClientePorCpf(cpf);
            clienteService.mostrarNomeCpfCliente(cliente);
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
        imprimirExtrato(contaBancaria, transacoes, dataInicio, dataFim);
    }

    private void imprimirExtrato(ContaBancaria contaBancaria, List<Transacao> transacoes, LocalDate dataInicio, LocalDate dataFim) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Você deseja imprimir o extrato? (S/N): ");
        String opcao = scanner.nextLine();

        if (opcao.equalsIgnoreCase("S")) {
            Map<String, Object> params = generateReportParams(contaBancaria, dataInicio, dataFim);

            List<Telefone> telefones = clienteService.buscarTelefones(contaBancaria.getCliente());
            List<TelefoneDTO> telefoneDTOList = gerarTelefoneDTOList(telefones);
            List<Email> emails = clienteService.buscarEmails(contaBancaria.getCliente());
            List<EmailDTO> emailDTOList = gerarEmailDTOList(emails);
            List<TransacaoDTO> transacaoDTOList = gerarTransacaoDTOList(transacoes);

            Map<String, Object> collections = new HashMap<>();
            collections.put("1", telefoneDTOList);
            collections.put("2", emailDTOList);
            collections.put("3", transacaoDTOList);

            byte[] file = jasperService.reportGenerate("reports/extrato.jrxml", params, collections);

            if (file != null) {
                try (OutputStream out = new FileOutputStream("extrato.pdf")) {
                    out.write(file);
                    System.out.println("Extrato gerado e salvo em 'extrato.pdf'.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Erro ao gerar o extrato.");
            }
        } else {
            System.out.println("\nRetornando ao menu principal!");
        }
    }

    private List<TransacaoDTO> gerarTransacaoDTOList(List<Transacao> transacoes) {
        List<TransacaoDTO> transacaoDTOList = new ArrayList<>();

        for (Transacao transacao : transacoes) {
            transacaoDTOList.add(new TransacaoDTO(transacao.getDataTransacao().toString(), transacao.getCodigoTransacao(),
                    transacao.getTipoTransacao().getCodigoTipoTransacao(), transacao.getTipoTransacao().getNomeTipoTransacao(),
                    transacao.getValorTransacao().toString()));
        }

        return transacaoDTOList;
    }

    private List<EmailDTO> gerarEmailDTOList(List<Email> emails) {
        List<EmailDTO> emailDTOList = new ArrayList<>();

        for(Email email : emails) {
            emailDTOList.add(new EmailDTO(email.getEmail()));
        }

        return emailDTOList;
    }

    private List<TelefoneDTO> gerarTelefoneDTOList(List<Telefone> telefones) {
        List<TelefoneDTO> telefoneDTOList = new ArrayList<>();

        for(Telefone telefone : telefones) {
            telefoneDTOList.add(new TelefoneDTO("+" + telefone.getNumeroDDI().getNumeroDDI() + " " + telefone.getNumeroDDD().getNumeroDDD() + " " + telefone.getNumeroTelefone()));
        }

        return telefoneDTOList;
    }

    private Map<String, Object> generateReportParams(ContaBancaria contaBancaria, LocalDate dataInicio, LocalDate dataFim) {
        Map<String, Object> params = new HashMap<>();
        params.put("BANK_LOGO", "reports/img.png");
        params.put("NOME_CLIENTE", contaBancaria.getCliente().getNomeCliente());
        params.put("CPF_CLIENTE", contaBancaria.getCliente().getCpfCliente());
        params.put("ENDERECO_CLIENTE", formatarEndereco(contaBancaria.getCliente(), contaBancaria.getCliente().getEndereco()));
        params.put("NRO_CONTA_CLIENTE", contaBancaria.getNumeroContaBancaria());
        params.put("BANCO_CLIENTE", contaBancaria.getAgencia().getBanco().getNome());
        params.put("AGENCIA_CLIENTE", contaBancaria.getAgencia().getCodigoAgencia());
        params.put("TIPO_CONTA", contaBancaria.getTipoContaBancaria().getNomeTipoContaBancaria());
        params.put("DATA_ABERTURA_CONTA", contaBancaria.getDataAberturaContaBancaria().toString());
        params.put("SALDO_ATUAL", "R$ " + String.format("%.2f", contaBancaria.getSaldoAtuaContaBancaria()));
        params.put("PERIODO_EXTRATO", dataInicio + " a " + dataFim);

        return params;
    }

    private String formatarEndereco(Cliente cliente, Endereco endereco) {
        return endereco.getLogradouro().getNomeLogradouro()  + ", " + endereco.getBairro().getNomeBairro() + ", " +
                endereco.getCidade().getNomeCidade() + " - " + endereco.getCidade().getUnidadeFederativa().getSiglaUF() +
                ", " + endereco.getCep() +  ", " + cliente.getComplementoEndereco() + ", " + cliente.getNumeroEndereco();
    }

    public void mostrarTransacoesBancarias(List<Transacao> transacoes, LocalDate dataInicio, LocalDate dataFim) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("\nPeríodo do extrato: " + dataInicio.format(formatter) + " a " + dataFim.format(formatter));
        System.out.println(" ----------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("| %-12s | %-15s | %-10s | %-30s | %-30s | %-15s |\n", "Data", "Transação Cod", "Tipo Cod", "Nome Tipo", "Observação", "Valor");
        System.out.println(" ----------------------------------------------------------------------------------------------------------------------------------");

        if(transacoes.isEmpty()) {
            System.out.println("|                                         Não houveram transações nesse período                                         |");
        } else {
            for (Transacao transacao : transacoes) {
                System.out.printf("| %-12s | %-15s | %-10s | %-30s | %-30s | %-15.2f |\n",
                        transacao.getDataTransacao().format(formatter),
                        transacao.getCodigoTransacao(),
                        transacao.getTipoTransacao().getCodigoTipoTransacao(),
                        transacao.getTipoTransacao().getNomeTipoTransacao(),
                        transacao.getObservacao(),
                        transacao.getValorTransacao());
            }
        }

        System.out.println(" ----------------------------------------------------------------------------------------------------------------------------------");
    }
}
