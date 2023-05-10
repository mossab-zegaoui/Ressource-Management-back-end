package com.resourcesManager.backend.resourcesManager.services;

import com.resourcesManager.backend.resourcesManager.entities.Fournisseur;
import com.resourcesManager.backend.resourcesManager.exceptions.NotFoundException;
import com.resourcesManager.backend.resourcesManager.repositories.FournisseurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class FournisseurServiceImpl implements FournisseurService {

    private final FournisseurRepository fournisseurRepository;


    @Override
    public void blackListFournisseur(String id, String motif) {
        Fournisseur fournisseur = fournisseurRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Fournisseur introuvable"));
        fournisseur.setBlackList(true);
        fournisseur.setMotifDeBlockage(motif);
        fournisseurRepository.save(fournisseur);
    }

    @Override
    public Fournisseur updateFournisseur(Fournisseur fournisseur) {
        return fournisseurRepository.save(fournisseur);
    }

    @Override
    public List<Fournisseur> getFournisseur() {
        return fournisseurRepository.findAll();
    }
}
