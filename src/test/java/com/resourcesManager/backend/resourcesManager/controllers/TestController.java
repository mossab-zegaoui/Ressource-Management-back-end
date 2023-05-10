package com.resourcesManager.backend.resourcesManager.controllers;

import com.resourcesManager.backend.resourcesManager.entities.Role;
import com.resourcesManager.backend.resourcesManager.entities.User;
import com.resourcesManager.backend.resourcesManager.services.AuthenticationServiceImpl;
import com.resourcesManager.backend.resourcesManager.services.LogoutService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthController.class)
@ExtendWith(SpringExtension.class)
public class TestController {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AuthenticationServiceImpl authenticationService;
    @MockBean
    private LogoutService logoutService;
    private User USER_AMINE;

    @Test
    public void testGetAllTechniciens() throws Exception {
//        USER_AMINE = new User();
//        USER_AMINE.setUsername("amine");
//        USER_AMINE.setNom("amine");
//        USER_AMINE.setUsername("amine_123");
//        USER_AMINE.setEmail("amine@gmail.com");
//        USER_AMINE.setPassword("amine_password");
//        List<User> users = Arrays.asList(USER_AMINE);
//        when(authenticationService.getAllUsers()).thenReturn(users);
//        mockMvc.perform(get("/api/v1/users")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", Matchers.hasSize(1)))
//                .andExpect(jsonPath("$[0].nom", Matchers.is("amine")));

    }

    @Test
    void testGetRoles() throws Exception {
        Role role = Role.builder()
                .nomRole("RESPONSABLE")
                .id(1L)
                .build();
        when(authenticationService.getAllRoles()).thenReturn(List.of(role));
        mockMvc.perform(get("/api/v1/users/roles")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].nomRole", Matchers.is("RESPONSABLE")));
    }

}
