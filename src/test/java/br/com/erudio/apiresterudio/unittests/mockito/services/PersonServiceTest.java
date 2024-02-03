package br.com.erudio.apiresterudio.unittests.mockito.services;

import br.com.erudio.apiresterudio.data.vo.v1.PersonVo;
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

import java.util.List;
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

        Person entity = input.mockEntity(1);

        when(personRepository.findById(1L)).thenReturn(Optional.of(entity));

        var result = personService.findById(1L);

        assertNotNull(result);
        assertNotNull(result.getLinks());
        assertNotNull(result.getKey());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("Female", result.getGender());

    }

    @Test
    void findAll() {
        List<Person> listPerson = input.mockEntityList();

        when(personRepository.findAll()).thenReturn(listPerson);

        var people = personService.findAll();

        assertNotNull(people);
        assertEquals(14, people.size());

        var personOne = people.get(1);

        assertNotNull(personOne);
        assertNotNull(personOne.getLinks());
        assertNotNull(personOne.getKey());
        assertEquals("First Name Test1", personOne.getFirstName());
        assertEquals("Last Name Test1", personOne.getLastName());
        assertEquals("Addres Test1", personOne.getAddress());
        assertEquals("Female", personOne.getGender());


        var personThree = people.get(3);

        assertNotNull(personThree);
        assertNotNull(personThree.getLinks());
        assertNotNull(personThree.getKey());
        assertEquals("First Name Test3", personThree.getFirstName());
        assertEquals("Last Name Test3", personThree.getLastName());
        assertEquals("Addres Test3", personThree.getAddress());
        assertEquals("Female", personThree.getGender());


        var personTwelve = people.get(12);

        assertNotNull(personTwelve);
        assertNotNull(personTwelve.getLinks());
        assertNotNull(personTwelve.getKey());
        assertEquals("First Name Test12", personTwelve.getFirstName());
        assertEquals("Last Name Test12", personTwelve.getLastName());
        assertEquals("Addres Test12", personTwelve.getAddress());
        assertEquals("Male", personTwelve.getGender());
    }



    @Test
    void create() {
        Person entity = input.mockEntity(1);
        Person persisted = entity;

        PersonVo vo = input.mockVO(1);

        when(personRepository.save(entity)).thenReturn(persisted);

        var result = personService.create(vo);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("Female", result.getGender());


    }

    @Test
    void update() {
        Person entity = input.mockEntity(1);
        Person persisted = entity;
        PersonVo vo = input.mockVO(1);


        when(personRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(personRepository.save(entity)).thenReturn(persisted);

        var result = personService.update(vo);

        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());

        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Addres Test1", result.getAddress());
        assertEquals("Female", result.getGender());
    }

    @Test
    void remove() {
        Person entity = input.mockEntity(1);
        var id = 1L;

        when(personRepository.findById(id)).thenReturn(Optional.of(entity));

        personService.remove(id);

    }
}