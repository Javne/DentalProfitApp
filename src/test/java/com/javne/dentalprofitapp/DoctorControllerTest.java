package com.javne.dentalprofitapp;


import com.javne.dentalprofitapp.controller.DoctorController;
import com.javne.dentalprofitapp.entity.Doctor;
import com.javne.dentalprofitapp.service.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class DoctorControllerTest {

    @Mock
    private DoctorService doctorService;

    @InjectMocks
    private DoctorController doctorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllDoctors() {
        List<Doctor> expectedDoctors = new ArrayList<>();
        when(doctorService.getAllDoctors()).thenReturn(expectedDoctors);

        ResponseEntity<List<Doctor>> responseEntity = doctorController.getAllDoctors();
        List<Doctor> actualDoctors = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedDoctors, actualDoctors);
    }
}
