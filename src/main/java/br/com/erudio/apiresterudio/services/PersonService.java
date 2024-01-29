package br.com.erudio.apiresterudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.erudio.apiresterudio.controllers.PersonControler;
import br.com.erudio.apiresterudio.data.vo.v1.PersonVo;
import br.com.erudio.apiresterudio.exceptions.ResourceNotFoundException;
import br.com.erudio.apiresterudio.mapper.custom.PersonMapper;
import br.com.erudio.apiresterudio.models.Person;
import br.com.erudio.apiresterudio.repositories.PersonRepository;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());
    
    @Autowired
    private PersonRepository repository;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PersonMapper personMapper;

    public List<PersonVo> findAll() {
        var listPersons = repository.findAll();
        List<PersonVo> listVo = personMapper.findAllVo(listPersons);
        return listVo;
    }

    public PersonVo findById(Long id) {
        logger.info("Finding one person!");
        Person person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found this Id"));
        PersonVo vo = mapper.map(person, PersonVo.class);
        vo.add(linkTo(methodOn(PersonControler.class).findById(id)).withSelfRel());
        vo.setKey(id);
        return vo;
    }

    public PersonVo create(PersonVo personVo) {
        logger.info("Creating person!");
        Person entity = mapper.map(personVo, Person.class);
        Person person = repository.save(entity);
        PersonVo vo = mapper.map(person, PersonVo.class);
        vo.setKey(person.getId());
        return vo;
    }

    public PersonVo update(PersonVo personVo) {
        logger.info("Updating person!");
        Person entity = repository.findById(personVo.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found this Id"));
        entity = updatePerson(entity, personVo);
        var vo = mapper.map(repository.save(entity), PersonVo.class);
        vo.setKey(entity.getId());
        return vo;
    }

    public void remove(Long id) {
        var vo = findById(id);
        var person = mapper.map(vo, Person.class);
        repository.delete(person);
    }

    private Person updatePerson(Person entity, PersonVo vo) {
        entity.setFirstName(vo.getFirstName());
        entity.setLastName(vo.getLastName());
        entity.setAddress(vo.getAddress());
        entity.setGender(vo.getGender());
        return entity;
    }
}
