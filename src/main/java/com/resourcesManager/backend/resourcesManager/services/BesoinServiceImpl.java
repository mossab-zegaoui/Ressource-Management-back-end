package com.resourcesManager.backend.resourcesManager.services;

import com.resourcesManager.backend.resourcesManager.entities.Besoin;
import com.resourcesManager.backend.resourcesManager.entities.Imprimante;
import com.resourcesManager.backend.resourcesManager.entities.Ordinateur;
import com.resourcesManager.backend.resourcesManager.entities.Ressource;
import com.resourcesManager.backend.resourcesManager.exceptions.NotFoundException;
import com.resourcesManager.backend.resourcesManager.repositories.BesoinRepository;
import com.resourcesManager.backend.resourcesManager.repositories.ImprimanteRepository;
import com.resourcesManager.backend.resourcesManager.repositories.MembreDepartementRepository;
import com.resourcesManager.backend.resourcesManager.repositories.OrdinateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class BesoinServiceImpl implements BesoinService {

    private final BesoinRepository besoinRepository;
    private final OrdinateurRepository ordinateurRepository;
    private final ImprimanteRepository imprimanteRepository;


    @Override
    public Besoin save(Besoin besoin) {

        List<Ordinateur> ordinateurs = ordinateurRepository.saveAll(besoin.getOrdinateurs()
                .stream()
                .toList());
        List<Imprimante> imprimantes = imprimanteRepository.saveAll(besoin.getImprimantes()
                .stream().
                toList());
        besoin.setOrdinateurs(ordinateurs);
        besoin.setImprimantes(imprimantes);
        return besoinRepository.save(besoin);
    }

    @Override
    public List<Besoin> getAllBesoins() {
        return besoinRepository.findAll();
    }

    @Override
    public List<Besoin> getAllBesoinDepartement(Long id) {
        return besoinRepository.findBesoinByIdDepartement(id);
    }

    @Override
    public List<Besoin> getBesoinsMembreDepartement(String id) {
        return besoinRepository.findBesoinByIdMembreDepartement(id);
    }

    @Override
    public List<Besoin> getBesoinsDepartementNotInAppelOffre(Long id) {
        return besoinRepository.findBesoinByIdDepartementAndIsBesoinInAppelOffreIsFalse(id);
    }

    @Override
    public Besoin updateBesoin(Besoin besoin) {
        return besoinRepository.save(besoin);
    }

    @Override
    public Besoin getBesoinMembreDepartementNotInAppelOffre(String id) {
        return besoinRepository.findBesoinByIdMembreDepartementAndIsBesoinInAppelOffreIsFalse(id);
    }

    @Override
    public void besoinAddedInAppelOffre(Long id) {
        Besoin besoin = besoinRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Le besoin avec l'id = " + id + " est introuvable")
        );
        besoin.setIsBesoinInAppelOffre(true);
        besoinRepository.save(besoin);
    }

    @Override
    public List<Besoin> getBesoinsNotInAppelOffre() {
        return besoinRepository.findAllByIsBesoinInAppelOffreIsFalse();
    }

    @Override
    public void deleteBesoinOfMembre(String id) {
        List<Besoin> besoins = besoinRepository.findBesoinByIdMembreDepartementAndIsAffectedIsFalse(id);
        besoinRepository.deleteAll(besoins);
    }

    @Override
    public void deleteBesoin(Long id) {
        besoinRepository.deleteById(id);
    }

}
