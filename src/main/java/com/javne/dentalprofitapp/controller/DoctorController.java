package com.javne.dentalprofitapp.controller;

import com.javne.dentalprofitapp.entity.Doctor;
import com.javne.dentalprofitapp.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    @GetMapping("/home")
    public String home(Model model) {
        List<Doctor> doctors = doctorRepository.findAll();
        model.addAttribute("doctors", doctors);
        return "home";
    }


    @GetMapping("/bestPaidPerHour")
    public String getBestPaidDoctorPerHour(Model model) {
        Optional<Doctor> bestPaidDoctor = doctorRepository.findBestPaidDoctorPerHour();
        bestPaidDoctor.ifPresent(doctor -> model.addAttribute("bestPaidDoctor", doctor));
        return "home";
    }



    @GetMapping("")
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<Doctor> getDoctorByName(@PathVariable String name) {
        Optional<Doctor> optionalDoctor = doctorRepository.findByName(name);
        return optionalDoctor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable int id) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        return optionalDoctor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<List<Doctor>> addDoctors(@RequestBody List<Doctor> doctors) {
        List<Doctor> savedDoctors = doctorRepository.saveAll(doctors);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDoctors);
    }

    @PostMapping("/addDoctor")
    public ResponseEntity<String> addDoctor(@RequestBody Doctor doctor) {
        Doctor deletedDoctor = doctorRepository.findFirstDeletedDoctorOrderByDateAsc();
        if (deletedDoctor != null) {
            deletedDoctor.setDate(doctor.getDate());
            deletedDoctor.setName(doctor.getName());
            deletedDoctor.setAmount(doctor.getAmount());
            deletedDoctor.setHours(doctor.getHours());
            deletedDoctor.setDeleted(false);
            doctorRepository.save(deletedDoctor);
        } else {
            doctorRepository.save(doctor);
        }

        return ResponseEntity.ok("Doctor added successfully");
    }

    @PutMapping("/byName/{name}")
    public ResponseEntity<String> updateDoctor(@PathVariable String name, @RequestBody Doctor updatedDoctor) {
        Optional<Doctor> optionalDoctor = doctorRepository.findByName(name);
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            doctor.setName(updatedDoctor.getName());
            doctor.setDate(updatedDoctor.getDate());
            doctor.setAmount(updatedDoctor.getAmount());
            doctor.setHours(updatedDoctor.getHours());
            doctorRepository.save(doctor);
            return ResponseEntity.ok("Doctor updated");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/byName/{name}")
    public ResponseEntity<String> deleteDoctorByName(@PathVariable String name) {
        Optional<Doctor> optionalDoctor = doctorRepository.findByName(name);
        if (optionalDoctor.isPresent()) {
            doctorRepository.delete(optionalDoctor.get());
            return ResponseEntity.ok("Doctor deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/byId/{id}")
    public ResponseEntity<String> deleteDoctorById(@PathVariable int id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return ResponseEntity.ok("Doctor deleted");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
