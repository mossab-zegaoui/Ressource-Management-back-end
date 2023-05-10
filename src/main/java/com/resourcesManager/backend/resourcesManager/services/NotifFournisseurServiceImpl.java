package com.resourcesManager.backend.resourcesManager.services;


import com.resourcesManager.backend.resourcesManager.entities.NotifFournisseur;
import com.resourcesManager.backend.resourcesManager.exceptions.NotFoundException;
import com.resourcesManager.backend.resourcesManager.repositories.NotifFournisseurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor

public class NotifFournisseurServiceImpl implements NotifFournisseurService {

    private final NotifFournisseurRepository notifFournisseurRepository;


    @Override
    public List<NotifFournisseur> getNotifsByIdFournisseurNotSeen(String id) {
        return notifFournisseurRepository.getNotifFournisseurByIdFournisseurAndIsSeenIsFalse(id);
    }

    @Override
    public List<NotifFournisseur> getAllNotifications() {
        return notifFournisseurRepository.findAllByAndIsSeenIsFalse();
    }

    @Override
    public void addNotifFournisseur(NotifFournisseur notifFournisseur) {
        notifFournisseurRepository.save(notifFournisseur);
    }

    @Override
    public void notifSeen(Long id) {
        NotifFournisseur notifFournisseur = notifFournisseurRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("notification with id:" + id + " not found"));
        notifFournisseur.setIsSeen(true);
        notifFournisseurRepository.save(notifFournisseur);
    }
}
