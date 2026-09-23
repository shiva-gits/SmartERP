package com.smartERP.Backend.DTOs;

import jakarta.validation.constraints.*;

public class stockDTO {
    public record stockRequest(
            @NotBlank(message = "item name cannot be empty!") String itemName,

            @NotNull(message = "quantity is required") @Min(value = 0, message = "quantity cannot be negative!") Integer quantity,

            @NotNull(message = "unit price is required!") @Positive(message = "unit price cannot be negative!") Double unitPrice,

            @Min(value = 0, message = "reorder level cannot be negative!") Integer reorderLevel

    ) {
    }

    public record stockResponse(
            Long id,
            String itemName,
            Integer quantity,
            Double unitPrice,
            Integer reorderLevel) {
    }
}
