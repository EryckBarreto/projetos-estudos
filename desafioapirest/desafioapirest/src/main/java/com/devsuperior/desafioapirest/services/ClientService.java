package com.devsuperior.desafioapirest.services;

import com.devsuperior.desafioapirest.dto.ClientDTO;
import com.devsuperior.desafioapirest.entities.Client;
import com.devsuperior.desafioapirest.repositories.ClientRepository;
import com.devsuperior.desafioapirest.services.exceptions.DatabaseException;
import com.devsuperior.desafioapirest.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional
    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado."));
        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> result = clientRepository.findAll(pageable);
        return result.map(x -> new ClientDTO(x));
    }

    public ClientDTO insert(ClientDTO clientDTO) {
        Client entity = new Client();
        copyDtoToEntity(clientDTO, entity);
        entity = clientRepository.save(entity);
        return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO clientDTO) {
        try {
            Client entity = clientRepository.getReferenceById(id);
            copyDtoToEntity(clientDTO, entity);
            entity = clientRepository.save(entity);
            return new ClientDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado.");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrato.");
        }
        try {
            clientRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial.");
        }
    }

    private void copyDtoToEntity(ClientDTO clientDTO, Client entity) {
        entity.setName(clientDTO.getName());
        entity.setCpf(clientDTO.getCpf());
        entity.setIncome(clientDTO.getIncome());
        entity.setBirthDate(clientDTO.getBirthDate());
        entity.setChildren(clientDTO.getChildren());
    }

}
