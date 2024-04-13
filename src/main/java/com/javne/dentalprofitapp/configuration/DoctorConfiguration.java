package com.javne.dentalprofitapp.configuration;
import com.javne.dentalprofitapp.entity.Doctor;
import com.javne.dentalprofitapp.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Date;

@Configuration
@AllArgsConstructor
public class DoctorConfiguration {

    private final DoctorRepository doctorRepository;

    @Bean
    public CommandLineRunner commandLineRunner(DoctorRepository repository) {
        return args -> {
            var doctor1 = Doctor.builder()
                    .date(new Date())
                    .name("Dr. Smith")
                    .amount(BigDecimal.valueOf(1000))
                    .hours(40.0)
                    .build();

            var doctor2 = Doctor.builder()
                    .date(new Date())
                    .name("Dr. Johnson")
                    .amount(BigDecimal.valueOf(1200))
                    .hours(45.5)
                    .build();

            var doctor3 = Doctor.builder()
                    .date(new Date())
                    .name("Dr. Williams")
                    .amount(BigDecimal.valueOf(1500))
                    .hours(50.0)
                    .build();

            repository.save(doctor1);
            repository.save(doctor2);
            repository.save(doctor3);
        };
    }
}
