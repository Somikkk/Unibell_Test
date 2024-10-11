package com.unibell.Unibell.controllertest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unibell.Unibell.controllers.ClientController;
import com.unibell.Unibell.models.Client;
import com.unibell.Unibell.models.Contact;
import com.unibell.Unibell.models.Type;
import com.unibell.Unibell.services.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testAddClient() throws Exception {
        Client client = new Client();
        client.setName("Test Client");

        when(clientService.addClient(any(Client.class))).thenReturn(client);

        mockMvc.perform(post("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Client"));

        verify(clientService, times(1)).addClient(any(Client.class));
    }

    @Test
    public void testGetAllClients() throws Exception {
        Client client = new Client();
        client.setName("Test Client");

        List<Client> clients = Collections.singletonList(client);
        when(clientService.getAllClients()).thenReturn(clients);

        mockMvc.perform(get("/api/clients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Client"));

        verify(clientService, times(1)).getAllClients();
    }

    @Test
    public void testGetClientById() throws Exception {
        Client client = new Client();
        client.setName("Test Client");

        when(clientService.getClientById(anyLong())).thenReturn(client);

        mockMvc.perform(get("/api/clients/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Client"));

        verify(clientService, times(1)).getClientById(1L);
    }

    @Test
    public void testAddContact() throws Exception {
        Contact contact = new Contact();
        contact.setType(Type.PHONE);
        contact.setValue("123456789");

        when(clientService.addContact(anyLong(), any(Contact.class))).thenReturn(contact);

        mockMvc.perform(post("/api/clients/1/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contact)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value("123456789"));

        verify(clientService, times(1)).addContact(anyLong(), any(Contact.class));
    }

    @Test
    public void testGetContactsByClient() throws Exception {
        Contact contact = new Contact();
        contact.setType(Type.EMAIL);
        contact.setValue("test@example.com");

        List<Contact> contacts = Collections.singletonList(contact);
        when(clientService.getContactsByClient(anyLong())).thenReturn(contacts);

        mockMvc.perform(get("/api/clients/1/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].value").value("test@example.com"));

        verify(clientService, times(1)).getContactsByClient(1L);
    }
}