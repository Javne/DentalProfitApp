package com.javne.dentalprofitapp.service;

import com.javne.dentalprofitapp.entity.Doctor;
import com.javne.dentalprofitapp.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public ResponseEntity<String> addDoctor(Doctor doctor) {
        Integer nextId = findNextAvailableId();
        if (nextId != null) {
            doctor.setId(nextId);
        }
        doctorRepository.save(doctor);
        return ResponseEntity.ok("Doctor added successfully");
    }
    private Integer findNextAvailableId() {
        Doctor deletedDoctor = doctorRepository.findFirstDeletedDoctorOrderByDateAsc();
        if (deletedDoctor != null) {
            return deletedDoctor.getId();
        } else {
            Integer maxId = doctorRepository.findMaxId();
            return maxId != null ? maxId + 1 : 1;
        }
    }
}
