package com.example.Parivahan.Dashboard.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleClassDto {
    private String className;
    private long count;
    private double yoyGrowth;
    private double qoqGrowth;
}
