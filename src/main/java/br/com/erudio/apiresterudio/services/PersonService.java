package br.com.erudio.apiresterudio.services;

import java.util.List;
import java.util.logging.Logger;

import br.com.erudio.apiresterudio.exceptions.RequireObjectIsNullException;
import br.com.erudio.apiresterudio.mapper.custom.MapperCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import br.com.erudio.apiresterudio.controllers.PersonControler;
import br.com.erudio.apiresterudio.data.vo.v1.PersonVo;
import br.com.erudio.apiresterudio.exceptions.ResourceNotFoundException;
import br.com.erudio.apiresterudio.models.Person;
import br.com.erudio.apiresterudio.repositories.PersonRepository;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository repository;

    public List<PersonVo> findAll() {
        var listPersons = repository.findAll();
         var listVO = MapperCustom.parseListObjects(listPersons, PersonVo.class);
         listVO.forEach(x -> x.add(linkTo(methodOn(PersonControler.class).findAll()).withSelfRel()));
         return listVO;
    }

    public PersonVo findById(Long id) {
        logger.info("Finding one person!");
        Person person = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found this Id"));
        PersonVo vo = MapperCustom.parseObject(person, PersonVo.class);
        vo.add(linkTo(methodOn(PersonControler.class).findById(id)).withSelfRel());
        return vo;
    }

    public PersonVo create(PersonVo personVo) {
        if(personVo == null) throw new RequireObjectIsNullException();
        logger.info("Creating person!");
        Person entity = MapperCustom.parseObject(personVo, Person.class);
        Person person = repository.save(entity);
        PersonVo vo = MapperCustom.parseObject(person, PersonVo.class);
        vo.add(linkTo(methodOn(PersonControler.class).findById(person.getId())).withSelfRel());

        return vo;
    }

    public PersonVo update(PersonVo personVo) {
        if(personVo == null) throw new RequireObjectIsNullException();
        logger.info("Updating person!");
        Person person = repository.findById(personVo.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found this Id"));
        var personUpdate = updatePerson(person, personVo);
        var vo = MapperCustom.parseObject(repository.save(personUpdate), PersonVo.class);
        vo.add(linkTo(methodOn(PersonControler.class).findById(personVo.getKey())).withSelfRel());
        return vo;
    }

    public void remove(Long id) {
        var vo = findById(id);
        var person = MapperCustom.parseObject(vo, Person.class);
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
