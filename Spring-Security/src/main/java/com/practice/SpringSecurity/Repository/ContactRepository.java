package com.practice.SpringSecurity.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.practice.SpringSecurity.Entity.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {


}