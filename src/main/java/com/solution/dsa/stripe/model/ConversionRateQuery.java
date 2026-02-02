package com.solution.dsa.stripe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConversionRateQuery {
    private String from;
    private String to;
}
