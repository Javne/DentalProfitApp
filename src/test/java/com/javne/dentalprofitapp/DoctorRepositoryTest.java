package com.javne.dentalprofitapp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest


public class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Test
    public void testGetByName() {
        Doctor doctor = doctorRepository.getByName("Dr.Kwaśniewska").orElse(null);
        assertNotNull(doctor);
        assertEquals("Dr.Kwaśniewska", doctor.getName());
    }
}
