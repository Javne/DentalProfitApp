package com.javne.dentalprofitapp.controller;

import com.javne.dentalprofitapp.entity.Doctor;
import com.javne.dentalprofitapp.entity.DoctorDTO;
import com.javne.dentalprofitapp.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*Klasa DoctorController
Opis: Kontroler obsługujący zapytania dotyczące lekarzy.
Metody:
Metody obsługujące żądania HTTP: GET, POST, DELETE.*/

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctorsSortedByHourlyRateDescending());
        return "home";
    }

    @GetMapping("")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable int id) {
        Optional<Doctor> optionalDoctor = doctorService.findDoctorById(id);
        return optionalDoctor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/addDoctor")
    public ResponseEntity<String> addDoctor(@RequestBody DoctorDTO doctorDTO) {
        try {
            doctorService.addDoctor(doctorDTO);
            return new ResponseEntity<>("Doctor added successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add doctor", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
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
