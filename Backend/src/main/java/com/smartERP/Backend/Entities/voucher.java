package com.smartERP.Backend.Entities;

import com.smartERP.Backend.Entities.ledger;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "vouchers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "voucher_number", nullable = false, unique = true)
    private String voucherNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "voucher_type", nullable = false)
    private VoucherType voucherType;

    @Column(nullable = false)
    private double amount;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ledger_id", nullable = false)
    private ledger ledger;

    public enum VoucherType {
        PAYMENT, RECEIPT, JOURNAL
    }

}
