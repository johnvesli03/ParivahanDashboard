package com.example.Parivahan.Dashboard.Repository;

import com.example.Parivahan.Dashboard.model.VehicleData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleDataRepository extends JpaRepository<VehicleData, Long> {

    List<VehicleData> findByYearAndQuarter(int year, String quarter);
    List<VehicleData> findByYear(int year);

    @Query("SELECT DISTINCT v.stateName FROM VehicleData v ORDER BY v.stateName")
    List<String> findDistinctStateNames();

    @Query("SELECT DISTINCT v.year FROM VehicleData v ORDER BY v.year DESC")
    List<Integer> findDistinctYears();

    // NEW: Add a method to get all unique manufacturer names
    @Query("SELECT DISTINCT v.manufacturer FROM VehicleData v ORDER BY v.manufacturer")
    List<String> findDistinctManufacturers();
}
