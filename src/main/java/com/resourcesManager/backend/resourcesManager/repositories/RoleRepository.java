package com.resourcesManager.backend.resourcesManager.repositories;

import com.resourcesManager.backend.resourcesManager.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByNomRole(String roleName);
}