package com.javne.dentalprofitapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
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
    private BigDecimal hourlyRate;


    private Boolean deleted = false;

    public Doctor(Date date, String name, BigDecimal amount, double hours) {
        this.date = date;
        this.name = name;
        this.amount = amount;
        this.hours = hours;
    }

    public BigDecimal getHourlyRate() {
        if (hours == 0) {
            return BigDecimal.ZERO;
        }
        return amount.divide(BigDecimal.valueOf(hours), 2, RoundingMode.HALF_UP);
    }

    public void setHourlyRate(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

}
