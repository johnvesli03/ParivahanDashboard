package com.example.Parivahan.Dashboard.Service;

import com.example.Parivahan.Dashboard.Data.DashboardDataDto;
import com.example.Parivahan.Dashboard.Data.ManufacturerDataDto;
import com.example.Parivahan.Dashboard.Data.VehicleClassDto;
import com.example.Parivahan.Dashboard.model.VehicleData;
import com.example.Parivahan.Dashboard.Repository.VehicleDataRepository;
import com.example.Parivahan.Dashboard.Util.CalculationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private VehicleDataRepository vehicleDataRepository;

    // UPDATED: Method signature now includes manufacturerName
    @Override
    public List<DashboardDataDto> getDashboardData(String stateName, int year, String quarter, String manufacturerName) {
        // --- Data Fetching ---
        List<VehicleData> currentData = vehicleDataRepository.findByYearAndQuarter(year, quarter);
        List<VehicleData> prevYearData = vehicleDataRepository.findByYearAndQuarter(year - 1, quarter);
        String prevQuarter = getPreviousQuarter(quarter);
        int prevQuarterYear = quarter.equals("Q1") ? year - 1 : year;
        List<VehicleData> prevQuarterData = vehicleDataRepository.findByYearAndQuarter(prevQuarterYear, prevQuarter);

        // --- Filtering ---
        if (!"All States".equalsIgnoreCase(stateName)) {
            currentData = filterByState(currentData, stateName);
            prevYearData = filterByState(prevYearData, stateName);
            prevQuarterData = filterByState(prevQuarterData, stateName);
        }
        // NEW: Add filtering for manufacturer if one is selected
        if (!"All Manufacturers".equalsIgnoreCase(manufacturerName)) {
            currentData = filterByManufacturer(currentData, manufacturerName);
            prevYearData = filterByManufacturer(prevYearData, manufacturerName);
            prevQuarterData = filterByManufacturer(prevQuarterData, manufacturerName);
        }

        // --- DTO Population ---
        DashboardDataDto dto = new DashboardDataDto(stateName, year, quarter);

        // Populate the detailed vehicle class data
        populateDetailedData(dto, currentData, prevYearData, prevQuarterData);

        // Populate the manufacturer-specific data
        populateManufacturerData(dto, currentData, prevYearData, prevQuarterData);

        List<DashboardDataDto> result = new ArrayList<>();
        result.add(dto);
        return result;
    }

    private void populateManufacturerData(DashboardDataDto dto, List<VehicleData> currentData, List<VehicleData> prevYearData, List<VehicleData> prevQuarterData) {
        Map<String, Long> manufacturerCounts = aggregateManufacturerCounts(currentData);
        Map<String, Long> prevYearCounts = aggregateManufacturerCounts(prevYearData);
        Map<String, Long> prevQuarterCounts = aggregateManufacturerCounts(prevQuarterData);

        manufacturerCounts.forEach((manufacturer, currentCount) -> {
            long prevYearCount = prevYearCounts.getOrDefault(manufacturer, 0L);
            long prevQuarterCount = prevQuarterCounts.getOrDefault(manufacturer, 0L);

            double yoy = CalculationUtil.calculatePercentageChange(currentCount, prevYearCount);
            double qoq = CalculationUtil.calculatePercentageChange(currentCount, prevQuarterCount);

            dto.getManufacturerData().add(new ManufacturerDataDto(manufacturer, currentCount, yoy, qoq));
        });
    }

    private void populateDetailedData(DashboardDataDto dto, List<VehicleData> currentData, List<VehicleData> prevYearData, List<VehicleData> prevQuarterData) {
        Map<String, Map<String, Long>> currentCountsMap = aggregateDetailedCounts(currentData);
        Map<String, Map<String, Long>> prevYearCountsMap = aggregateDetailedCounts(prevYearData);
        Map<String, Map<String, Long>> prevQuarterCountsMap = aggregateDetailedCounts(prevQuarterData);

        currentCountsMap.forEach((vehicleType, classCounts) -> {
            List<VehicleClassDto> classDtoList = new ArrayList<>();
            long totalForType = 0;
            for (Map.Entry<String, Long> entry : classCounts.entrySet()) {
                String vehicleClass = entry.getKey();
                long currentCount = entry.getValue();
                totalForType += currentCount;
                long prevYearCount = prevYearCountsMap.getOrDefault(vehicleType, Map.of()).getOrDefault(vehicleClass, 0L);
                long prevQuarterCount = prevQuarterCountsMap.getOrDefault(vehicleType, Map.of()).getOrDefault(vehicleClass, 0L);
                double yoy = CalculationUtil.calculatePercentageChange(currentCount, prevYearCount);
                double qoq = CalculationUtil.calculatePercentageChange(currentCount, prevQuarterCount);
                classDtoList.add(new VehicleClassDto(vehicleClass, currentCount, yoy, qoq));
            }
            dto.getDetailedData().put(vehicleType, classDtoList);
            dto.getTotalCounts().put(vehicleType, totalForType);
        });
    }

    // --- Helper Methods ---
    private Map<String, Long> aggregateManufacturerCounts(List<VehicleData> data) {
        return data.stream()
                .filter(v -> Objects.nonNull(v.getManufacturer()))
                .collect(Collectors.groupingBy(VehicleData::getManufacturer, Collectors.summingLong(VehicleData::getRegistrationCount)));
    }

    private Map<String, Map<String, Long>> aggregateDetailedCounts(List<VehicleData> data) {
        return data.stream()
                .filter(v -> Objects.nonNull(v.getVehicleType()) && Objects.nonNull(v.getVehicleClass()))
                .collect(Collectors.groupingBy(VehicleData::getVehicleType, Collectors.groupingBy(VehicleData::getVehicleClass, Collectors.summingLong(VehicleData::getRegistrationCount))));
    }

    private List<VehicleData> filterByManufacturer(List<VehicleData> data, String manufacturerName) {
        return data.stream().filter(d -> d.getManufacturer().equalsIgnoreCase(manufacturerName)).collect(Collectors.toList());
    }

    // --- Unchanged Methods ---
    @Override
    public List<String> getAllStateNames() { return vehicleDataRepository.findDistinctStateNames(); }
    @Override
    public List<Integer> getAllYears() { return vehicleDataRepository.findDistinctYears(); }
    @Override
    public List<String> getAllManufacturers() { return vehicleDataRepository.findDistinctManufacturers(); }
    private List<VehicleData> filterByState(List<VehicleData> data, String stateName) {
        return data.stream().filter(d -> d.getStateName().equalsIgnoreCase(stateName)).collect(Collectors.toList());
    }
    private String getPreviousQuarter(String quarter) {
        return switch (quarter) { case "Q1" -> "Q4"; case "Q2" -> "Q1"; case "Q3" -> "Q2"; case "Q4" -> "Q3"; default -> ""; };
    }
}
