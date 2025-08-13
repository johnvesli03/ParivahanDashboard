package com.example.Parivahan.Dashboard.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "vehicle")
@Data
public class VehicleData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "state_code")
    private String stateCode;

    @Column(name = "state_name")
    private String stateName;

    @Column(name = "vehicle_type") // e.g., '2W', '3W', '4W'
    private String vehicleType;

    // NEW: Add the vehicle_class column
    @Column(name = "vehicle_class") // e.g., 'MOTOR CAR', 'SCOOTER'
    private String vehicleClass;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "registration_count")
    private int registrationCount;

    @Column(name = "year")
    private int year;

    @Column(name = "quarter")
    private String quarter;
}
