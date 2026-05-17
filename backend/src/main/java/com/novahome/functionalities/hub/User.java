package com.novahome.functionalities.hub;

import com.novahome.database.repositories.AccountInterface;
import com.novahome.database.services.AccountService;
import com.novahome.database.tables.Account;


public class User {
    private byte accountType;
    private String accountName;
    private String accountPass;
    private boolean logged;
    private final AccountService accountService;


    public User(AccountService accountService){

        this.accountType = 0;
        this.accountName = "";
        this.accountPass = "";
        this.logged = false;
        this.accountService = accountService;
    }

    public void register(byte type, String name, String pass){
        // The account type is being defined only ones.
        if(type == 1 || type == 2){
            if(!accountService.accountNameValidation(name)){
                accountService.insertNewAccount(type, name, pass);
            }
        }
    }

    public void login(String name, String pass){
        this.logged = accountService.loginValidation(name, pass);
    }

    public void logout(){
        this.logged = false;
    }

    public boolean getLogged(){
        return this.logged;
    }

}
