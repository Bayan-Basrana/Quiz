package com.example.testt.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

    @NotEmpty(message = "id is empty")
    private String id;
    @NotEmpty(message = "name is empty")
    private String name;
    @NotNull(message = "number_of_pages is empty")
    @PositiveOrZero(message = " number_of_pages must be Positive Or Zero  ")
    private int number_of_pages;
    @NotNull(message = "price is empty")
    private double price;
    @NotEmpty(message = " category is empty ")
    @Pattern(regexp = "novel|academic")
    private String category;
    @NotNull(message = " isAvailable is empty")
    private boolean isAvailable;
}
