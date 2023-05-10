package com.resourcesManager.backend.resourcesManager.services;

import com.resourcesManager.backend.resourcesManager.entities.Imprimante;
import com.resourcesManager.backend.resourcesManager.exceptions.NotFoundException;
import com.resourcesManager.backend.resourcesManager.repositories.ImprimanteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor

public class ImprimanteServiceImpl implements ImprimanteService {

    private final ImprimanteRepository imprimanteRepository;

    @Override
    public Imprimante addImprimante(Imprimante imprimante) {
        return imprimanteRepository.save(imprimante);
    }

    @Override
    public List<Imprimante> getAllImprimantes() {
        List<Imprimante> imprimantes = imprimanteRepository.findAll();
        return filterImprimantes(imprimantes);
    }

    @Override
    public List<Imprimante> getImprimantesByMembreDepartement(String id) {
        List<Imprimante> imprimantes = imprimanteRepository.getImprimanteByIdMembreDepartement(id);
        return filterImprimantes(imprimantes);
    }

    @Override
    public List<Imprimante> getImprimantesByDepartement(Long id) {
        List<Imprimante> imprimantes = imprimanteRepository.getImprimanteByIdDepartement(id);
        return filterImprimantes(imprimantes);
    }

    @Override
    public List<Imprimante> getImprimantesByFournisseur(String id) {
        List<Imprimante> imprimantes = imprimanteRepository.getImprimanteByIdFournisseur(id);
        return filterImprimantes(imprimantes);
    }

    @Override
    public Imprimante getImprimante(Long id) {
        Imprimante imprimante = imprimanteRepository.findById(id).orElseThrow(() ->
                new NotFoundException("L'imprimante avec l'id = " + id + " est introuvable")
        );
        if (imprimante.getIsDeleted())
            throw new NotFoundException("L'imprimante avec l'id = " + id + " est supprime");
        return imprimante;

    }

    @Override
    public Imprimante updateImprimante(Imprimante imprimante) {
        return imprimanteRepository.save(imprimante);
    }

    @Override
    public void deleteImprimante(Long id) {
        Imprimante imprimante = this.getImprimante(id);
        imprimante.setIsDeleted(true);
        imprimanteRepository.save(imprimante);
    }

    public List<Imprimante> filterImprimantes(List<Imprimante> imprimantes) {
        List<Imprimante> imprimanteList = imprimantes.stream()
                .filter(imprimante -> "Imprimante".equals(imprimante.getType()))
                .filter(imprimante -> !imprimante.getIsDeleted())
                .toList();

        return imprimanteList;
    }

    @Override
    public List<Imprimante> getImprimantesLivrees() {
        List<Imprimante> imprimanteNonLivre = filterImprimantes(imprimanteRepository.findAllByCodeBarreIsNullAndMarqueIsNotNull());
        return imprimanteNonLivre;
    }

    @Override
    public List<Imprimante> getImprimantesDisponibles() {
        List<Imprimante> imprimanteDisponible = filterImprimantes(imprimanteRepository.findAllByCodeBarreIsNotNull());
        return imprimanteDisponible;
    }

}
