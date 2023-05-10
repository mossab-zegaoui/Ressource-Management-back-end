package com.resourcesManager.backend.resourcesManager.services;

import com.resourcesManager.backend.resourcesManager.entities.AppelOffre;
import com.resourcesManager.backend.resourcesManager.exceptions.NotFoundException;
import com.resourcesManager.backend.resourcesManager.repositories.AppelOffreRepository;
import com.resourcesManager.backend.resourcesManager.repositories.BesoinRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Service
@Transactional
public class AppelOffreImpl implements AppelOffreService {
    private final AppelOffreRepository appelOffreRepository;
    private final BesoinRepository besoinRepository;

    public AppelOffreImpl(AppelOffreRepository appelOffreRepository,
                          BesoinRepository besoinRepository) {
        this.appelOffreRepository = appelOffreRepository;
        this.besoinRepository = besoinRepository;
    }


    @Override
    public AppelOffre getAppelOffre(Long id) {
        return appelOffreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Appel d'offre not found with id: " + id));
    }


    @Override
    public List<AppelOffre> getAllAppelOffre() {
        return appelOffreRepository.findAll();
    }

    @Override
    public void publierAppelOffre(AppelOffre appelOffre) {
        Optional.ofNullable(appelOffre.getBesoins())
                .stream()
                .flatMap(Collection::stream)
                .peek(b -> b.setIsBesoinInAppelOffre(true))
                .forEach(besoinRepository::save);
        appelOffre.setDatePub(Date.valueOf(LocalDate.now()));
        appelOffreRepository.save(appelOffre);
    }

    @Override
    public void deleteAppelOffre(Long id) {
        AppelOffre appelOffre = appelOffreRepository.findAppelOffreById(id);
        appelOffre.getBesoins().stream()
                .peek(besoin -> besoin.setIsBesoinInAppelOffre(false))
                .forEach(besoinRepository::save);

        appelOffreRepository.deleteById(id);
    }

}