package com.resourcesManager.backend.resourcesManager.services;

import com.resourcesManager.backend.resourcesManager.entities.*;
import com.resourcesManager.backend.resourcesManager.exceptions.EntityAlreadyExistsException;
import com.resourcesManager.backend.resourcesManager.repositories.PanneRepository;
import com.resourcesManager.backend.resourcesManager.repositories.RessourceRepository;
import com.resourcesManager.backend.resourcesManager.repositories.RoleRepository;
import com.resourcesManager.backend.resourcesManager.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class TestServices {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private PanneRepository panneRepository;
    @Mock
    private RessourceRepository ressourceRepository;
    @InjectMocks
    private AuthenticationServiceImpl authenticationService;
    @InjectMocks
    private PanneServiceImpl panneService;
    private User USER_AMINE;
    private User USER_KHALID;
    private Role ROLE_ADMIN;
    private Role ROLE_USER;
    private Role ROLE_RESPONSABLE;
    private Panne PANNE_1;
    private Panne PANNE_2;
    private Ressource ORDINATEUR;
    private Ressource IMPRIMANTE;

    @BeforeEach
    void setupService() {
        USER_AMINE = new User();
        USER_AMINE.setUsername("amine");
        USER_AMINE.setUsername("amine_123");
        USER_AMINE.setEmail("amine@gmail.com");
        USER_AMINE.setPassword("amine_password");
        USER_KHALID = new User();
        USER_KHALID.setUsername("khalid");
        USER_KHALID.setUsername("khalid_123");
        USER_KHALID.setEmail("khalid@gmail.com");
        USER_KHALID.setPassword("khalid_password");
//
        ROLE_ADMIN = new Role();
        ROLE_ADMIN.setId(1L);
        ROLE_ADMIN.setNomRole("ADMIN");
        ROLE_USER = new Role();
        ROLE_USER.setId(2L);
        ROLE_USER.setNomRole("USER");
        ROLE_RESPONSABLE = new Role();
        ROLE_RESPONSABLE.setNomRole("responsable");
//
        PANNE_1 = new Panne();
        PANNE_1.setId(1L);
        PANNE_1.setIsTreated(false);
        PANNE_1.setIdRessource(1L);
        PANNE_1.setDateApparition(Date.valueOf(LocalDate.now()));

        PANNE_2 = new Panne();
        PANNE_2.setId(2L);
        PANNE_2.setIsTreated(false);
        PANNE_2.setIdRessource(2L);
        PANNE_2.setDateApparition(Date.valueOf(LocalDate.now()));

        ORDINATEUR = new Ressource();
        ORDINATEUR.setId(1L);
        ORDINATEUR.setIdMembreDepartement("1");
        ORDINATEUR.setCodeBarre(UUID.randomUUID().toString());
        ORDINATEUR.setDateLivraison(Date.valueOf(LocalDate.of(2023, 06, 11)));
        ORDINATEUR.setDateFinGarantie(Date.valueOf(LocalDate.of(2023, 07, 11)));
        ORDINATEUR.setType("Ordinateur");
        ORDINATEUR.setMarque("Canon");
        ORDINATEUR.setPrix(25);
        IMPRIMANTE = new Ressource();
        IMPRIMANTE.setId(2L);
        IMPRIMANTE.setIdMembreDepartement("2");
        IMPRIMANTE.setCodeBarre(UUID.randomUUID().toString());
        IMPRIMANTE.setDateLivraison(Date.valueOf(LocalDate.of(2023, 06, 11)));
        IMPRIMANTE.setDateFinGarantie(Date.valueOf(LocalDate.of(2023, 07, 11)));
        IMPRIMANTE.setType("Imprimante");
        IMPRIMANTE.setMarque("HP");
        IMPRIMANTE.setPrix(199);


    }

    @Test
    void Should_Return_ListofAllUsers() {
//
        List<User> expectedUsers = List.of(USER_AMINE, USER_KHALID);
        when(userRepository.findAll()).thenReturn(expectedUsers);
//
        List<User> users = authenticationService.getAllUsers();
//
        Assertions.assertThat(users).isNotEmpty();
        assertEquals(expectedUsers.size(), users.size());
        assertEquals(expectedUsers, users);
    }

    @Test
    void Should_Return_ListofAllRoles() {
//
        List<Role> expectedRoles = List.of(ROLE_ADMIN, ROLE_USER);
        when(roleRepository.findAll()).thenReturn(expectedRoles);
//
        List<Role> roles = authenticationService.getAllRoles();
//
        Assertions.assertThat(roles).isNotEmpty();
        assertEquals(expectedRoles.size(), roles.size());
        assertEquals(expectedRoles, roles);
    }

    @Test
    void Should_Return_SavedUser() {
//
        String encodedPassword = "$2a$04$4Jt/RqPoiCEHqmN78S60s.gGtHVJtabZkBcnFAbY4Y6hXD8jhcqcS";
        User expectedUser = USER_AMINE;
        expectedUser.setPassword(encodedPassword);
        expectedUser.setRoles(List.of(ROLE_USER));
        when(userRepository.findUserByUsername(USER_AMINE.getUsername())).thenReturn(null);
        when(passwordEncoder.encode(USER_AMINE.getPassword())).thenReturn(encodedPassword);
        when(roleRepository.findRoleByNomRole("USER")).thenReturn(ROLE_USER);
        when(userRepository.save(USER_AMINE)).thenReturn(expectedUser);
//
        User savedUser = authenticationService.saveUser(USER_AMINE);
//
        assertNotNull(savedUser);
        assertEquals(expectedUser, savedUser);
    }

    @Test
    void saveUser_ShouldThrow_EntityAlreadyExistsException() {
        when(userRepository.findUserByUsername(USER_AMINE.getUsername())).thenReturn(USER_AMINE);
        EntityAlreadyExistsException exception = assertThrows(EntityAlreadyExistsException.class, () -> {
            authenticationService.saveUser(USER_AMINE);
        });
        String expectedMessage = exception.getMessage();
        String actualMessage = "Usename:" + USER_AMINE.getUsername() + " already exists !! Try another username";
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void saveResponsable_shouldReturn_savedResponsable() {
//
        String encodedPassword = "$2a$04$4Jt/RqPoiCEHqmN78S60s.gGtHVJtabZkBcnFAbY4Y6hXD8jhcqcS";
        User expectedUser = USER_AMINE;
        expectedUser.setRoles(List.of(ROLE_RESPONSABLE));
        expectedUser.setPassword(encodedPassword);
        when(userRepository.findUserByUsername(USER_AMINE.getUsername())).thenReturn(null);
        when(roleRepository.findRoleByNomRole("responsable")).thenReturn(ROLE_RESPONSABLE);
        when(userRepository.save(USER_AMINE)).thenReturn(expectedUser);
//
        User savedUser = authenticationService.saveResponsable(USER_AMINE);
//
        assertNotNull(savedUser);
        assertEquals(expectedUser, savedUser);
    }

    @Test
    void saveRole_shouldReturn_savedRole() {
//
        when(roleRepository.findRoleByNomRole(ROLE_USER.getNomRole())).thenReturn(null);
//
        Role savedRole = authenticationService.saveRole(ROLE_USER.getNomRole());
//
        assertNotNull(savedRole);
        assertEquals(ROLE_USER.getNomRole(), savedRole.getNomRole());
    }

    @Test
    void getNotTreatedPanne_should_Return_ListofPannesAvecRessourcesNotTreated() {
//

        List<Panne> pannes = List.of(PANNE_1, PANNE_2);
        List<Ressource> ressources = List.of(ORDINATEUR, IMPRIMANTE);

        when(panneRepository.findAllByIsTreatedFalse()).thenReturn(pannes);
        when(ressourceRepository.findAll()).thenReturn(ressources);
        PanneAvecRessource panneAvecRessource_1 = PanneAvecRessource.builder()
                .idPanne(PANNE_1.getId())
                .idRessource(ORDINATEUR.getId())
                .idMembreDepartement(ORDINATEUR.getIdMembreDepartement())
                .type(ORDINATEUR.getType())
                .marque(ORDINATEUR.getMarque())
                .dateApparition(PANNE_1.getDateApparition())
                .build();
        PanneAvecRessource panneAvecRessource_2 = PanneAvecRessource.builder()
                .idPanne(PANNE_2.getId())
                .idRessource(IMPRIMANTE.getId())
                .idMembreDepartement(IMPRIMANTE.getIdMembreDepartement())
                .type(IMPRIMANTE.getType())
                .marque(IMPRIMANTE.getMarque())
                .dateApparition(PANNE_2.getDateApparition())
                .build();
        List<PanneAvecRessource> panneAvecRessources = List.of(panneAvecRessource_1, panneAvecRessource_2);
//
        List<PanneAvecRessource> result = panneService.getNotTreatedPanne();
//
        Assertions.assertThat(result).isNotEmpty();
        assertEquals(panneAvecRessources.size(), result.size());
        assertEquals(panneAvecRessources, result);

    }
}
