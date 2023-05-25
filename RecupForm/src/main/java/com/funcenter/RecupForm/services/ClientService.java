package com.funcenter.RecupForm.services;

import com.funcenter.RecupForm.entities.Client;
import org.springframework.stereotype.Service;


public interface ClientService {
    Client save(Client client);
}
