package com.menumaster.contabancaria.cliente;

import com.menumaster.contabancaria.endereco.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final EnderecoService enderecoService;

    public void cadastrarCliente() {
        Cliente cliente = lerDadosCliente();

        if(cliente != null) {
            clienteRepository.save(cliente);
            System.out.println("Cadastro realizado com sucesso!");
        } else {
            System.out.println("Já existe cliente cadastrado com esse cpf!");
        }
    }

    private Cliente lerDadosCliente() {
        Scanner scanner = new Scanner(System.in);
        Cliente cliente = new Cliente();

        System.out.println("Digite seu cpf: ");
        String cpf = scanner.nextLine();

        if(!clienteRepository.existsByCpfCliente(cpf)) {
            cliente.setCpfCliente(cpf);

            System.out.println("Digite seu nome: ");
            cliente.setNomeCliente(scanner.nextLine());

            cliente.setEndereco(enderecoService.lerEndereco());

            System.out.println("Digite o número do endereço: ");
            cliente.setNumeroEndereco(scanner.nextLine());

            System.out.println("Digite o complemento do endereco: ");
            cliente.setComplementoEndereco(scanner.nextLine());

            return cliente;
        }

        return null;
    }
}
