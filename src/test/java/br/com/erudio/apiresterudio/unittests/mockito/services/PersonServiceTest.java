package br.com.erudio.apiresterudio.unittests.mockito.services;

import br.com.erudio.apiresterudio.models.Person;
import br.com.erudio.apiresterudio.repositories.PersonRepository;
import br.com.erudio.apiresterudio.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.erudio.apiresterudio.unittests.mapper.mocks.MockPerson;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    MockPerson input;

    @InjectMocks
    private PersonService personService;

    @Mock
    PersonRepository personRepository;

    @BeforeEach
    void setUpMocks() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {

        Person person = input.mockEntity();
        person.setId(1L);

        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        var result = personService.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getLinks());
        assertNotNull(result.getKey());

    }

    @Test
    void findAll() {
    }



    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void remove() {
    }
}