package com.menumaster.contabancaria;

import com.menumaster.contabancaria.menu.Menu;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@SpringBootApplication
@RequiredArgsConstructor
public class BankAccountApplication implements CommandLineRunner {

    private final Menu menu;

    public static void main(String[] args) {
        SpringApplication.run(BankAccountApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Aplicação inicializada!");

        menu.inicializarMenu();
    }
}
