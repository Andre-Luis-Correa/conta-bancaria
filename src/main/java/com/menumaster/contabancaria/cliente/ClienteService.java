package com.menumaster.contabancaria.cliente;

import com.menumaster.contabancaria.contato.email.Email;
import com.menumaster.contabancaria.contato.email.EmailService;
import com.menumaster.contabancaria.contato.telefone.Telefone;
import com.menumaster.contabancaria.contato.telefone.TelefoneService;
import com.menumaster.contabancaria.endereco.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final EnderecoService enderecoService;

    private final TelefoneService telefoneService;

    private final EmailService emailService;

    public void cadastrarCliente() {
        Cliente cliente = lerDadosCliente();

        if(cliente != null) {
            clienteRepository.save(cliente);
            System.out.println("\nCadastro realizado com sucesso!\n");
        } else {
            System.out.println("\nJá existe cliente cadastrado com esse cpf!\n");
        }
    }

    private Cliente lerDadosCliente() {
        Scanner scanner = new Scanner(System.in);
        Cliente cliente = new Cliente();

        System.out.println("\nDADOS PESSOAIS:");
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        if(!clienteRepository.existsByCpfCliente(cpf)) {
            cliente.setCpfCliente(cpf);

            System.out.print("Nome: ");
            cliente.setNomeCliente(scanner.nextLine());

            System.out.println("\nDADOS DO ENDEREÇO:");
            cliente.setEndereco(enderecoService.lerEndereco());

            System.out.print("Número endereço: ");
            cliente.setNumeroEndereco(scanner.nextLine());

            System.out.print("Complemento endereço: ");
            cliente.setComplementoEndereco(scanner.nextLine());

            return cliente;
        }

        return null;
    }

    public boolean verificarSeExisteCliente(String cpf) {
        return clienteRepository.existsByCpfCliente(cpf);
    }

    public Cliente buscarClientePorCpf(String cpf) {
        return clienteRepository.findByCpfCliente(cpf);
    }

    public void mostrarDadosCliente(Cliente cliente) {
        System.out.println("\nDADOS DO CLIENTE:");
        System.out.println("Nome: " + cliente.getNomeCliente());
        System.out.println("CPF: " + cliente.getCpfCliente());

        telefoneService.mostrarTelefonesCliente(cliente);
        emailService.mostrarEmailCliente(cliente);
        enderecoService.mostrarEnderecoCliente(cliente);

    }

    public List<Telefone> buscarTelefones(Cliente cliente) {
        return telefoneService.findAll(cliente);
    }

    public List<Email> buscarEmails(Cliente cliente) {
        return emailService.findAll(cliente);
    }
}
