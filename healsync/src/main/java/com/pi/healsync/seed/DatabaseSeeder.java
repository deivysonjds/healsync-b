package com.pi.healsync.seed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.pi.healsync.models.Hospital;
import com.pi.healsync.repositories.HospitalRepository;
import com.pi.healsync.services.HospitalService;

@Component
@Profile("dev")
public class DatabaseSeeder implements CommandLineRunner {
    @Autowired
    private HospitalService service;

    @Autowired
    private HospitalRepository repository;

    @Override
    public void run(String... args){
        if (repository.count() == 0) {
            Hospital hospital = new Hospital();
            hospital.setName("Restauração");
            hospital.setCnpj("12345678000195");
            hospital.setPassword("rest123#");
            hospital.setEmail("restauracao@gmail.com");
            hospital.setPhone("81912345678");

            service.insert(hospital);
        }
    }
}
