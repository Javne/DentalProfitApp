package com.javne.dentalprofitapp.repository;

import com.javne.dentalprofitapp.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

@RepositoryDefinition(domainClass = Doctor.class, idClass = Long.class)
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
