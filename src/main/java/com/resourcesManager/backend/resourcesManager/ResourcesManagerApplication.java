package com.resourcesManager.backend.resourcesManager;


import com.resourcesManager.backend.resourcesManager.entities.Role;
import com.resourcesManager.backend.resourcesManager.entities.User;
import com.resourcesManager.backend.resourcesManager.services.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.stream.Stream;

@SpringBootApplication
public class ResourcesManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResourcesManagerApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(AuthenticationService authenticationService) {
//        return args -> {
////            Stream.of("user", "responsable", "fournisseur", "technicien", "CHEF_DEP",
////                    "PROF").forEach(name -> {
////                Role
////                        role = new Role();
////                role.setNomRole(name);
////                authenticationService.saveRole(role.getNomRole());
////            });
////            Role responsableRole = authenticationService.getRole("responsable");
////            User responsable = new User();
////            responsable.setUsername("responsable");
////            responsable.setPassword("responsable");
////            responsable.setRoles(new ArrayList<>());
////            responsable.getRoles().add(responsableRole);
////            authenticationService.saveResponsable(responsable);
//        };
//    }

}
