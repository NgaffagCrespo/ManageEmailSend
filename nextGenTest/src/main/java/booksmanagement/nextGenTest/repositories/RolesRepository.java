package booksmanagement.nextGenTest.repositories;

import booksmanagement.nextGenTest.entities.Roles_Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles_Manager,Long> {
    Roles_Manager findByRoleName(String roleName);
}
