package com.funcenter.RecupForm.web;


import com.funcenter.RecupForm.entities.Client;
import com.funcenter.RecupForm.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/recupform")
@AllArgsConstructor
public class ClientRestService {

    private ClientService clientService;

    @PostMapping(path = "/client")
    private Client EnregistrerClient(@RequestBody Client client){
        return clientService.save(client);
    }
}
