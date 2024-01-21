package com.devsuperior.desafioapirest.services;

import com.devsuperior.desafioapirest.entities.Client;
import com.devsuperior.desafioapirest.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

}
