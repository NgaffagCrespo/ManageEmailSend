package com.funcenter.RecupForm.services;


import com.funcenter.RecupForm.entities.Client;
import com.funcenter.RecupForm.repositories.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class ClientServiceImp implements ClientService {

    public ClientServiceImp(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    ClientRepository clientRepository;

    @Override
    public Client save(Client client) {

        client.setId(UUID.randomUUID().toString());


        return clientRepository.save(client);
    }
}
