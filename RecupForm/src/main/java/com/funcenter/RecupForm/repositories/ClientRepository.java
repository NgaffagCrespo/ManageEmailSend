package com.funcenter.RecupForm.repositories;

import com.funcenter.RecupForm.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
}
