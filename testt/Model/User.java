package com.example.testt.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    @NotEmpty(message = "id is empty ")
private String id;
    @NotEmpty(message = "name is empty ")
@Pattern(regexp = "^[a-zA-Z]+$" ,message = "name must be character only")
    private String name;
    @NotNull(message = "age is empty")
    @Positive(message = "age must be positive number")
private int age;
    @NotNull(message = "balance is empty")
private double balance;
    @NotEmpty(message = "role is empty ")
@Pattern(regexp = "customer|libraian ")
private String role ;
}

