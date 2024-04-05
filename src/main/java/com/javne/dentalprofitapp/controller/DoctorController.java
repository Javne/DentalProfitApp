package com.javne.dentalprofitapp.controller;

import com.javne.dentalprofitapp.repository.DoctorRepository;
import com.javne.dentalprofitapp.entity.Doctor;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    DoctorRepository doctorRepository;

    @GetMapping("")
    public List<Doctor> getAll() {
        return doctorRepository.getAll();
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<Doctor> getDoctorByName(@PathVariable("name") String name) {
        Optional<Doctor> optionalDoctor = doctorRepository.getByName(name);
        if (optionalDoctor.isPresent()) {
            return ResponseEntity.ok(optionalDoctor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<Doctor> getById(@PathVariable("id") int id) {
        Optional<Doctor> optionalDoctor = doctorRepository.getById(id);
        if (optionalDoctor.isPresent()) {
            return ResponseEntity.ok(optionalDoctor.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public List<Doctor> add(@RequestBody List<Doctor> doctors) {
        for (Doctor doctor : doctors) {
            doctorRepository.save(doctor);
        }
        return new ArrayList<>(doctors);
    }

    @PostMapping("/addDoctor")
    public ResponseEntity<String> addDoctor(@RequestBody Doctor doctor) {
        doctorRepository.save(doctor);
        return ResponseEntity.ok("Doctor added successfully");
    }


    @PutMapping("/byName/{name}")
    public String update(@PathVariable("name") String name, @RequestBody Doctor updatedDoctor) {
        Optional<Doctor> optionalDoctor = doctorRepository.getByName(name);
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            doctor.setName(updatedDoctor.getName());
            doctor.setDate(updatedDoctor.getDate());
            doctor.setAmount(updatedDoctor.getAmount());
            doctor.setHours(updatedDoctor.getHours());
            doctorRepository.update(doctor);
            return "Doctor updated";
        } else {
            return "Doctor does not exist in DB";
        }
    }

    @PatchMapping("/byName/{name}")
    public String partiallyUpdated(@PathVariable("name") String name, @RequestBody Doctor updatedDoctor) {
        Optional<Doctor> optionalDoctor = doctorRepository.getByName(name);
        if (optionalDoctor.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            if (StringUtils.isNotEmpty(updatedDoctor.getName())) {
                doctor.setName(updatedDoctor.getName());
            }
            if (updatedDoctor.getAmount() != null) {
                doctor.setAmount(updatedDoctor.getAmount());
            }
            if (updatedDoctor.getHours() != 0) {
                doctor.setHours(updatedDoctor.getHours());
            }
            doctorRepository.update(doctor);
            return "Doctor updated";
        } else {
            return "Doctor does not exist in DB";
        }
    }

    @DeleteMapping("/byName/{name}")
    public String delete(@PathVariable("name") String name) {
        Optional<Doctor> optionalDoctor = doctorRepository.getByName(name);
        if (optionalDoctor.isPresent()) {
            doctorRepository.delete(optionalDoctor.get());
            return "Doctor deleted";
        } else {
            return "Doctor with name " + name + " not found";
        }
    }

    @DeleteMapping("/byId/{id}")
    public String deleteById(@PathVariable("id") int id) {
        Optional<Doctor> optionalDoctor = doctorRepository.getById(id);
        if (optionalDoctor.isPresent()) {
            doctorRepository.deleteById(id);
            return "Doctor deleted";
        } else {
            return "Doctor with id " + id + " not found";
        }
    }


}
