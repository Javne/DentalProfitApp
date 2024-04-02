package com.javne.dentalprofitapp.service;

import com.javne.dentalprofitapp.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // Tutaj możesz umieścić metody serwisu, które wykorzystują DoctorRepository do operacji na danych lekarzy
}
