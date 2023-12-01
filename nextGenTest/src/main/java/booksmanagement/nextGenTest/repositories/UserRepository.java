package booksmanagement.nextGenTest.repositories;

import booksmanagement.nextGenTest.entities.User_Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User_Manager,Long> {
    User_Manager findByUsername(String username);
}
