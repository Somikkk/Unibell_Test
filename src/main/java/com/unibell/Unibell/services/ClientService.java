package com.unibell.Unibell.services;

import com.unibell.Unibell.models.Client;
import com.unibell.Unibell.models.Contact;
import com.unibell.Unibell.models.Type;
import com.unibell.Unibell.repositories.ClientRepository;
import com.unibell.Unibell.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ContactRepository contactRepository;

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public Contact addContact(Long clientId, Contact contact) {
        Client client = clientRepository.findById(clientId).orElseThrow();
        contact.setClient(client);
        return contactRepository.save(contact);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow();
    }

    public List<Contact> getContactsByClient(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow();
        return client.getContacts();
    }

    public List<Contact> getContactByType(Long clientId, Type type) {
        Client client = clientRepository.findById(clientId).orElseThrow();
        return contactRepository.findByClientAndType(client, type);
    }
}
