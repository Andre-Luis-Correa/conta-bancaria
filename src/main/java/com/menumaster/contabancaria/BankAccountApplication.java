package com.menumaster.contabancaria;

import com.menumaster.contabancaria.menu.Menu;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@SpringBootApplication
public class BankAccountApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(BankAccountApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Aplicação inicializada!");
        Menu menu = new Menu();
        menu.inicializarMenu();
    }
}
