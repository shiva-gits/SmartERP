package com.smartERP.Backend.DTOs;

import jakarta.validation.constraints.*;

public class BookDTO {
        public record BookRequest(
                        @NotBlank(message = "ISBN is required") String isbn,
                        @NotBlank(message = "title is required") String title,
                        @NotBlank(message = "Author is required") String author,
                        @NotNull(message = "price is required") @Positive(message = "Price should be greater than zero.") Double price) {
        }

        public record BookResponse(
                        Long id,
                        String isbn,
                        String title,
                        String author,
                        Double price) {
        }
}
