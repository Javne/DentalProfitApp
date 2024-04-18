package com.javne.dentalprofitapp;


import com.javne.dentalprofitapp.entity.Doctor;
import com.javne.dentalprofitapp.repository.DoctorRepository;
import com.javne.dentalprofitapp.service.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorService doctorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllDoctors() {
        List<Doctor> expectedDoctors = new ArrayList<>();
        when(doctorRepository.findAll()).thenReturn(expectedDoctors);

        List<Doctor> actualDoctors = doctorService.getAllDoctors();

        assertEquals(expectedDoctors, actualDoctors);
    }
}
