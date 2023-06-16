package com.resourcesManager.backend.resourcesManager.services;

import com.resourcesManager.backend.resourcesManager.controllers.TechnicienController;
import com.resourcesManager.backend.resourcesManager.entities.User;
import com.resourcesManager.backend.resourcesManager.security.ApplicationConfig;
import com.resourcesManager.backend.resourcesManager.security.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(TechnicienController.class)
@ContextConfiguration(classes = {ApplicationConfig.class, SecurityConfig.class})
@ExtendWith(SpringExtension.class)
public class TestControllers {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TechnicienServiceImpl technicienService;
    private User USER_AMINE;
    private User USER_KHALID;


    @Test
    void getUsers_ShouldReturn_ListofUsers() throws Exception {
//        USER_AMINE = new User();
//        USER_AMINE.setUsername("amine");
//        USER_AMINE.setUsername("amine_123");
//        USER_AMINE.setEmail("amine@gmail.com");
//        USER_AMINE.setPassword("amine_password");
//        USER_KHALID = new User();
//        USER_KHALID.setUsername("khalid");
//        USER_KHALID.setUsername("khalid_123");
//        USER_KHALID.setEmail("khalid@gmail.com");
//        USER_KHALID.setPassword("khalid_password");
////
//        List<User> users = List.of(USER_AMINE, USER_KHALID);
////
//        when(authenticationService.getAllUsers()).thenReturn(users);
////
//        mockMvc.perform(get("/api/v1/users"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id").value(USER_AMINE.getId()))
//                .andExpect(jsonPath("$[0].username").value(USER_AMINE.getUsername()))
//                .andExpect(jsonPath("$[0].email").value(USER_AMINE.getEmail()))
//        ;
    }
}
