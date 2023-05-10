package com.resourcesManager.backend.resourcesManager.services;

import com.resourcesManager.backend.resourcesManager.entities.MembreDepartement;
import com.resourcesManager.backend.resourcesManager.entities.Role;
import com.resourcesManager.backend.resourcesManager.exceptions.NotFoundException;
import com.resourcesManager.backend.resourcesManager.repositories.MembreDepartementRepository;
import com.resourcesManager.backend.resourcesManager.repositories.RoleRepository;
import com.resourcesManager.backend.resourcesManager.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor

public class MembreDepartementServiceImpl implements MembreDepartementService {

    private final MembreDepartementRepository membreDepartementRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<MembreDepartement> getAllMembresDepartement() {
        return membreDepartementRepository.findAll();
    }

    public MembreDepartement getMembreDepartement(String id) {
        return membreDepartementRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Le membre departement avec l'id = " + id + " est introuvable")
        );
    }

    @Override
    public MembreDepartement addMembreDepartement(MembreDepartement membreDepartement) {
        updateMembreRoles(membreDepartement);
        membreDepartement.setPassword(passwordEncoder.encode(membreDepartement.getPassword()));
        return membreDepartementRepository.save(membreDepartement);
    }

    @Override
    public MembreDepartement updateMembreDepartement(MembreDepartement membreDepartement) {
        updateMembreRoles(membreDepartement);
        return membreDepartementRepository.save(membreDepartement);
    }

    private void updateMembreRoles(MembreDepartement membreDepartement) {
        Collection<Role> roles = membreDepartement.getRoles();
        List<Role> rolesMembre = new ArrayList<>();
        for (Role role : roles) {
            switch (role.getNomRole()) {
                case "PROF":
                    rolesMembre.add(roleRepository.findRoleByNomRole("PROF"));
                    break;
                case "CHEF_DEP":
                    rolesMembre.addAll(List.of(
                            roleRepository.findRoleByNomRole("PROF"),
                            roleRepository.findRoleByNomRole("CHEF_DEP")
                    ));
                    break;
            }
        }
        membreDepartement.setRoles(rolesMembre);
    }

    @Override
    public List<MembreDepartement> getMembresByIdDepartement(Long idDepartement) {
        return this.membreDepartementRepository.getMembreDepartementByIdDepartement(idDepartement);
    }

    @Override
    public void deleteMembreDepartement(String id) {
        membreDepartementRepository.deleteById(id);
        userRepository.deleteById(id);
    }

}
