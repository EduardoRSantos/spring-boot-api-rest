package br.com.erudio.apiresterudio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.erudio.apiresterudio.models.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
    
}
