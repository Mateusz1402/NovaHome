package com.novahome.database.tables;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "accountType", nullable = false)
    private byte accountType;

    @Column(name = "accountName", nullable = false, length = 255)
    private String accountName;

    @Column(name = "accountPass", nullable = false, length = 255)
    private String accountPass;

}
