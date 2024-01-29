package br.com.erudio.apiresterudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        var vo = mapper.map(person, PersonVo.class);
        return vo;
    }

    public PersonVo create(PersonVo person) {
        logger.info("Creating person!");
        var entity = mapper.map(person, Person.class);
        var vo = repository.save(entity);
        return mapper.map(vo, PersonVo.class);
    }

    public PersonVo update(PersonVo personVo) {
        logger.info("Updating person!");
        Person entity = repository.findById(personVo.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found this Id"));
        entity = updatePerson(entity, personVo);
        var vo = mapper.map(repository.save(entity), PersonVo.class);
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
