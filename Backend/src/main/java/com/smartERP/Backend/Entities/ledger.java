package com.smartERP.Backend.Entities;

import com.smartERP.Backend.Entities.voucher;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "ledger")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ledger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "account_name", nullable = false, unique = true)
    private String accountName;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    private AccountType accountType;

    @Column(nullable = false)
    private double balance;

    @OneToMany(mappedBy = "ledger", cascade = CascadeType.ALL)
    private List<voucher> vouchers;

    public enum AccountType {
        ASSET, LIABILITY, EQUITY, REVENUE, EXPENSE
    }
}
