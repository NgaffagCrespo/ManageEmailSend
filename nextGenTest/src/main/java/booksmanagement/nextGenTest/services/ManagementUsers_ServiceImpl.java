package booksmanagement.nextGenTest.services;

import booksmanagement.nextGenTest.entities.Roles_Manager;
import booksmanagement.nextGenTest.entities.User_Manager;
import booksmanagement.nextGenTest.repositories.RolesRepository;
import booksmanagement.nextGenTest.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ManagementUsers_ServiceImpl implements ManagementUsers_Service {

    private RolesRepository rolesRepository;
    private UserRepository userRepository;

    public ManagementUsers_ServiceImpl(RolesRepository rolesRepository, UserRepository userRepository) {
        this.rolesRepository = rolesRepository;
        this.userRepository = userRepository;
    }

    @Override
    public User_Manager addUser(User_Manager userManager) {
        return userRepository.save(userManager);
    }

    @Override
    public Roles_Manager addRoles(Roles_Manager rolesManager) {

        return rolesRepository.save(rolesManager);
    }

    @Override
    public void addRolesToUser(String username, String roleName) {

        User_Manager user_manager = userRepository.findByUsername(username);
        Roles_Manager roles_manager = rolesRepository.findByRoleName(roleName);
        user_manager.getRolesManagers().add(roles_manager);

    }
}
