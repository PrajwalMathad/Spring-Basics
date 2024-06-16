package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student one = new Student(
                    "Prajwal",
                    "Mathad",
                    "mathad.p@neu.com",
                    LocalDate.of(1996, Month.APRIL, 25)
            );
            Student two = new Student(
                    "test",
                    "test",
                    "test.p@neu.com",
                    LocalDate.of(1997, Month.MARCH, 25)
            );
            studentRepository.saveAll(List.of(one, two));
        };
    }
}
