package com.javne.dentalprofitapp.entity;
/*Klasa DoctorDTO
Opis: Obiekt DTO (Data Transfer Object) dla lekarza.
Pola:
name: Imię i nazwisko lekarza.
amount: Kwota zarobiona przez lekarza.
hours: Liczba godzin przepracowanych przez lekarza.
Metody:
Konstruktory.*/

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class DoctorDTO {

    private String name;
    private BigDecimal amount;
    private BigDecimal hours;

}
