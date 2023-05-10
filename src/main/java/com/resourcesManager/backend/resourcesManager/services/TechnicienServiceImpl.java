package com.resourcesManager.backend.resourcesManager.services;

import com.resourcesManager.backend.resourcesManager.entities.*;
import com.resourcesManager.backend.resourcesManager.exceptions.EntityAlreadyExistsException;
import com.resourcesManager.backend.resourcesManager.exceptions.NotFoundException;
import com.resourcesManager.backend.resourcesManager.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class TechnicienServiceImpl implements TechnicienService {

    private final TechnicienRepository technicienRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationServiceImpl authenticationService;

    @Override
    public Technicien addTechnicien(Technicien technicien) {
        User existuser = userRepository.findUserByUsername(technicien.getUsername());
        if (existuser != null) {
            throw new EntityAlreadyExistsException("Username:" + existuser.getUsername() + " already exists !! Try another username");
        }
        technicien.setPassword(passwordEncoder.encode(technicien.getPassword()));
        technicien.setRoles(List.of(authenticationService.saveRole("technicien")));
        return technicienRepository.save(technicien);
    }

    @Override
    public Technicien updateTechnicien(Technicien technicien) {
        return technicienRepository.save(technicien);
    }

    @Override
    public void deleteTechnicien(String id) {
        technicienRepository.deleteById(id);
    }

    @Override
    public List<Technicien> getAllTechniciens() {
        return technicienRepository.findAll();
    }

    @Override
    public Technicien getTechnicienById(String id) {
        return technicienRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("technicien with id:" + id + "not found"));
    }

}