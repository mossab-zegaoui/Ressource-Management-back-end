package com.resourcesManager.backend.resourcesManager.services;

import com.resourcesManager.backend.resourcesManager.entities.Demande;
import com.resourcesManager.backend.resourcesManager.entities.Departement;
import com.resourcesManager.backend.resourcesManager.entities.MembreDepartement;
import com.resourcesManager.backend.resourcesManager.exceptions.NotFoundException;
import com.resourcesManager.backend.resourcesManager.repositories.DemandeRepository;
import com.resourcesManager.backend.resourcesManager.repositories.DepartementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DemandeServiceImpl implements DemandeService {

    private final DemandeRepository demandeRepository;

    private final DepartementRepository departementRepository;
    private final MembreDepartementService membreDepartementService;

    @Override
    public void createDemande(Long idDepartement) {
        List<MembreDepartement> membresDepartement = membreDepartementService.getMembresByIdDepartement(idDepartement);
        membresDepartement.removeIf(membre -> membre.getRoles().stream().anyMatch(r -> r.getNomRole().equals("CHEF_DEP")));
        List<Demande> demandes = membresDepartement.stream()
                .map(membre -> Demande.builder()
                        .message("Envoyez vos besoins")
                        .dateDemande(Date.valueOf(LocalDate.now()))
                        .idDepartement(idDepartement)
                        .idMembreDepartement(membre.getId())
                        .isSeen(false)
                        .build())
                .collect(Collectors.toList());
        demandeRepository.saveAll(demandes);
    }

    @Override
    public Long getDepartementIdByUserId(String userId) {

        Departement departement = departementRepository
                .getDepartementIdByUserId(userId).orElseThrow(() -> new NotFoundException("cannot find departement of user: id" + userId ));

        return departement.getId();
    }

    @Override
    public Demande demandeSeen(Long id) {
        Demande demande = demandeRepository.findById(id).orElseThrow(() ->
                new NotFoundException("La demande avec l'id = " + id + " est introuvable")
        );
        demande.setIsSeen(true);
        return demandeRepository.save(demande);
    }

    @Override
    public List<Demande> getAllDemandesByMembreId(String id) {
        return demandeRepository.findAllByIdMembreDepartement(id);
    }

    @Override
    public List<Demande> getAllDemandesByIdDepartement(Long id) {
        return demandeRepository.findAllByIdDepartement(id);
    }

    @Override
    public List<Demande> getAllDemandes() {
        return demandeRepository.findAll();
    }

    @Override
    public void deleteDemande(Long id) {
        demandeRepository.deleteById(id);
    }
}
