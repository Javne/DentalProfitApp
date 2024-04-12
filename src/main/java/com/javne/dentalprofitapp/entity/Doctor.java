package com.javne.dentalprofitapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Doctor {
    @Id
    private int id;
    private Date date;
    private String name;
    private BigDecimal amount;
    private double hours;
}
