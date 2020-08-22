package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
@AllArgsConstructor
@Data
public class PricePerDay {

    private String state;
    private BigDecimal highQualityPrice;
    private BigDecimal midQualityPrice;
    private BigDecimal lowQualityPrice;
    private LocalDate date;

}