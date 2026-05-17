package com.novahome.database.services;

import com.novahome.database.repositories.AccountInterface;
import com.novahome.database.tables.Account;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AccountService {
    private final AccountInterface accountInterface;

    @Autowired
    public AccountService(AccountInterface accountInterface){
        this.accountInterface = accountInterface;
    }

    //Checking the unique
    public boolean accountNameValidation(String accountName){
        return accountInterface.existsByAccountName(accountName);
    }

    //Inserting new row
    public Account insertNewAccount(byte type, String name, String password){
        Account newAccount = new Account();
        newAccount.setAccountType(type);
        newAccount.setAccountName(name);
        newAccount.setAccountPass(password);
        return accountInterface.save(newAccount);
    }

    public boolean loginValidation(String name, String pass){
        return accountInterface.existsByAccountNameAndAccountPass(name, pass);
    }

}
