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
        return args -> {Doctor doctor1 = new Doctor();
            doctor1.setDate(new Date());
            doctor1.setName("Dr. Smith");
            doctor1.setAmount(BigDecimal.valueOf(1000));
            doctor1.setHours(40.0);

            Doctor doctor2 = new Doctor();
            doctor2.setDate(new Date());
            doctor2.setName("Dr. Johnson");
            doctor2.setAmount(BigDecimal.valueOf(1200));
            doctor2.setHours(45.5);

            Doctor doctor3 = new Doctor();
            doctor3.setDate(new Date());
            doctor3.setName("Dr. Williams");
            doctor3.setAmount(BigDecimal.valueOf(1500));
            doctor3.setHours(50.0);

            repository.save(doctor1);
            repository.save(doctor2);
            repository.save(doctor3);

        };
    }
}
