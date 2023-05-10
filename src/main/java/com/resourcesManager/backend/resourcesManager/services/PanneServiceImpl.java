package com.resourcesManager.backend.resourcesManager.services;

import com.resourcesManager.backend.resourcesManager.entities.Panne;
import com.resourcesManager.backend.resourcesManager.entities.PanneAvecRessource;
import com.resourcesManager.backend.resourcesManager.entities.Ressource;
import com.resourcesManager.backend.resourcesManager.repositories.PanneRepository;
import com.resourcesManager.backend.resourcesManager.repositories.RessourceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor

public class PanneServiceImpl implements PanneService {

    private final PanneRepository panneRepository;
    private final RessourceRepository ressourceRepository;

    @Override
    public Panne addPanne(Panne panne) {
        return panneRepository.save(panne);
    }

    @Override
    public Panne updatePanne(Panne panne) {
        return panneRepository.save(panne);
    }

    @Override
    public void deletePanne(Long id) {
        panneRepository.deleteById(id);
    }

    @Override
    public List<Panne> getAllPannes() {
        return panneRepository.findAll();
    }

    @Override
    public List<Panne> getPannesMembreDepartement(String id) {
        return panneRepository.findPanneByIdMembreDepartement(id);
    }

    @Override
    public List<Panne> getPanneWithConstatNotNullAndDemandeNull() {
        return panneRepository.findByConstatIsNotNullAndDemandeIsNull();
    }

    @Override
    public List<Panne> getPannesNotTreated() {
        return panneRepository.findPanneByIsTreatedFalse();
    }

    @Override
    public List<Panne> getPanneWithDemandeNotNull() {
        return panneRepository.findByDemandeIsNotNull();
    }

    @Override
    public List<PanneAvecRessource> getNotTreatedPanne() {
        List<Panne> pannes = panneRepository.findAllByIsTreatedFalse();
        List<Ressource> ressources = ressourceRepository.findAll();

        List<PanneAvecRessource> panneAvecRessourceList = pannes.stream()
                .map(panne -> {
                    Ressource ressource = ressources.stream()
                            .filter(r -> r.getId() == panne.getIdRessource())
                            .findFirst()
                            .orElse(null);
                    PanneAvecRessource panneAvecRessource = PanneAvecRessource.builder()
                            .idPanne(panne.getId())
                            .idRessource(ressource == null ? null : ressource.getId())
                            .idMembreDepartement(ressource == null ? null :ressource.getIdMembreDepartement())
                            .dateApparition(panne.getDateApparition())
                            .type(ressource == null ? null : ressource.getType())
                            .marque(ressource == null ? null : ressource.getMarque())
                            .build();
                    return panneAvecRessource;
                })
                .collect(Collectors.toList());
        return panneAvecRessourceList;
    }
}
