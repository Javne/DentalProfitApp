package com.javne.dentalprofitapp.service;

import com.javne.dentalprofitapp.entity.Doctor;
import com.javne.dentalprofitapp.entity.DoctorDTO;
import com.javne.dentalprofitapp.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
Klasa DoctorService
Opis: Warstwa serwisowa do obsługi logiki biznesowej związanej z lekarzami.
Metody:
getAllDoctors(): Zwraca listę wszystkich lekarzy.
findDoctorById(int id): Znajduje lekarza po identyfikatorze.
addOrUpdateDoctor(Doctor doctor): Dodaje lub aktualizuje lekarza.
deleteDoctorById(int id): Usuwa lekarza po identyfikatorze.
getAllDoctorsSortedByHourlyRateDescending(): Zwraca listę wszystkich lekarzy posortowaną malejąco według stawki godzinowej.
addDoctor(DoctorDTO doctorDTO): Dodaje nowego lekarza na podstawie obiektu DTO.
findNextAvailableId(): Znajduje następny dostępny identyfikator lekarza.
calculateHourlyRate(BigDecimal amount, double hours): Oblicza stawkę godzinową na podstawie kwoty i liczby godzin.*/

@AllArgsConstructor
@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Optional<Doctor> findDoctorById(int id) {
        return doctorRepository.findById(id);
    }

    public Doctor addOrUpdateDoctor(Doctor doctor) {
        if (doctor.getId() == null) {
            doctor.setId(findNextAvailableId());
        }
        return doctorRepository.save(doctor);
    }

    public Optional<Doctor> updateDoctor(int id, Doctor updatedDoctor) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        if (optionalDoctor.isPresent()) {
            updatedDoctor.setId(id);
            return Optional.of(doctorRepository.save(updatedDoctor));
        }
        return Optional.empty();
    }

    public boolean deleteDoctorById(int id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Doctor> getAllDoctorsSortedByHourlyRateDescending() {
        return doctorRepository.findAll().stream()
                .sorted(Comparator.comparing(Doctor::getHourlyRate).reversed())
                .collect(Collectors.toList());
    }

    @Transactional
    public void addDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = mapDoctorDTOtoDoctor(doctorDTO);
        doctorRepository.save(doctor);
    }

    private Doctor mapDoctorDTOtoDoctor(DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setName(doctorDTO.getName());
        doctor.setAmount(doctorDTO.getAmount());
        doctor.setHours(doctorDTO.getHours().doubleValue());
        return doctor;
    }


    private Integer findNextAvailableId() {
        Doctor deletedDoctor = doctorRepository.findFirstDeletedDoctorOrderByDateAsc();
        if (deletedDoctor != null) {
            return deletedDoctor.getId();
        }
        Integer maxId = doctorRepository.findMaxId();
        return (maxId != null ? maxId + 1 : 1);
    }

    private BigDecimal calculateHourlyRate(BigDecimal amount, double hours) {
        if (hours == 0) {
            return BigDecimal.ZERO;
        }
        return amount.divide(BigDecimal.valueOf(hours), 2, RoundingMode.HALF_UP);
    }
}
