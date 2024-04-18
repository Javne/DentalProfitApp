package com.javne.dentalprofitapp.entity;

/*
Klasa DoctorDTO
Opis: Obiekt DTO (Data Transfer Object) dla lekarza.
Pola:
name: Imię i nazwisko lekarza.
amount: Kwota zarobiona przez lekarza.
hours: Liczba godzin przepracowanych przez lekarza.
Metody:
Konstruktory.
*/


import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DoctorDTO {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Positive(message = "Amount must be a positive number")
    private BigDecimal amount;

    @PositiveOrZero(message = "Hours must be a positive number or zero")
    private BigDecimal hours;

}
