package com.javne.dentalprofitapp.configuration;

import com.javne.dentalprofitapp.entity.Doctor;
import com.javne.dentalprofitapp.repository.DoctorRepository;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Configuration
@AllArgsConstructor
public class DoctorConfiguration {

    private final DoctorRepository doctorRepository;

    @Bean
    public CommandLineRunner commandLineRunner(DoctorRepository repository) {
        return args -> {
            List<Doctor> doctors = generateDoctors(50);
            repository.saveAll(doctors);
        };
    }

    private List<Doctor> generateDoctors(int count) {
        List<Doctor> doctors = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            String[] names = {"Dr. Smith", "Dr. Johnson", "Dr. Williams", "Dr. Brown", "Dr. Jones"};
            String name = names[random.nextInt(names.length)];
            BigDecimal amount = BigDecimal.valueOf(random.nextDouble() * 10000 + 5000);
            double hours = random.nextDouble() * 40 + 20;
            Date date = new Date();

            Doctor doctor = Doctor.builder()
                    .date(date)
                    .name(name)
                    .amount(amount)
                    .hours(hours)
                    .build();

            doctors.add(doctor);
        }

        return doctors;
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("DentalProfitApp").version("1.0").description("FirstRestApi")
                        .contact(new Contact()
                                .name("Ewelina Borkowska")
                                .url("https://github.com/Javne/DentalProfitApp.git")
                                .email("javaczysen@gmail.com")));
    }
}
