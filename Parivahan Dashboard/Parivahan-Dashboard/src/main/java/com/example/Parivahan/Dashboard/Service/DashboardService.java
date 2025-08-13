package com.example.Parivahan.Dashboard.Service;

import com.example.Parivahan.Dashboard.Data.DashboardDataDto;
import java.util.List;

public interface DashboardService {

    // UPDATED: Added manufacturerName as a filter
    List<DashboardDataDto> getDashboardData(String stateName, int year, String quarter, String manufacturerName);

    List<String> getAllStateNames();
    List<Integer> getAllYears();

    // NEW: Method to get all manufacturer names
    List<String> getAllManufacturers();
}
