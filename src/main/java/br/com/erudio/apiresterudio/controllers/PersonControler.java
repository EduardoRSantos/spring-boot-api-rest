package br.com.erudio.apiresterudio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.erudio.apiresterudio.data.vo.v1.PersonVo;
import br.com.erudio.apiresterudio.services.PersonService;
import br.com.erudio.apiresterudio.util.MediaType;

@RestController
@RequestMapping("/person")
public class PersonControler {

    @Autowired
    private PersonService service;

    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML })
    public ResponseEntity<PersonVo> findById(@PathVariable(value = "id") Long id) {
        PersonVo person = service.findById(id);
        return ResponseEntity.ok().body(person);
    }

    @GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public ResponseEntity<List<PersonVo>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML }, produces = {
                    MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public ResponseEntity<PersonVo> create(@RequestBody PersonVo person) {
        return ResponseEntity.ok().body(service.create(person));
    }

    @PutMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.APPLICATION_YML }, produces = {
                    MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
    public ResponseEntity<PersonVo> update(@RequestBody PersonVo person) {
        return ResponseEntity.ok().body(service.update(person));
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "Id") Long id) {
        service.remove(id);
        return ResponseEntity.noContent().build();
    }

}
