package com.example.defaultproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class DefaultProjectApplication {
    private static Produser produser;

    public DefaultProjectApplication(Produser produser) {
        DefaultProjectApplication.produser = produser;
    }

    public static void main(String[] args) {
        SpringApplication.run(DefaultProjectApplication.class, args);

        var scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            produser.sendMessage(scanner.nextLine());
        }
    }

}
