package com.javne.dentalprofitapp.controller;

import com.javne.dentalprofitapp.entity.Doctor;
import com.javne.dentalprofitapp.entity.DoctorDTO;
import com.javne.dentalprofitapp.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctors")
@Tag(name = "Doctor Management", description = "Endpoints for managing doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/home")
    @Operation(summary = "Get all doctors sorted by hourly rate descending")
    public String home(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctorsSortedByHourlyRateDescending());
        return "home";
    }

    @GetMapping("")
    @Operation(summary = "Get all doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get doctor by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doctor found"),
            @ApiResponse(responseCode = "404", description = "Doctor not found")
    })
    public ResponseEntity<Doctor> getDoctorById(@PathVariable int id) {
        Optional<Doctor> optionalDoctor = doctorService.findDoctorById(id);
        return optionalDoctor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/addDoctor")
    @Operation(summary = "Add a new doctor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Doctor added successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error")
    })
    public ResponseEntity<String> addDoctor(@Valid @RequestBody DoctorDTO doctorDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Validation error: " + bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        try {
            doctorService.addDoctor(doctorDTO);
            return new ResponseEntity<>("Doctor added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add doctor: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete doctor by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doctor deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Doctor not found")
    })
    public ResponseEntity<Void> deleteDoctorById(@PathVariable int id) {
        boolean isDeleted = doctorService.deleteDoctorById(id);
        return isDeleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    private Doctor mapDoctorDTOtoDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setName(doctorDTO.getName());
        doctor.setAmount(doctorDTO.getAmount());
        doctor.setHours(doctorDTO.getHours().doubleValue());
        return doctor;
    }

}
