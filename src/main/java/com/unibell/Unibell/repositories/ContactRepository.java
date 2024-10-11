package com.unibell.Unibell.repositories;

import com.unibell.Unibell.models.Client;
import com.unibell.Unibell.models.Contact;
import com.unibell.Unibell.models.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByClientAndType(Client client, Type contactType);
}
