package com.smartERP.Backend.DTOs;

import com.smartERP.Backend.Entities.voucher.VoucherType;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public class voucherDTO {
    public record voucherRequest(
            @NotBlank(message = "voucher number is required!") String voucherNumber,

            @NotBlank(message = "voucher type is required!") VoucherType voucherType,

            @NotNull(message = "amount is required!") @Positive(message = "amount should not be negative!") Double amount,

            @NotNull(message = "Trasanction date is required!") LocalDateTime transactionDate,

            String description,

            @NotNull(message = "Ledger ID is required!") Long ledgerId) {
    }

    public record voucherResponse(
            Long id,
            String voucherNumber,
            VoucherType voucherType,
            Double amount,
            LocalDateTime transactionDate,
            String description,
            Long ledgerId) {
    }
}
