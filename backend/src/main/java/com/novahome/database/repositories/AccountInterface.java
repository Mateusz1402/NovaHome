package com.novahome.database.repositories;

import com.novahome.database.tables.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountInterface extends JpaRepository<Account, Integer>{
    boolean existsByAccountName(String username);
    boolean existsByAccountNameAndAccountPass(String name, String pass);
}
