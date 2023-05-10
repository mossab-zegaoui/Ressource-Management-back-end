package com.resourcesManager.backend.resourcesManager.repositories;

import com.resourcesManager.backend.resourcesManager.entities.Departement;
import com.resourcesManager.backend.resourcesManager.entities.MembreDepartement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MembreDepartementRepository extends JpaRepository<MembreDepartement, String> {

    List<MembreDepartement> getMembreDepartementByIdDepartement(Long idDepartement);

}
