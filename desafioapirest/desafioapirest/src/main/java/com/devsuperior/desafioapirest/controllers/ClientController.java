package com.devsuperior.desafioapirest.controllers;

import com.devsuperior.desafioapirest.dto.ClientDTO;
import com.devsuperior.desafioapirest.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id) {
        ClientDTO clientDTO = clientService.findById(id);
        return ResponseEntity.ok(clientDTO);
    }

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable) {
        Page<ClientDTO> clientDTO = clientService.findAll(pageable);
        return ResponseEntity.ok(clientDTO);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> insert(@Valid @RequestBody ClientDTO clientDTO) {
        clientDTO = clientService.insert(clientDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientDTO.getId())
                .toUri();
        return ResponseEntity.created(uri)
                .body(clientDTO);
    }


}
