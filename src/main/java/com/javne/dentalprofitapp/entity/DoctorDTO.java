package com.javne.dentalprofitapp.entity;

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
