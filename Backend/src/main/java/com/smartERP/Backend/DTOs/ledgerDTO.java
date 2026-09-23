package com.smartERP.Backend.DTOs;

import com.smartERP.Backend.Entities.ledger.AccountType;
import jakarta.validation.constraints.*;

public class ledgerDTO {

    public record ledgerRequest(
            @NotBlank(message = "account name is required!") String accountName,

            @NotNull(message = "account type is required!") AccountType accountType,

            @NotNull(message = "Initial balance is required!") Double balance) {
    }

    public record ledgerResponse(
            Long id,
            String accountName,
            AccountType accountType,
            Double balance) {
    }
}
