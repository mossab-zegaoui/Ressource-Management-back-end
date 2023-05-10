package com.resourcesManager.backend.resourcesManager.repositories;

import com.resourcesManager.backend.resourcesManager.entities.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DemandeRepository extends JpaRepository<Demande, Long> {

    List<Demande> findAllByIdMembreDepartement(String id);

    List<Demande> findAllByIdDepartement(Long id);

}
