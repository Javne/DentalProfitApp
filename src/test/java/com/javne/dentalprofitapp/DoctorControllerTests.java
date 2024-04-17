package com.javne.dentalprofitapp;

import com.javne.dentalprofitapp.controller.DoctorController;
import com.javne.dentalprofitapp.entity.Doctor;
import com.javne.dentalprofitapp.repository.DoctorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DoctorControllerTests {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorController doctorController;

    @Test
    public void testGetDoctorByName() {
        // Given
        String name = "Dr. Smith";
        Doctor doctor = new Doctor();
        doctor.setName(name);
        Optional<Doctor> optionalDoctor = Optional.of(doctor);

        when(doctorRepository.findByName(name)).thenReturn(optionalDoctor);

        // When
        ResponseEntity<Doctor> responseEntity = doctorController.getDoctorByName(name);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.hasBody());

        Doctor responseBody = responseEntity.getBody();
        assertEquals(name, responseBody.getName());
    }

    @Test
    public void testGetDoctorById() {
        // Given
        int id = 1;
        Doctor doctor = new Doctor();
        doctor.setId(id);
        Optional<Doctor> optionalDoctor = Optional.of(doctor);

        when(doctorRepository.findById(id)).thenReturn(optionalDoctor);

        // When
        ResponseEntity<Doctor> responseEntity = doctorController.getDoctorById(id);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.hasBody());

        Doctor responseBody = responseEntity.getBody();
        assertEquals(id, responseBody.getId());
    }


    @Test
    public void testAddDoctors() {
        // Given
        List<Doctor> doctorsToAdd = new ArrayList<>();
        Doctor doctor1 = new Doctor();
        Doctor doctor2 = new Doctor();
        doctorsToAdd.add(doctor1);
        doctorsToAdd.add(doctor2);

        when(doctorRepository.saveAll(doctorsToAdd)).thenReturn(doctorsToAdd);

        // When
        ResponseEntity<List<Doctor>> responseEntity = doctorController.addDoctors(doctorsToAdd);

        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(doctorsToAdd.size(), responseEntity.getBody().size());
    }

    @Test
    public void testAddDoctor() {
        // Given
        Doctor doctor = new Doctor();

        when(doctorRepository.findFirstDeletedDoctorOrderByDateAsc()).thenReturn(null);
        when(doctorRepository.save(doctor)).thenReturn(doctor);

        // When
        ResponseEntity<String> responseEntity = doctorController.addDoctor(doctor);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Doctor added successfully", responseEntity.getBody());
    }

    @Test
    public void testUpdateDoctor() {
        // Given
        String name = "Dr. Smith";
        Doctor existingDoctor = new Doctor();
        existingDoctor.setName(name);
        Optional<Doctor> optionalDoctor = Optional.of(existingDoctor);

        Doctor updatedDoctor = new Doctor();
        updatedDoctor.setName("Dr. John");

        when(doctorRepository.findByName(name)).thenReturn(optionalDoctor);
        when(doctorRepository.save(existingDoctor)).thenReturn(existingDoctor);

        // When
        ResponseEntity<String> responseEntity = doctorController.updateDoctor(name, updatedDoctor);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Doctor updated", responseEntity.getBody());
        assertEquals(updatedDoctor.getName(), existingDoctor.getName());
    }

    @Test
    public void testDeleteDoctorByName() {
        // Given
        String name = "Dr. Smith";
        Doctor doctorToDelete = new Doctor();
        Optional<Doctor> optionalDoctor = Optional.of(doctorToDelete);

        when(doctorRepository.findByName(name)).thenReturn(optionalDoctor);

        // When
        ResponseEntity<String> responseEntity = doctorController.deleteDoctorByName(name);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Doctor deleted", responseEntity.getBody());
        verify(doctorRepository, times(1)).delete(doctorToDelete);
    }

}
