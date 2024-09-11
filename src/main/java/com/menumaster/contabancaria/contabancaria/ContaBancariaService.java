package com.menumaster.contabancaria.contabancaria;

import com.menumaster.contabancaria.agencia.Agencia;
import com.menumaster.contabancaria.agencia.AgenciaService;
import com.menumaster.contabancaria.cliente.ClienteService;
import com.menumaster.contabancaria.tipocontabancaria.TipoContaBancariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
}
