package booksmanagement.nextGenTest.services;

import booksmanagement.nextGenTest.entities.Roles_Manager;
import booksmanagement.nextGenTest.entities.User_Manager;

public interface ManagementUsers_Service {

    User_Manager addUser( User_Manager userManager);
    Roles_Manager addRoles(Roles_Manager rolesManager);
    void addRolesToUser(String username, String roleName);

    User_Manager showUser(String username);
}
