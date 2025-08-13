package com.example.Parivahan.Dashboard.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManufacturerDataDto {
    private String manufacturerName;
    private long count;
    private double yoyGrowth;
    private double qoqGrowth;
}
