package com.example.Parivahan.Dashboard.Controller;

import com.example.Parivahan.Dashboard.Data.DashboardDataDto;
import com.example.Parivahan.Dashboard.Service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/")
    public String getDashboard(
            @RequestParam(required = false, defaultValue = "All States") String stateName,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String quarter,
            // NEW: Add manufacturerName request parameter
            @RequestParam(required = false, defaultValue = "All Manufacturers") String manufacturerName,
            Model model) {

        // Set default year and quarter if not provided
        if (year == null) { year = LocalDate.now().getYear(); }
        if (quarter == null) { quarter = "Q" + ((LocalDate.now().getMonthValue() - 1) / 3 + 1); }

        // 1. Fetch the main dashboard data, now including the manufacturer filter
        List<DashboardDataDto> dashboardData = dashboardService.getDashboardData(stateName, year, quarter, manufacturerName);

        // 2. Fetch data for the filter dropdowns
        List<String> states = dashboardService.getAllStateNames();
        List<Integer> years = dashboardService.getAllYears();
        List<String> quarters = List.of("Q1", "Q2", "Q3", "Q4");
        // NEW: Get the list of manufacturers
        List<String> manufacturers = dashboardService.getAllManufacturers();

        // 3. Add all data to the model
        model.addAttribute("dashboardData", dashboardData);
        model.addAttribute("states", states);
        model.addAttribute("years", years);
        model.addAttribute("quarters", quarters);
        model.addAttribute("manufacturers", manufacturers); // NEW
        model.addAttribute("selectedState", stateName);
        model.addAttribute("selectedYear", year);
        model.addAttribute("selectedQuarter", quarter);
        model.addAttribute("selectedManufacturer", manufacturerName); // NEW

        // 4. Return the view name
        return "index";
    }
}
