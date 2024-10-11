package com.unibell.Unibell.controllers;

import com.unibell.Unibell.models.Client;
import com.unibell.Unibell.models.Contact;
import com.unibell.Unibell.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.addClient(client));
    }

    @PostMapping("/{clientId}/contacts")
    public ResponseEntity<Contact> addContact(@PathVariable Long clientId, @RequestBody Contact contact) {
        return ResponseEntity.ok(clientService.addContact(clientId, contact));
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientById(id));
    }

    @GetMapping("/{clientId}/contacts")
    public ResponseEntity<List<Contact>> getContactsByClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(clientService.getContactsByClient(clientId));
    }
}
