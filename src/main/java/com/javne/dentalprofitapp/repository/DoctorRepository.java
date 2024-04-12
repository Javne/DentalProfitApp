package com.javne.dentalprofitapp.repository;

import com.javne.dentalprofitapp.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    Optional<Doctor> findByName(String name);

    @Query("SELECT MAX(d.id) FROM Doctor d")
    Integer findMaxId();

    @Query("SELECT d FROM Doctor d WHERE d.deleted = true ORDER BY d.date ASC")
    Doctor findFirstDeletedDoctorOrderByDateAsc();

}




