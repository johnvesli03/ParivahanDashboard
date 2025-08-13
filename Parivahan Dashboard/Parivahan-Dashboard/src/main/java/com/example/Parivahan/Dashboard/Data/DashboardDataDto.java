package com.example.Parivahan.Dashboard.Data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class DashboardDataDto {

    private String stateName;
    private int year;
    private String quarter;

    // This map holds the detailed data for vehicle classes
    private Map<String, List<VehicleClassDto>> detailedData = new HashMap<>();

    // NEW: This list will hold the data for each manufacturer
    private List<ManufacturerDataDto> manufacturerData = new ArrayList<>();

    // This map holds the total counts for each vehicle type
    private Map<String, Long> totalCounts = new HashMap<>();


    public DashboardDataDto(String stateName, int year, String quarter) {
        this.stateName = stateName;
        this.year = year;
        this.quarter = quarter;
    }
}
