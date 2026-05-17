package com.novahome;

import com.novahome.database.repositories.AccountInterface;
import com.novahome.database.services.AccountService;
import com.novahome.database.tables.Account;
import com.novahome.functionalities.hub.CentralSystem;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.novahome.functionalities.hub.User;
import com.novahome.functionalities.devices.Light;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
@SpringBootApplication
@EnableScheduling
public class Main {
    public static void main(String[] args){
        SpringApplication.run(Main.class, args);
    }
    @Bean
    public CentralSystem centralSystem(AccountService accountService){
        User rootUser = new User(accountService);
        return new CentralSystem(rootUser,3 , 3);
    }
}
