package com.javne.dentalprofitapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class Doctor {
    private int id;
    private Date date;
    private String name;
    private BigDecimal amount;
    private double hours;
}
