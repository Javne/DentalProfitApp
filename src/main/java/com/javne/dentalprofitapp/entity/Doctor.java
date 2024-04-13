package com.javne.dentalprofitapp.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Doctor {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "doctor_sequence"
    )
    @SequenceGenerator(
            name = "doctor_sequence",
            sequenceName = "doctor_sequence",
            allocationSize = 1
    )
    private Integer id;

    private Date date;
    private String name;
    private BigDecimal amount;
    private double hours;

    // Dodajemy pole, które będzie określało, czy dany lekarz został usunięty
    private Boolean deleted = false;

    // Dodajemy konstruktor, który będzie używany do tworzenia nowego lekarza na puste lub kolejne miejsce
    public Doctor(Date date, String name, BigDecimal amount, double hours) {
        this.date = date;
        this.name = name;
        this.amount = amount;
        this.hours = hours;
    }
}
